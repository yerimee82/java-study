package chat.gui;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.util.Scanner;

public class ChatWindow {
	String name = null;
	Scanner scanner = new Scanner(System.in);

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;

	public ChatWindow(String name) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
	}

	public void show() {
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.BLACK);
		buttonSend.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent actionEvent ) {
				sendMessage();
			}
		});

		// Textfield
		textField.setColumns(80);

		// 엔터 누를 시, 이름 없는 KeyListener 클래스 : KeyAdapter() 전달
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					sendMessage();
				}

			}
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
//				System.exit(0);
				finish();
			}
		});
		frame.setVisible(true);
		frame.pack();

		// IOStream 받아오기
		// chatClientThread 생성

	}

	private void finish() {
		// quit 프로토콜 구현

		
		// exit java application
		System.exit(0);
	}

	private void sendMessage() {
		String message = textField.getText();
		System.out.println("메세지 보내는 프로토콜을 구현!: " + message);

		// send 시 텍스트 창 초기화 및 커서 focus
		textField.setText("");
		textField.requestFocus();

		// ChatClientThread 에서 서버로부터 받은 메세지가 있다고 친다...
		updateTextArea("마이콜:" + message);

	}

	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}

	private class ChatClientThread extends Thread {
		public void run() {
			// String message = br.readlind();
			updateTextArea("마이콜 : 밥먹으러 가자");
		}
	}
}
