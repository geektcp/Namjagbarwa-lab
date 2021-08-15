package alpha.socket.io;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by HaiyangHome on 2018/11/24.
 */

@Slf4j
public class Server {

    public static void main(String[] args) throws Exception {
        int port = 999;
        ServerSocket serverSocket = new ServerSocket(port);
        log.info("start accept ...");
        Socket connection = serverSocket.accept();
        log.info("accepting ...");
        InputStream ins = connection.getInputStream();
        Reader reader = new InputStreamReader(ins, "utf-8");
        BufferedReader br = new BufferedReader(reader);

//        StringBuffer sb = new StringBuffer();
//        for(int c = reader.read(); c!=-1; c=reader.read() ){
//            sb.append((char)c);
//
//            log.info("sb: {}", sb);
//        }

//        SelectionKey;
//        DefaultSelectorProvider

        String line = null;
        while (( line = br.readLine() ) != null){
            log.info("line: {}", line);

            if(line.equals("") |line.length()==0){
                break;
            }
        }

        OutputStream out =  connection.getOutputStream();
        Writer writer = new OutputStreamWriter(out, "UTF-8");

        String str = "sdfsdfsdfs";

        log.info(str);
        writer.write("3333333\r\n");
        writer.flush();

//        out.write((str ).getBytes() );
//        out.flush();

        Thread.sleep(3000);
        connection.close();
        serverSocket.close();

    }




}
