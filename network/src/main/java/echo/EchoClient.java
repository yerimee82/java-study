package echo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class EchoClient {
    private static final String SERVER_IP = "127.0.0.1";

    public static void main(String[] args) {
        Scanner scanner = null;
        Socket socket = null;

        try {
            scanner = new Scanner(System.in);
            socket = new Socket();

            socket.connect(new InetSocketAddress(SERVER_IP, EchoServer.PORT));

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

            while (true) {
                System.out.print(">>");
                String line = scanner.nextLine();
                if ("exit".equals(line)) {
                    break;
                }

                pw.println(line);
                String data = br.readLine();
                if (data == null) {
                    log("closed by server");
                    break;
                }
                System.out.println("<<" + data);
            }
        } catch (SocketException e) {
            log("Socket Exception: " + e);
        }
        catch (IOException e) {
            log("error" + e);
        } finally {
            try {
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

    private static void log(String message) {
        System.out.println("[EchoClient] " + message);
    }
}
