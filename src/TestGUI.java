import java.awt.*; // Using AWT container and component classes
import java.awt.event.*; // Using AWT event classes and listener interfaces
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 * Класс TestGUI содержит интерфейс пользователя для тестирования
 * 
 * Автор: Цуман А., Шуба С. Критиковал код: Шуба С., Цуман А.
 */
//An AWT program inherits from the top-level container java.awt.Frame
public class TestGUI extends Frame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Label lblQuestion; // Declare component Label
	private Label lblAnswer; // Declare component Label
	private TextArea tfQuestion; // Declare component TextField
	private TextField tfAnswer; // Declare component TextField
	private Button btnConfirm; // Declare component Button

	private BufferedReader in = null;
    private PrintWriter out = null;
    
    int i = 0;

    /**
     * Runs the client application.
     */
	//public static void main(String[] args) throws Exception {
    //	TestGUI client = new TestGUI();
    //  client.connectToServer();
    //}
	
	/**
     * Implements the connection logic by prompting the end user for
     * the server's IP address, connecting, setting up streams, and
     * consuming the welcome messages from the server.  The Capitalizer
     * protocol says that the server sends three lines of text to the
     * client immediately after establishing a connection.
     */
    public void connectToServer() throws IOException {
        // Get the server address from a dialog box.
        String serverAddress = JOptionPane.showInputDialog(
        		this,
            "Enter IP Address of the Server:",
            "Welcome to the 123 Program",
            JOptionPane.QUESTION_MESSAGE);
        
        // Make connection and initialize streams
        Socket socket = new Socket(serverAddress, 9898);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        // Consume the initial welcoming messages from the server
        //for (int i = 0; i < 3; i++) {
        //    tfQuestion.append(in.readLine() + "\n");
        //}
    }
	
	/** Constructor to setup GUI components and event handling */
	public TestGUI() {
		setLayout(null);

		lblQuestion = new Label("Тестирование."); // construct Label
		lblQuestion.setLocation(30, 30);
		lblQuestion.setSize(120, 30);
		add(lblQuestion); // "super" Frame adds Label
		lblAnswer = new Label("Ответ: "); // construct Label
		lblAnswer.setLocation(30, 300);
		lblAnswer.setSize(120, 30);
		add(lblAnswer); // "super" Frame adds Label

		tfQuestion = new TextArea("", 100, 10, 1); // construct TextField
		tfQuestion.setLocation(30, 90);
		tfQuestion.setSize(510, 180);
		tfQuestion.setEditable(false); // set to read-only
		add(tfQuestion); // "super" Frame adds tfCount
		
		tfAnswer = new TextField("", 100); // construct TextField
		tfAnswer.setLocation(210, 300);
		tfAnswer.setSize(120, 30);
		add(tfAnswer); // "super" Frame adds tfCount

		btnConfirm = new Button("Начать"); // construct Button
		btnConfirm.setLocation(420, 300);
		btnConfirm.setSize(120, 30);
		add(btnConfirm); // "super" Frame adds Button

		btnConfirm.addActionListener(this);
		// Clicking Button source fires ActionEvent
		// btnCount registers this instance as ActionEvent listener

		setTitle("Тестирование"); // "super" Frame sets title
		setSize(600, 360); // "super" Frame sets initial window size

		setVisible(true); // "super" Frame shows
		
		this.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
				  System.exit(0);  
			  }
			});
		
		tfQuestion.setText("Итак, приступим.\n");
	}

	/** 
	 * ActionEvent handler - Called back upon button-click. 
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(i < 6) {
			if (i == 0) {
				btnConfirm.setLabel("Подтвердить");
				out.println("0 0");
			}
			if (i == 5) {
				btnConfirm.setLabel("Завершить");
				askQuestion(5, out);
				tfQuestion.setText("");
				try {
					int k = 0;
					while(k < 11) {
						tfQuestion.append(in.readLine() + '\n');
						k++;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				askQuestion(i, out);
				tfQuestion.setText("");
				try {
					int k = 0;
					while(k < 6) {
						tfQuestion.append(in.readLine() + '\n');
						k++;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			this.dispose();
			System.exit(0);
		}
		i++;
	}
	
	public void askQuestion(int i, PrintWriter out) {
		out.println(i + " " + tfAnswer.getText());
	}
}