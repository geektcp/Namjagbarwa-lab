package com.geektcp.alpha.agent.builder;

import com.geektcp.alpha.agent.constant.AgentMethod;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import static com.geektcp.alpha.agent.util.LogUtil.log;

/**
 * @author haiyang.tang on 12.05 005 9:42:02.
 */
public class HttpBuilder {

    private static final String GREETING = "# agent exporter";
    private static final String URL = "http://localhost:3300";
    private static final String URI = "/prometheus/metrics";

    private static final String WELCOME = GREETING + ": " + URL + URI;

    private HttpBuilder() {
    }

    public static void build() {
        AgentCacheBuilder.init();
        try (ServerSocket serverSocket = new ServerSocket(3300);) {
            log(WELCOME);
            long count = 0;
            while (count < Long.MAX_VALUE) {
                Socket socket = serverSocket.accept();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String url = getUrlFromBuffer(bufferedReader, stringBuilder);
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                printWriter.println("HTTP/1.1 200 OK");
                printWriter.println("Content-Type:text/plain;charset=utf-8");
                printWriter.println();
                if (!url.startsWith(URI)) {
                    count++;
                    socket.close();
                }
                String result = stringBuilder.toString();
                if (result.trim().length() == 0) {
                    continue;
                }
                response(printWriter);
                printWriter.close();
                socket.close();
                if (url.contains("clear")) {
                    AgentCacheBuilder.init();
                }
                count++;
            }
        } catch (Exception e) {
            log("Exception: " + e.getMessage());
        }
    }


    //////////////////////////////
    private static void response(PrintWriter printWriter) {
        printWriter.println(GREETING);
        List<String> metricSystemList = AgentCacheBuilder.listSystem();
        for (String metric : metricSystemList) {
            printWriter.println(metric.trim());
        }
        List<String> metricRequestList = AgentCacheBuilder.listCache();
        for (String metric : metricRequestList) {
            printWriter.println(metric.trim());
        }
    }

    private static String getUrlFromBuffer(BufferedReader bufferedReader, StringBuilder stringBuilder) {
        String url = "";
        try {
            String line = null;
            while ((line = bufferedReader.readLine()) != null && line.trim().length() > 0) {
                stringBuilder.append(line);
                if (line.contains(AgentMethod.METHOD_GET)) {
                    String[] headMethod = line.split(" ");
                    if (headMethod.length >= 2) {
                        url = headMethod[1];
                    }
                }
            }
        } catch (Exception e) {
            log(e.getMessage());
        }
        return url;
    }
}
