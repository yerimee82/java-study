package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatClientThread extends Thread {
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
                System.out.println(message);
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
