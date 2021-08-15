package alpha.socket.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by HaiyangHome on 2018/11/24.
 */

@Slf4j
public class Client {
    public static void main(String[] args) throws Exception {
        for (int i = 3300; i < 10000; i++) {
            log.info("start check port: {}", i);
            Socket socket = new Socket();
            socket.setSoTimeout(11);
            socket.connect(new InetSocketAddress("geektcp.com", i), 200);
            log.info("port is opened: {}", i);
            socket.close();


        }
    }


    @Test
    public void connect() throws Exception {
        Socket socket = new Socket();
        socket.setSoTimeout(11);
        socket.connect(new InetSocketAddress("localhost", 999), 200);
        log.info("port is opened: {}", 999);
    }

    @Test
    public void connectNIO() throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream out = socket.getOutputStream();
        String s = "hello world";
        out.write(s.getBytes());
        out.close();
    }

}
