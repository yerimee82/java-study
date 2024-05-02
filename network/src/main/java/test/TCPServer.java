package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            //1. Server Socket 생성
            serverSocket = new ServerSocket();

            // 2. 바인딩(binding)
            //  socket에 InetSocketAddress[InetAddress(IPAddress + Port)] 를 바인딩 한다.
            //  IPAddress: 0.0.0.0 : 특정 호스트 IP 를 바인딩 하지 않는다. -> 모든 가능한 IP 주소 허용
            serverSocket.bind(new InetSocketAddress("0.0.0.0", 5050));

            // 3. accept
            socket = serverSocket.accept();    // blocking

            InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
            String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
            int remotePort = inetRemoteSocketAddress.getPort();
            System.out.println("[server] conneted by client [" + remoteHostAddress + ":" + remotePort + "]");

            // 4. IO Stream 받아오기
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            while (true) {
                System.out.println("try to read");
                // 5. 데이터 읽기
                byte[] buffer = new byte[256];
                int readByteCount = is.read(buffer);    // blocking
                if (readByteCount == -1) {
                    // Connection Reset -- closed by client
                    System.out.println("[server] closed by client");
                    break;
                }

                String data = new String(buffer, 0, readByteCount, StandardCharsets.UTF_8);
                System.out.println("[server] received:" + data);

                // 6. 데이터 쓰기
                os.write(data.getBytes(StandardCharsets.UTF_8));

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                os.write(data.getBytes("utf-8"));
            }

        } catch (SocketException e) {
            System.out.println("[server] SocketException :" + e);
        } catch (IOException e) {
            System.out.println("[server] error: " + e);
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
