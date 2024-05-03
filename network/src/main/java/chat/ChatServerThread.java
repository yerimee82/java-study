package chat;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ChatServerThread extends Thread {
    private String nickname;
    private Socket socket;

    private final List<Writer> listWriters;

    public ChatServerThread(Socket socket, List<Writer> listWriters) {
        this.socket = socket;
        this.listWriters = listWriters;
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;

        try {
            // 1. Remote Host Information
            InetSocketAddress remoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
            String hostAddress = remoteSocketAddress.getAddress().getHostAddress();
            int port = remoteSocketAddress.getPort();
            ChatServer.log("connected by client[" + hostAddress + ":" + port + "]");

            // 2. 스트림 얻기
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

            // 3. 요청 처리
            while (true) { // 메인 스레드 역할
                String request = bufferedReader.readLine();
                if (request == null) {
                    ChatServer.log("클라이언트로 부터 연결 끊김");
                    doQuit(printWriter);
                    break;
                }

                // 4. 프로토콜 분석
                System.out.println(request);
                String[] tokens = request.split(":");
                for (String text :
                        tokens) {
                    System.out.println(text);
                }

                if ("join".equals(tokens[0])) {
                    doJoin(tokens[1], printWriter);
                } else if ("message".equals(tokens[0])) {
                    doMessage(tokens[1]);
                } else if ("quit".equals(tokens[0])) {
                    doQuit(printWriter);
                    break;
                } else {
                    ChatServer.log("에러:알수 없는 요청(" + tokens[0] + ")");
                }
            }
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

    private void doJoin(String nickname, Writer writer) {
        this.nickname = nickname;
        String data = nickname + "님이 입장하셨습니다. 즐거운 채팅 되세요.";

        broadcast(data);

        /* writer pool 에 저장 */
        addWriter(writer);

        // ack
//        PrintWriter printWriter = (PrintWriter) writer;
//        printWriter.println("join:ok");
//        printWriter.flush();
    }

    private void doMessage(String message) {
        String data = nickname + ": " + message;
        broadcast(data);
    }

    private void doQuit(Writer writer) {
        removeWriter(writer);

        String data = nickname + "님이 퇴장 하였습니다.";
        broadcast(data);
    }

    private void addWriter(Writer writer) {
        synchronized (listWriters) {
            listWriters.add(writer);
        }
    }

    private void removeWriter(Writer writer) {
        synchronized (listWriters) {
            listWriters.remove(writer);
        }
    }

    private void broadcast(String data) {
        synchronized (listWriters) {
            for (Writer writer : listWriters) {
                PrintWriter printWriter = (PrintWriter) writer;
                printWriter.println(data);
                printWriter.flush();
            }
        }
    }
}
