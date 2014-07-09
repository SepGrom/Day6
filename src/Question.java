/**
 * Класс Question содержит номер вопроса, описание вопроса,
 * список ответов и правильный ответ
 * Автор: Шуба С. Критиковал код: Цуман А.
 */
public class Question {
	public int number;
	private String question;
	private Answer[] answers;
	private Answer rightAnswer;
	/**
	 * Конструктор класса
	 * @param n - номер вопроса
	 * @param q - описание вопроса
	 * @param a - список ответов
	 * @param ra - правильный ответ
	 */
	public Question(int n, String q, Answer[] a, Answer ra) {
		number = n;
		question = q;
		answers = a;
		rightAnswer = ra;
	}
	/**
	 * Метод IsTrue(int a)
	 * Проверяет правильность ответа a
	 * @param a - введенный ответ
	 * @return - boolean
	 */
	public boolean IsTrue(int a) {
		if(a == rightAnswer.number)
			return true;
		return false;
	}
	/**
	 * Метод ToString()
	 * Формирует блок вопроса
	 * @return String
	 */
	public String toString() {
		String s = number + ". " + question + "\r\n";
		for(int i = 0; i < answers.length; i++) {
			s += answers[i].toString();
		}
		return s;
	}
}
