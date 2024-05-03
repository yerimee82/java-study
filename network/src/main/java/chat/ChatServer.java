package chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    public static final int PORT = 7000;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        List<Writer> listWriters = new ArrayList<>();

        try {
            // 1. 서버 소켓 생성
            serverSocket = new ServerSocket();

            // 2. 바인딩(binding)
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
            log("연결 기다림 " + hostAddress + ":" + PORT);

            // 3. 요청 대기  -- 반복문을 통해 여러 대의 클라이언트 서버 요청 대기
            while (true) {
                socket = serverSocket.accept();
                // 각 스레드 생성하여 연결
                new ChatServerThread(socket, listWriters).start();
            }

        } catch (IOException e) {
            log("error:" + e);
        } finally {
            try {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void log(String message) {
        System.out.println("[ChatServer] " + message);
    }
}
