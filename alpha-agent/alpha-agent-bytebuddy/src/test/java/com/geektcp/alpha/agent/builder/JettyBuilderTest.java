package com.geektcp.alpha.agent.builder;//package com.geektcp.alpha.agent.builder;
//
///**
// * @author haiyang.tang on 11.27 027 18:05:22.
// */
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.servlet.ServletContextHandler;
//import org.eclipse.jetty.servlet.ServletHolder;
//
//import java.io.IOException;
//
//public class JettyBuilderTest {
//
//
//    public static void main(String[] args) throws Exception {
//
//        Server server = new Server(33000);
//        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        context.setContextPath("/");
//        server.setHandler(context);
//        context.addServlet(new ServletHolder(new HelloServlet()), "/*");
//        server.start();
//        server.join();
//    }
//}
//
//class HelloServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private String greeting = "agent jetty server!";
//
//    HelloServlet() {
//    }
//
//    public HelloServlet(String greeting) {
//        this.greeting = greeting;
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/plain");
//        response.setStatus(HttpServletResponse.SC_OK);
//        response.getWriter().println(greeting);
//        response.getWriter().println("session=" + request.getSession(true).getId());
//    }
//
//}
