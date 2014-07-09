/**
 * Класс Answer содержит номер ответа
 * и само описание ответа
 * Автор: Шуба С. Критиковал код: Цуман А.
 */
public class Answer {
	public int number;
	private String description;
	public Answer(int n, String s) {
		number = n;
		description = s;
	}
	/**
	 * Метод ToString()
	 * Формирует строку ответа
	 * @return String
	 */
	public String toString() {
		String s = number + ") " + description + "\r\n";
		return s;
	}
}