import java.sql.*;

/**
 * Класс DataBaseTools содержит инструменты для работы с БД SQLite
 * 
 * Автор: Цуман А., Шуба С., Критиковал код: Шуба С., Цуман А.
 */
public class DataBaseTools {

	/**
	 * Метод createDB() подключается к БД test.db
	 * и создает в ней таблицу TEST
	 */
	public static void createDB() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS TEST"
					+ "(ID INT PRIMARY KEY     NOT NULL,"
					+ " QUESTION       TEXT    NOT NULL, "
					+ " ANSWER1        TEXT    NOT NULL, "
					+ " ANSWER2        TEXT    NOT NULL, "
					+ " ANSWER3        TEXT    NOT NULL, "
					+ " ANSWER4        TEXT    NOT NULL, "
					+ " NANSWER        INT     NOT NULL, "
					+ " RANSWER        TEXT    NOT NULL)";

			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	/**
	 * Метод fillDBMusic() заполняет таблицу TEST БД test.db
	 * вопросами предметной области - музыка
	 */
	public static void fillDBMusic() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			String sqlDel = "DELETE FROM TEST;";
			stmt.executeUpdate(sqlDel);
			c.commit();

			String sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) "
					+ "VALUES (1, 'Кого судебным приговоров в наказание за наркотики заставили играть благотворительные концерты?', 'Элис Купер', 'Стиви Уандер', 'Rolling Stones', 'Dire Straits', 3, 'Rolling Stones' );";
				stmt.executeUpdate(sql);
				sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) "
					+ "VALUES (2, 'На обложке альбома какой группы был огромная надувная свинья?', 'AC/DC', 'Radiohead', 'Pink Floyd', 'Black Sabbath', 3, 'Pink Floyd' );";
				stmt.executeUpdate(sql);
				sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) "
					+ "VALUES (3, 'Чьё горе стало основой для песни Smoke on the Water?', 'Фрэнк Заппа', 'Билли Джоэл', 'Ричи Блэкмор', 'Капитан Бифхарт', 1, 'Фрэнк Заппа' );";
				stmt.executeUpdate(sql);
				sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) "
					+ "VALUES (4, 'Для какой группы почти всю музыку сочинял наполовину глухой человек?', 'Duran Duran', 'The Beach Boys', 'The Police', 'Slade', 2, 'The Beach Boys' );";
				stmt.executeUpdate(sql);
				sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) "
					+ "VALUES (5, 'Из-за чего скончался Боб Марли?', 'Перешёл на тяжёлые наркотики', 'Не перенёс суровый климат при переезде из Ямайки в Британию', 'Религия не дозволяла ему лечиться', 'Чувствовал, что больше не запишет ничего стоящего', 3, 'Религия не дозволяла ему лечиться' );";
				stmt.executeUpdate(sql);
				sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) "
					+ "VALUES (6, 'Как называлась первая рок-группа, которая была создана специально для телешоу?', 'The Monkees', 'The Searchers', 'Ramones', 'The Everly Brothers', 1, 'The Monkees' );";
				stmt.executeUpdate(sql);
				sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) "
					+ "VALUES (7, 'Гитаристом какой группы был Кит Мун?', 'T.Rex', 'The Who', 'Oasis', 'Он не гитарист, а барабанщик', 4, 'Он не гитарист, а барабанщик' );";
				stmt.executeUpdate(sql);
				sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) "
					+ "VALUES (8, 'Кем был по национальности Фредди Меркьюри?', 'Араб', 'Негр', 'Итальянец', 'Парс', 4, 'Парс' );";
				stmt.executeUpdate(sql);
				sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) "
					+ "VALUES (9, 'Каким образом группа U2 получила деньги на свою первую запись?', 'Они выиграли на конкурсе молодых талантов', 'Они сдали полиции одного преступника', 'Они торговали в цветочном магазине', 'Они одолжили их у известного музыканта', 1, 'Они выиграли на конкурсе молодых талантов' );";
				stmt.executeUpdate(sql);
				sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) "
					+ "VALUES (10, 'Как звали первого рокера, разбившего гитару на сцене?', 'Джими Хендрикс', 'Рэй Манзарек', 'Линк Рэй', 'Пит Тауншенд', 4, 'Пит Тауншенд' );";
				stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}
	
	/**
	 * Метод fillDBLiterature() заполняет таблицу TEST БД test.db
	 * вопросами предметной области - литература
	 */
	public static void fillDBLiterature() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			String sqlDel = "DELETE FROM TEST;";
			stmt.executeUpdate(sqlDel);
			c.commit();

			stmt = c.createStatement();
		    String sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) " +
		                 "VALUES (1, 'Кто написал повесть Странная история доктора Джекилла и мистера Хайда?', 'Фрэнк Уайлдхорн', 'Роберт Стивенсон', 'Лесли Брикасс', 'Генри Джеймс', 2, 'Роберт Стивенсон' );"; 
		    stmt.executeUpdate(sql);

		    sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) " +
		            "VALUES (2, 'Какова центральная проблема книги Харпер Ли Убить пересмешника?', 'Добро и милосердие', 'Война', 'Убийство птиц', 'Поиск смысла жизни', 1, 'Добро и милосердие' );"; 
		    stmt.executeUpdate(sql);

		    sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) " +
		            "VALUES (3, 'Из скольки томов состоит роман Льва Толстого Война и мир?', 'Один', 'Два', 'Три', 'Четыре', 4, 'Четыре' );"; 
		    stmt.executeUpdate(sql);  
		    
		    sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) " +
		            "VALUES (4, 'Какая из перечисленных книг Дафны дю Морье была экранизирована Альфредом Хичкоком?', 'Синие линзы', 'Птицы', 'Паразиты', 'Не позже полуночи', 2, 'Птицы' );"; 
		    stmt.executeUpdate(sql); 
		    
		    sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) " +
		            "VALUES (5, 'Королём какого жанра считается Стивен Кинг?', 'Ужасы', 'Драма', 'Научная фантастика', 'Поэзия', 1, 'Ужасы' );"; 
		    stmt.executeUpdate(sql); 
		    
		    sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) " +
		            "VALUES (6, 'Уроженкой какой страны была Айн Рэнд?', 'США', 'Германия', 'Российская Империя', 'Великобритания', 3, 'Российская империя' );"; 
		    stmt.executeUpdate(sql); 
		    
		    sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) " +
		            "VALUES (7, 'Кем были главные герои романа Франсуа Рабле Гаргантюа и Пантагрюэль?', 'Путешественники', 'Торговцы', 'Плотники', 'Великаны', 4, 'Великаны' );"; 
		    stmt.executeUpdate(sql); 
		    
		    sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) " +
		            "VALUES (8, 'Какие события описывает роман Эрнеста Хемингуэя По ком звонит колокол?', 'Первая мировая война', 'Вторая мировая война', 'Гражданская война в Испании', 'Великая депрессия в США', 3, 'Гражданская война в Испании' );"; 
		    stmt.executeUpdate(sql); 
		    
		    sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) " +
		            "VALUES (9, 'Куда попадает главный герой романа Кена Кизи Пролетая над гнездом кукушки?', 'Необитаемый остров', 'Психиатрическая больница', 'Тюрьма', 'Дом престарелых', 2, 'Психиатрическая больница' );"; 
		    stmt.executeUpdate(sql); 
		    
		    sql = "INSERT INTO TEST (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,ANSWER4,NANSWER,RANSWER) " +
		            "VALUES (10, 'Кто стал первым свидетелем говорящей головы в романе Александра Беляева Голова профессора Доуэля?', 'Лаборант', 'Случайный прохожий', 'Ребёнок', 'Ассистентка', 4, 'Ассистентка' );"; 
		    stmt.executeUpdate(sql); 

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	/**
	 * Метод getQuestions() формирует массив вопросов из данных таблицы TEST БД test.db
	 * @return Question[]
	 */
	public static Question[] getQuestions() {
		Connection c = null;
		Statement stmt = null;
		Question[] questions = new Question[10];
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM TEST;");
			rs.next(); // !!! Без этой строчки работает неверно, дублирует первый вопрос и не вносит последний
			for (int i = 0; i < 10; i++) {
				int id = rs.getInt("ID");
				String question = rs.getString("QUESTION");
				String answer1 = rs.getString("ANSWER1");
				String answer2 = rs.getString("ANSWER2");
				String answer3 = rs.getString("ANSWER3");
				String answer4 = rs.getString("ANSWER4");
				int nanswer = rs.getInt("NANSWER");
				String ranswer = rs.getString("RANSWER");
				questions[i] = new Question(id, question, new Answer[] {
						new Answer(1, answer1), new Answer(2, answer2),
						new Answer(3, answer3), new Answer(4, answer4) },
						new Answer(nanswer, ranswer));
				rs.next();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return questions;
	}

	/**
	 * Метод writeDBLog(String fname, String lname, String group,
	 *		int[] questions, int[] answers, String[] marks, int[] time, int total)
	 * соединяется с БД log.db и создает в ней таблицу LOG
	 * которую заполняет значениями параметров		
	 * @param fname - имя студента
	 * @param lname - фамилия
	 * @param group - группа
	 * @param questions - массив номеров вопросов, на которые отвечал пользователь
	 * @param answers - массив ответов пользователя 
	 * @param marks - массив содержащий отметки
	 * @param time - массив с таймингом вопросов
	 * @param total - результат теста
	 */
	public static void writeDBLog(String fname, String lname, String group,
			int[] questions, int[] answers, String[] marks, int[] time, int total) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:log.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS LOG"
					+ "(ID INT PRIMARY KEY ,"
					+ " FNAME       TEXT    NOT NULL, "
					+ " LNAME        TEXT    NOT NULL, "
					+ " NGROUP       TEXT    NOT NULL, "
					+ " QUESTION1        INT    NOT NULL, "
					+ " ANSWER1        INT    NOT NULL, "
					+ " MARK1        TEXT    NOT NULL, "
					+ " TIME1        TEXT    NOT NULL, "
					+ " QUESTION2        INT    NOT NULL, "
					+ " ANSWER2        INT    NOT NULL, "
					+ " MARK2        TEXT    NOT NULL, "
					+ " TIME2        TEXT    NOT NULL, "
					+ " QUESTION3        INT    NOT NULL, "
					+ " ANSWER3        INT    NOT NULL, "
					+ " MARK3        TEXT    NOT NULL, "
					+ " TIME3        TEXT    NOT NULL, "
					+ " QUESTION4        INT    NOT NULL, "
					+ " ANSWER4        INT    NOT NULL, "
					+ " MARK4        TEXT    NOT NULL, "
					+ " TIME4        TEXT    NOT NULL, "
					+ " QUESTION5        INT    NOT NULL, "
					+ " ANSWER5        INT    NOT NULL, "
					+ " MARK5        TEXT    NOT NULL, "
					+ " TIME5        TEXT    NOT NULL, "
					+ " TOTAL        INT    NOT NULL)";
			stmt.executeUpdate(sql);

			// String sqlDel = "DELETE FROM LOG;";
			// stmt.executeUpdate(sqlDel);
			// c.commit();

			sql = "INSERT INTO LOG (FNAME, LNAME, NGROUP, QUESTION1, ANSWER1, MARK1, TIME1, QUESTION2, ANSWER2, MARK2, TIME2, QUESTION3, ANSWER3, MARK3, TIME3, QUESTION4, ANSWER4, MARK4, TIME4, QUESTION5, ANSWER5, MARK5, TIME5, TOTAL) "
					+ "VALUES ('" + fname + "', '" + lname + "', '" + group + "', "
					+ questions[0] + ", " + answers[0] + ", '" + marks[0] + "', " + time[0] + ", "
					+ questions[1] + ", " + answers[1] + ", '" + marks[1] + "', " + time[1] + ", "
					+ questions[2] + ", " + answers[2] + ", '" + marks[2] + "', " + time[2] + ", "
					+ questions[3] + ", " + answers[3] + ", '" + marks[3] + "', " + time[3] + ", "
					+ questions[4] + ", " + answers[4] + ", '" + marks[4] + "', " + time[4] + ", " 
					+ total + ");";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	/**
	 * Метод getResults() формирует строку, содержащую информацию о результатах теста
	 * @return строка результатов
	 */
	public static String getResults() {
		Connection c = null;
		Statement stmt = null;
		String result = new String();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:log.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM LOG ORDER BY ID DESC LIMIT 1;");
			String fname = rs.getString("FNAME");
			String lname = rs.getString("LNAME");
			String ngroup = rs.getString("NGROUP");
			int[] question = new int[5];
			int[] answer = new int[5];
			String[] mark = new String[5];
			int[] time = new int[5];
			result += ("Имя: " + fname + "\r\n");
			result += ("Фамилия: " + lname + "\r\n");
			result += ("Группа: " + ngroup + "\r\n");
			result += ("Результаты теста:\r\n");
			for(int i = 1; i < 6; i++) {
				question[i-1] = rs.getInt("QUESTION" + i);
				answer[i-1] = rs.getInt("ANSWER" + i);
				mark[i-1] = rs.getString("MARK" + i);
				time[i-1] = rs.getInt("TIME" + i);
				
				result += ("Вопрос №" + question[i-1] + ". Ответ: " + answer[i-1] + ". " + mark[i-1] + " Затраченое время: " + time[i-1] + " мсек.\r\n");
			}
			int total = rs.getInt("TOTAL");
			result += ("Оценка: " + total);
			
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return result;
	}
}
