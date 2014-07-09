import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 * Класс RegistrationGUI содержит интерфейс пользователя для заполнения данных пользователя
 * 
 * Автор: Цуман А., Шуба С. Критиковал код: Шуба С., Цуман А.
 */
public class RegistrationGUI extends Frame implements ActionListener {
		private static final long serialVersionUID = 1L;
		private Label lblFName; // Declare component Label
		private Label lblLname; // Declare component Label
		private Label lblGroup; // Declare component Label
		private TextField tfFName; // Declare component TextField
		private TextField tfLName; // Declare component TextField
		private TextField tfGroup; // Declare component TextField
		private Button btnConfirm; // Declare component Button

	    private PrintWriter out = null;

	    /**
	     * Runs the client application.
	     */
		public static void main(String[] args) throws Exception {
	    	RegistrationGUI client = new RegistrationGUI();
	        client.connectToServer();
	    }
		
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
	            "Welcome to the Tests Program",
	            JOptionPane.QUESTION_MESSAGE);
	        
	        // Make connection and initialize streams
	        Socket socket = new Socket(serverAddress, 9898);
	        out = new PrintWriter(socket.getOutputStream(), true);
	    }
	    
		/**
		 *  Constructor to setup GUI components and event handling   
		 */
		public RegistrationGUI() {
			setLayout(null);
			
			lblFName = new Label("Имя: "); // construct Label
			lblFName.setLocation(60, 90);
			lblFName.setSize(120, 30);
			add(lblFName); // Frame adds Label
			
			lblLname = new Label("Фамилия: "); // construct Label
			lblLname.setLocation(60, 150);
			lblLname.setSize(120, 30);
			add(lblLname); // Frame adds Label
			
			lblGroup = new Label("Группа: "); // construct Label
			lblGroup.setLocation(60, 210);
			lblGroup.setSize(120, 30);
			add(lblGroup); // Frame adds Label

			tfFName = new TextField("", 100); // construct TextField
			tfFName.setLocation(240, 90);
			tfFName.setSize(210, 30);
			add(tfFName); // Frame adds tfFName
			
			tfLName = new TextField("", 100); // construct TextField
			tfLName.setLocation(240, 150);
			tfLName.setSize(210, 30);
			add(tfLName); // Frame adds tfLName
			
			tfGroup = new TextField("", 100); // construct TextField
			tfGroup.setLocation(240, 210);
			tfGroup.setSize(210, 30);
			add(tfGroup); // Frame adds tfGroup

			btnConfirm = new Button("Подтвердить"); // construct Button
			btnConfirm.setLocation(180, 270);
			btnConfirm.setSize(120, 30);
			add(btnConfirm); // Frame adds Button

			btnConfirm.addActionListener(this);
			// Clicking Button source fires ActionEvent
			// btnCount registers this instance as ActionEvent listener

			setTitle("Регистрация"); // Frame sets title
			setSize(510, 330); // Frame sets initial window size

			setVisible(true); // Frame shows
			
			this.addWindowListener(new WindowAdapter() {
				  public void windowClosing(WindowEvent e) {
					  System.exit(0);  
				  }
				});
		}

		/** 
		 * ActionEvent handler - Called back upon button-click. 
		 */
		@Override
		public void actionPerformed(ActionEvent evt) {
			out.println(getInfo());
			this.dispose();
			try {
				new TestGUI().connectToServer();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		
		/**
		 * Метод getInfo - получает из формы информацию о пользователе
		 * @return String - строка данных пользователя
		 */
		public String getInfo() {
			String s = new String();
			s += 99 + " " + tfFName.getText() + " " + tfLName.getText() + " " + tfGroup.getText();
			return s;
		}
}