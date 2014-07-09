import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A server program which accepts requests from clients to
 * capitalize strings.  When clients connect, a new thread is
 * started to handle an interactive dialog in which the client
 * sends in a string and the server thread sends back the
 * capitalized version of the string.
 *
 * The program is runs in an infinite loop, so shutdown in platform
 * dependent.  If you ran it from a console window with the "java"
 * interpreter, Ctrl+C generally will shut it down.
 */
public class ServerClass {
	static Question[] questions;
	static TestTools testInfoToDB = new TestTools();
    /**
     * Application method to run the server runs in an infinite loop
     * listening on port 9898.  When a connection is requested, it
     * spawns a new thread to do the servicing and immediately returns
     * to listening.  The server keeps a unique client number for each
     * client that connects just to show interesting logging
     * messages.  It is certainly not necessary to do this.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("The DB server is running.");
        questions = DataBaseTools.getQuestions();
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(9898);
        try {
            while (true) {
                new Capitalizer(listener.accept(), clientNumber++).run();
            }
        } finally {
            listener.close();
        }
    }

    /**
     * A private thread to handle capitalization requests on a particular
     * socket.  The client terminates the dialogue by sending a single line
     * containing only a period.
     */
    private static class Capitalizer extends Thread {
        private Socket socket;
        private int clientNumber;
        boolean first = true;
    	long startTime;
    	int num = 0;

        public Capitalizer(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            log("New connection with client# " + clientNumber + " at " + socket);
        }

        /**
         * Services this thread's client by first sending the
         * client a welcome message then repeatedly reading strings
         * and sending back the capitalized version of the string.
         */
        public void run() {
            try {

                // Decorate the streams so we can send characters
                // and not just bytes.  Ensure output is flushed
                // after every newline.
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Send a welcome message to the client.
                // out.println("Hello, you are client #" + clientNumber + ".");
                // out.println("Enter a line with only a period to quit\n");

                // Get messages from the client, line by line; return them
                // capitalized
                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        break;
                    } if(first) {
                    	if(splitInputLine(input)[0].equals("99")) {
                    		ServerClass.testInfoToDB.studentInfo = ServerClass.testInfoToDB.getStudentInfo(input);
                    		break;
                    	}
                    	first = false;
                    } else {
                    	if(splitInputLine(input)[0].equals("0")) {
                    		askFirstQuestion(0, num, out);
                    	} else if(splitInputLine(input)[0].equals("5")) {
                    		checkLastQuestion(5, num, splitInputLine(input)[1]);
                    		out.println(getTestResults());
                        	break;
                    	} else {
                    		questionsCycle(new Integer(splitInputLine(input)[0]), num, splitInputLine(input)[1], out);
                    	}
                    }                    
                }
                socket.close();
            } catch (IOException e) {
                log("Error handling client# " + clientNumber + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log("Couldn't close a socket, what's going on?");
                }
                log("Connection with client# " + clientNumber + " closed");
            }
        }
        
        /**
         * Метод splitInputLine
         * @param s
         * @return
         */
        public static String[] splitInputLine(String s) {
    		String[] info = s.split(" ");
    		return info;
    	}
        
        /**
    	 * Метод askFirstQuestion - задаёт первый вопрос
    	 * @param i - номер итерации
    	 * @param num - номер вопроса в массиве вопросов
    	 */
    	public void askFirstQuestion(int i, int num, PrintWriter out) {
    		askOneQuestion(i, out);
    		startTime = System.currentTimeMillis();
    	}
    	
    	/**
    	 * Метод checkLastQuestion - проверяет ответ а последний вопрос
    	 * @param i - номер итерации
    	 * @param num - номер вопроса в массиве вопросов
    	 * @throws IOException 
    	 */
    	public void checkLastQuestion(int i, int num, String answer) throws IOException {
    		checkOneQuestion(i-1, num, answer);
    		long timeSpent = System.currentTimeMillis() - startTime;
    		ServerClass.testInfoToDB.answerTimeForDB[i-1] = (int) timeSpent;
    	}
    	
    	/**
    	 * Метод askOneQuestion задает пользователю один вопрос
    	 */
    	public void askOneQuestion(int i, PrintWriter out) {
    		num = ServerClass.testInfoToDB.getRandomUniqueQuestionNumber(questions);
    		out.println(ServerClass.testInfoToDB.askOneQuestion(questions, i, num));
    	}
    	
    	/**
    	 * Метод checkOneQuestion - проверяет ответ на вопрос
    	 * @param ans - ответ, введенный пользователем
    	 * @param num - номер вопроса в массиве вопросов
    	 */
    	public void checkOneQuestion(int i, int num, String ans) {
    		ServerClass.testInfoToDB.checkOneQuestion(questions, i, num, ans);
    		ServerClass.testInfoToDB.answerNumbersForDB[i] = new Integer(ans);
    	}
        
        /**
        * Метод getTestResults - записывает результаты тестирования в БД и выдает их на экран
        */
        public String getTestResults() {
        	String res = new String();
        	// Записываем результаты тестирования в БД
        	DataBaseTools.writeDBLog(ServerClass.testInfoToDB.studentInfo[0], ServerClass.testInfoToDB.studentInfo[1],
        			ServerClass.testInfoToDB.studentInfo[2], ServerClass.testInfoToDB.quesionNumbersForDB, ServerClass.testInfoToDB.answerNumbersForDB,
        			ServerClass.testInfoToDB.quesionMarksForDB, ServerClass.testInfoToDB.answerTimeForDB, ServerClass.testInfoToDB.mark);
        	// Выводим результаты тестирования
        	res += "Результаты теста:\n";
        	res += DataBaseTools.getResults();
        	return res;		
        }

        /**
         * Logs a simple message.  In this case we just write the
         * message to the server applications standard output.
         */
        private void log(String message) {
            System.out.println(message);
        }

    	/**
    	 * Метод questionsCycle проверяет предыдущий вопрос и задает следующий
    	 * @param i - номер итерации
    	 * @param num - номер вопроса в массиве вопросов
    	 */
    	public void questionsCycle(int i, int num, String answer,  PrintWriter out) {
    		checkOneQuestion(i-1, num, answer);
    		long timeSpent = System.currentTimeMillis() - startTime;
    		ServerClass.testInfoToDB.answerTimeForDB[i-1] = (int) timeSpent;
    		askOneQuestion(i, out);
    		startTime = System.currentTimeMillis();
    	}
    }
}
