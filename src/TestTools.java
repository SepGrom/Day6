import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TestTools {
		// ������� ���������� ��� ����������� ���������� ��
		String[] studentInfo = new String[3];
		int[] quesionNumbersForDB = new int[5];
		int[] answerNumbersForDB = new int[5];
		String[] quesionMarksForDB = new String[5];
		int[] answerTimeForDB = new int[5];
		int mark = 0;
		List<Integer> questionNumbers = getIntList0to9();
		
		/**
		 * ����� checkOneQuestion ��������� ������������ ������ �� ������
		 * � ������� ��������������� ����������
		 * @param questions - ������ ��������
		 * @param i - ����� ��������
		 * @param num - ����� ������� � ������� ��������
		 * @param ans - �����
		 * @return String - ������ �����������
		 */
		public String checkOneQuestion(Question[] questions, int i, int num, String ans) {
			String result = new String();
			if (i != 4) {
				if (questions[num].IsTrue(new Integer(ans))) {
					result += ("�� �������� �����.\n��������� ������:");
					mark++;
					quesionMarksForDB[i] = "�����.";
				} else {
					result += ("�� �������� �������.\n��������� ������:");
					quesionMarksForDB[i] = "�������.";
				}
			} else {
				if (questions[num].IsTrue(new Integer(ans))) {
					result += ("�� �������� �����.\n���� ��������.");
					mark++;
					quesionMarksForDB[i] = "�����.";
				} else {
					result += ("�� �������� �������.\n���� ��������.");
					quesionMarksForDB[i] = "�������.";
				}
			}
			return result;
		}

		/**
		 * ����� askOneQuestion - ��������� ���� ������
		 * @param questions - ������ ��������
		 * @param i - ����� ���������
		 * @param num - ����� ������� � �������
		 * @return String - ������ �������
		 */
		public String askOneQuestion(Question[] questions, int i, int num) {
			String result = new String();
			quesionNumbersForDB[i] = questions[num].number;
			// ������ ������
			result += (questions[num].toString());
			return result;
		}

		/**
		 * ����� getRandomUniqueQuestionNumber - ���������� ��������� ����� �������
		 * @param questions - ������ ��������
		 * @param questionNumbers - ������ �������
		 * @return int - ��������� ����� �������
		 */
		public int getRandomUniqueQuestionNumber(Question[] questions) {
			// ������ �������� � ����������� ��������
			// ��������� ������ ������� �� 0 �� 9
			Random rand = new Random();
			// ��������� ����� �� 0 �� ������� ������ �� ������� ������
			int numb = rand.nextInt(questionNumbers.size());
			// ��������� ������� ������ (�� 0 �� 10)
			int num = questionNumbers.get(numb);
			// �������� ��������� ������� �� ������, ����� ���� ������� ���
			questionNumbers.remove(numb);
			return num;
		}

		/**
		 * ����� getIntList0to9 - ��������������� �����, ���������� ������ � �������� �� 0 �� 9
		 * @return List<Integer> - ������ � �������� �� 0 �� 9
		 */
		public static List<Integer> getIntList0to9() {
			List<Integer> questionNumbers = new ArrayList<Integer>();
			for (int i = 0; i < 10; i++) {
				questionNumbers.add(i);
			}
			return questionNumbers;
		}

		/**
		 * ����� writeStudentInfo(String s) �� ������ s �������� ���������� � ������������
		 * 
		 * @param s - ������� ������
		 * @throws IOException - ����������, ����������� ��� ������������� ������ �����-������.
		 */
		public String[] getStudentInfo(String s) {
			String[] info = new String[3];
			info[0] = s.split(" ")[1];
			info[1] = s.split(" ")[2];
			info[2] = s.split(" ")[3];
			return info;
		}
}
