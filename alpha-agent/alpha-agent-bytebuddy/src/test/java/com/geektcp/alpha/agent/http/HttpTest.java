package com.geektcp.alpha.agent.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author haiyang.tang on 12.05 005 9:39:30.
 */
public class HttpTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {//一直监听，直到受到停止的命令
            Socket socket = null;
            try {
                socket = serverSocket.accept();//如果没有请求，会一直hold在这里等待，有客户端请求的时候才会继续往下执行
                // log
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));//获取输入流(请求)
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null
                        && !line.equals("")) {//得到请求的内容，注意这里作两个判断非空和""都要，只判断null会有问题
                    stringBuilder.append(line).append("<br>");
                }
                String result = stringBuilder.toString();
                System.out.println(result);
                // echo
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);//这里第二个参数表示自动刷新缓存
                printWriter.println("HTTP/1.1 200 OK");
                printWriter.println("Content-Type:text/plain;charset=utf-8");
                printWriter.println();

                printWriter.println("你刚才发送的请求数据是：");
                printWriter.write(result);//将日志输出到浏览器
                // release
                printWriter.close();
                bufferedReader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
