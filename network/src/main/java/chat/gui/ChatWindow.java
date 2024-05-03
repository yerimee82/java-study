package chat.gui;

import chat.ChatClient;
import chat.ChatClientThread;
import chat.ChatServer;

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
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatWindow {
    String name = null;
    Scanner scanner = new Scanner(System.in);
    PrintWriter pw = null;

    private Frame frame;
    private Panel pannel;
    private Button buttonSend;
    private TextField textField;
    private static TextArea textArea;
    private Socket socket;

    public ChatWindow(String name, Socket socket) {
        frame = new Frame(name);
        pannel = new Panel();
        buttonSend = new Button("Send");
        textField = new TextField();
        textArea = new TextArea(30, 80);
        this.socket = socket;

        // ChatClientThread 시작
        new ChatClientThread(socket).start();

    }

    public void show() {
        // Button
        buttonSend.setBackground(Color.GRAY);
        buttonSend.setForeground(Color.BLACK);
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
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
                if (keyCode == KeyEvent.VK_ENTER) {
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
                finish();
                System.exit(0);
            }
        });
        frame.setVisible(true);
        frame.pack();

        // IOStream 받아오기
        // chatClientThread 생성


    }

    private void finish() {
        // quit 프로토콜 구현
        try {
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            pw.println("quit:");
            pw.flush();
        }
        catch (SocketException e) {
            ChatServer.log("Socket Exception:" + e);
        } catch (IOException e) {
            ChatServer.log("error:" + e);
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage() {
        try {
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

            String message = textField.getText();
            pw.println("message:"+ message);
            pw.flush();

            // send 시 텍스트 창 초기화 및 커서 focus
            textField.setText("");
            textField.requestFocus();
        } catch (SocketException e) {
            ChatServer.log("Socket Exception:" + e);
        } catch (IOException e) {
            ChatServer.log("error:" + e);
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void updateTextArea(String message) {
        textArea.append(message);
        textArea.append("\n");
    }

    private static class ChatClientThread extends Thread {
        private Socket socket;

        public ChatClientThread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                String message = null;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                while (true) {
                    // 메시지 읽어서 채팅방에 쓰기
                    message = bufferedReader.readLine();
                    updateTextArea(message);
                }
            } catch (IOException e) {
                ChatClient.log("error:" + e);
            } finally {
                try {
                    if(socket != null && !socket.isClosed()) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
