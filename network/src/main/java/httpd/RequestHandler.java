package httpd;

import java.io.*;
import java.lang.annotation.Documented;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.AccessDeniedException;

public class RequestHandler extends Thread {
    private Socket socket;
    private static String path = RequestHandler.class.getResource("").getPath();
    private static String desiredPath = path.replace("/target/classes/httpd/", "/");
    private static final String DOCUMENT_ROOT = desiredPath + "webapp";

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

            InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
            consoleLog("connected from " + inetSocketAddress.getAddress().getHostAddress() + ":" + inetSocketAddress.getPort());

            String request = null;

            while (true) {
                String line = br.readLine();

                // 브라우저에서 연결을 끊으면...
                if (line == null) {
                    break;
                }

                // SimpleHttpServer 는 HTTP Header 만 처리
                if ("".equals(line)) {
                    break;
                }

                if (request == null) {
                    request = line;
                    break;
                }
            }
            // 요청 처리
            consoleLog(request);

            String[] tokens = request.split(" ");
            if ("GET".equals(tokens[0])) {
                getResponseStaticResource(outputStream, tokens[1], tokens[2]);
                System.out.println(tokens[1] + tokens[2]);
            } else {
                /* methods: POST, PUT, DELETE, HEAD, CONNECT
                 * SimpleHttpServer 에서는 무시(400 Bad Request)
                 *  */
                // 400 에러
                getResponse400BadRequest(outputStream, tokens[2]);
            }
        } catch (Exception ex) {
            consoleLog("error:" + ex);
        } finally {
            // clean-up
            try {
                if (socket != null && socket.isClosed() == false) {
                    socket.close();
                }

            } catch (IOException ex) {
                consoleLog("error:" + ex);
            }
        }
    }
    private static void getResponseStaticResource(OutputStream outputStream, String url, String protocol) throws IOException {
        // default(welcome) file set
        if ("/".equals(url)) {
            url = "/index.html";
        }

        File file = new File(DOCUMENT_ROOT + url);
        if (!file.exists()) {
            // 404 에러
            getResponse404Error(outputStream, protocol);
            return;
        }

        // nio
        byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());

		outputStream.write((protocol + "200 OK\r\n").getBytes("UTF-8"));
        outputStream.write(("Content-Type:" + contentType+"; charset=utf-8\r\n").getBytes("UTF-8"));
        outputStream.write("\r\n".getBytes());
        outputStream.write("<h1>이 페이지가 잘 보이면 실습과제 SimpleHttpServer를 시작할 준비가 된 것입니다.</h1>".getBytes("UTF-8"));
		outputStream.write(body);
    }

    private static void getResponse400BadRequest(OutputStream outputStream, String protocol) throws IOException {
        File file = new File(DOCUMENT_ROOT + "/error/400.html");
        if(!file.exists()) {
            getResponse404Error(outputStream, protocol);
            return;
        }

        byte[] body = Files.readAllBytes(file.toPath());
        String contentType = Files.probeContentType(file.toPath());

        outputStream.write((protocol + "400 Bad Request\r\n").getBytes());
        outputStream.write(("Content-Type:" + contentType+"; charset=utf-8\r\n").getBytes("UTF-8"));
        outputStream.write("\r\n".getBytes());
        outputStream.write(body);

    }

    private static void getResponse404Error(OutputStream outputStream, String protocol) throws IOException {
        File file = new File(DOCUMENT_ROOT + "/error/404.html");
        if(!file.exists()) {
            System.out.println("file not found:" + file.getAbsolutePath());
            return;
        }

        byte[] body = Files.readAllBytes(file.toPath());
        String contentType = Files.probeContentType(file.toPath());

        outputStream.write((protocol + "404 Bad Request\r\n").getBytes("UTF-8"));
        outputStream.write(("Content-Type:" + contentType+"; charset=utf-8\r\n").getBytes("UTF-8"));
        outputStream.write("\r\n".getBytes());
        outputStream.write(body);
    }

    public void consoleLog(String message) {
        System.out.println("[RequestHandler#" + getId() + "] " + message);
    }
}
