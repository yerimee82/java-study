package chat.gui;

import chat.ChatServer;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatClientApp {
    public static void main(String[] args) {
        String name = null;
        Scanner scanner = null;

        // 1. 키보드 연결
        scanner = new Scanner(System.in);

        // 5. join 프로토콜
        while (true) {
            System.out.print("닉네임>>");
            name = scanner.nextLine();

            if (!name.isEmpty()) {
                break;
            }
            System.out.println("닉네임은 한 글자 이상 입력해야 합니다.\n");
        }

        scanner.close();

        // 2. socket 생성
        Socket socket = new Socket();

        try {
            // 2-1. 호스트 IP 주소 받아오기
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            // 3. 연결
            socket.connect(new InetSocketAddress(hostAddress, ChatServer.PORT));

            // 4. 채팅 - GUI 오픈
            new ChatWindow(name, socket).show();

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            pw.println("join:"+name);

        } catch (SocketException e) {
            log(name + "님이 나가셨습니다.");
        } catch (IOException e) {
            log(name + "님이 나가셨습니다.");
        }
    }

    public static void log(String message) {
        System.out.println(message);
    }
}
