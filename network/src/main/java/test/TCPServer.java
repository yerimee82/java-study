package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            //1. Server Socket 생성
            serverSocket = new ServerSocket();

            // 1.1 FIN-WAIT2 -> TIME_WAIT 상태에서도 소켓 포트 할당이 가능하도록 하기 위해
            serverSocket.setReuseAddress(true);

            // 2. 바인딩(binding)
            //  socket에 InetSocketAddress[InetAddress(IPAddress + Port)] 를 바인딩 한다.
            //  IPAddress: 0.0.0.0 : 특정 호스트 IP 를 바인딩 하지 않는다. -> 모든 가능한 IP 주소 허용
            serverSocket.bind(new InetSocketAddress("0.0.0.0", 5050));

            // 3. accept
            socket = serverSocket.accept();  // blocking

            try {
                InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
                String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
                int remotePort = inetRemoteSocketAddress.getPort();
                System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remotePort + "]");

                //4. IO Stream 받아오기
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                while (true) {
                    // System.out.println("try to read");
                    //5. 데이터 읽기
                    byte[] buffer = new byte[256];
                    int readByteCount = is.read(buffer); // blocking
                    if (readByteCount == -1) {
                        // closed by client
                        System.out.println("[server] closed by client");
                        break;
                    }

                    String data = new String(buffer, 0, readByteCount, "UTF-8");
                    System.out.println("[server] received:" + data);

                    // 6. 데이터 쓰기
                    // SO_TIMEOUT 테스트
//                    os.write(data.getBytes("utf-8"));
//
//					try {
//						Thread.sleep(3000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}

                    os.write(data.getBytes("utf-8"));
                }
            } catch (SocketException e) {
                System.out.println("[server] Socket Exception: " + e);
            } catch (IOException e) {
                System.out.println("[server] error:" + e);
            } finally {
                try {
                    if (socket != null && !socket.isClosed()) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
