package echo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;


public class EchoRequestHandler extends Thread {
    private Socket socket;

    public EchoRequestHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
            String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
            int remotePort = inetRemoteSocketAddress.getPort();
            EchoServer.log("connected by client[" + remoteHostAddress + ":" + remotePort + "]");

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

            while (true) { // 메인 스레드 역할
                String data = br.readLine();  // blocking
                if (data == null) {
                    // 클라이언트가 정상적으로 종료(close() 호출)
                    EchoServer.log("closed by client");
                    break;
                }
                EchoServer.log("received:" + data);
                pw.println(data);
            }
        } catch (SocketException e) {
            EchoServer.log("Socket Exception: " + e);
        } catch (IOException e) {
            EchoServer.log("error:" + e);
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
}
