package com.geektcp.alpha.socket.server;

import com.geektcp.alpha.socket.server.reactor.buffer.DataOutputBuffer;
import com.geektcp.alpha.socket.server.reactor.text.ConnectionBody;
import com.geektcp.alpha.socket.server.reactor.text.ConnectionHeader;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Unit test for simple Application.
 */
public class ApplicationTest extends TestCase {
    private Socket socket;

    public ApplicationTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ApplicationTest.class);
    }

    public void testApp() {
        assertTrue(true);

        try {
        	while (true) {
        		try {
        			socket = new Socket();
                	SocketAddress remoteAddr = new InetSocketAddress("127.0.0.1", 8090);
                	socket.connect(remoteAddr, 60000);
                	socket.setTcpNoDelay(false);
        			break;
        		} catch (IOException ie) {
        		    if (socket != null) {
        		        try {
        		            socket.close();
        		        } catch (IOException e) {
        		        	e.printStackTrace();
        		        }
        		    }

        		    socket = null;

        		    try {
        		      Thread.sleep(1000);
        		    } catch (InterruptedException ignored) {

        		    }
        		}
        	}

            DataInputStream in = new DataInputStream(
            		new BufferedInputStream(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(
            		new BufferedOutputStream(socket.getOutputStream()));

            DataOutputBuffer buf = new DataOutputBuffer();
            ConnectionHeader header = new ConnectionHeader("TLV");
            header.write(buf);

            int bufLen = buf.getLength();
            out.writeInt(bufLen);
            out.write(buf.getData(), 0, bufLen);
            out.flush();

            DataOutputBuffer buf2 = new DataOutputBuffer();
            ConnectionBody writeBody = new ConnectionBody("are you ok!!!");
            writeBody.write(buf2);

            bufLen = buf2.getLength();
            out.writeInt(bufLen);
            out.write(buf2.getData(), 0, bufLen);
            out.flush();

            ConnectionBody readBody = new ConnectionBody();
            readBody.readFields(in);
			out.close();
			in.close();
			socket.close();
			System.out.println("test over!!!");
		}catch (IOException e) {
			e.printStackTrace();
		}
    }
}
