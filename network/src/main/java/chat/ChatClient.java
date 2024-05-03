package chat;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        String nickname = null;
        Scanner scanner = null;
        Socket socket = null;
        String input;


        try {
            // 1. 키보드 연결
            scanner = new Scanner(System.in);
            // 2. socket 생성
            socket = new Socket();
            // 2-1. 호스트 IP 주소 받아오기
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            // 3. 연결
            socket.connect(new InetSocketAddress(hostAddress, ChatServer.PORT));

            // 4. reader/writer 생성
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

            // 5. join 프로토콜
            while (true) {
                System.out.print("닉네임>>");
                nickname = scanner.nextLine();

                if (!nickname.isEmpty()) {
                    pw.println("join:" + nickname);
                    pw.flush();
                    break;
                }
                System.out.println("닉네임은 한 글자 이상 입력해야 합니다.\n");
            }

            // 6. ChatClientThread 시작
            new ChatClientThread(socket).start();

            // 7. 키보드 입력 처리
            while (true) {
                input = scanner.nextLine();

                if ("quit".equals(input)) {
                    // 8. quit 프로토콜 처리
//                    log(nickname+"님이 채팅방을 나갔습니다.");
                    pw.println("quit:");
                    pw.flush();
                    break;
                } else {
                    // 9. 메시지 처리
                    pw.println("message:"+input);
                    pw.flush();
                }
            }

        } catch (SocketException e) {
            log(nickname + "님이 나가셨습니다.");

        } catch (IOException e) {
            log("error:" + e);
        } finally {
            try {
                // 10. 자원 정리
                assert scanner != null;
                scanner.close();
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                log("error:" + e);
            }
        }
    }

    public static void log(String message) {
        System.out.println(message);
    }


}
