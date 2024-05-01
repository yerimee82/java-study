package echo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class EchoServer {
    public static final int PORT = 6000;
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            // 1. Server Socket 생성
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT), 10);

            // 3. accept
            Socket socket = serverSocket.accept();  // blocking


            try {
                InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
                String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
                int remotePort = inetRemoteSocketAddress.getPort();
                log("connected by client[" + remoteHostAddress + ":" + remotePort + "]");

                //4. IO Stream 받아오기
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

                while(true) {
                    String data = br.readLine();  // blocking
                    if(data == null) {
                        // 클라이언트가 정상적으로 종료(close() 호출)
                        log("closed by client");
                        break;
                    }

                    log("received:" + data);

                    pw.println(data);
                    os.write(data.getBytes("utf-8"));

                }
            } catch(SocketException e) {
                log("suddenly closed by client");
            } catch(IOException e) {
                log("error:" + e);
            } finally {
                try {
                    if(socket != null && !socket.isClosed()) {
                        socket.close();
                    }
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }


        } catch (IOException e) {
            log("error: " + e);
        } finally {
            try {
                if(serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    private static void log(String message) {
        System.out.println("[EchoServer] " +message);
    }
}
