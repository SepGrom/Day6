import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TestTools {
		// Создаем переменные для дальнейшего заполнения БД
		String[] studentInfo = new String[3];
		int[] quesionNumbersForDB = new int[5];
		int[] answerNumbersForDB = new int[5];
		String[] quesionMarksForDB = new String[5];
		int[] answerTimeForDB = new int[5];
		int mark = 0;
		List<Integer> questionNumbers = getIntList0to9();
		
		/**
		 * Метод checkOneQuestion проверяет правильность ответа на вопрос
		 * и выводит соответствующую информацию
		 * @param questions - массив вопросов
		 * @param i - номер итерации
		 * @param num - номер вопроса в массиве вопросов
		 * @param ans - ответ
		 * @return String - строка результатов
		 */
		public String checkOneQuestion(Question[] questions, int i, int num, String ans) {
			String result = new String();
			if (i != 4) {
				if (questions[num].IsTrue(new Integer(ans))) {
					result += ("Вы ответили верно.\nСледующий вопрос:");
					mark++;
					quesionMarksForDB[i] = "Верно.";
				} else {
					result += ("Вы ответили неверно.\nСледующий вопрос:");
					quesionMarksForDB[i] = "Неверно.";
				}
			} else {
				if (questions[num].IsTrue(new Integer(ans))) {
					result += ("Вы ответили верно.\nТест завершен.");
					mark++;
					quesionMarksForDB[i] = "Верно.";
				} else {
					result += ("Вы ответили неверно.\nТест завершен.");
					quesionMarksForDB[i] = "Неверно.";
				}
			}
			return result;
		}

		/**
		 * Метод askOneQuestion - формирует один вопрос
		 * @param questions - массив вопросов
		 * @param i - номер итерациии
		 * @param num - номер вопроса в массиве
		 * @return String - строка вопроса
		 */
		public String askOneQuestion(Question[] questions, int i, int num) {
			String result = new String();
			quesionNumbersForDB[i] = questions[num].number;
			// Задаем вопрос
			result += (questions[num].toString());
			return result;
		}

		/**
		 * Метод getRandomUniqueQuestionNumber - возвращает случайный номер вопроса
		 * @param questions - массив вопросов
		 * @param questionNumbers - список номеров
		 * @return int - случайный номер вопроса
		 */
		public int getRandomUniqueQuestionNumber(Question[] questions) {
			// Решаем проблему с повторением вопросов
			// Заполняем список числами от 0 до 9
			Random rand = new Random();
			// Случайное число от 0 до размера списка на текущий момент
			int numb = rand.nextInt(questionNumbers.size());
			// Случайный элемент списка (от 0 до 10)
			int num = questionNumbers.get(numb);
			// Выбираем случайный элемент из списка, после чего удаляем его
			questionNumbers.remove(numb);
			return num;
		}

		/**
		 * Метод getIntList0to9 - вспомогательный метод, возвращает список с номерами от 0 до 9
		 * @return List<Integer> - список с номерами от 0 до 9
		 */
		public static List<Integer> getIntList0to9() {
			List<Integer> questionNumbers = new ArrayList<Integer>();
			for (int i = 0; i < 10; i++) {
				questionNumbers.add(i);
			}
			return questionNumbers;
		}

		/**
		 * Метод writeStudentInfo(String s) Из строки s получаем информацию о пользователе
		 * 
		 * @param s - входная строка
		 * @throws IOException - Исключение, создаваемое при возникновении ошибки ввода-вывода.
		 */
		public String[] getStudentInfo(String s) {
			String[] info = new String[3];
			info[0] = s.split(" ")[1];
			info[1] = s.split(" ")[2];
			info[2] = s.split(" ")[3];
			return info;
		}
}
