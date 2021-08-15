package alpha.socket.reactor.buffer;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class DataOutputBuffer extends DataOutputStream {

    private static class Buffer extends ByteArrayOutputStream {

        byte[] getData() {
            return buf;
        }
        int getLength() {
            return count;
        }
        Buffer() {
            super();
        }
        Buffer(int size) {
            super(size);
        }

        public void write(DataInput in, int len) throws IOException {
            int newCount = count + len;
            if (newCount > buf.length) {
                byte[] newBuf = new byte[Math.max(buf.length << 1, newCount)];
                System.arraycopy(buf, 0, newBuf, 0, count);
                buf = newBuf;
            }
            in.readFully(buf, count, len);
            count = newCount;
        }
    }

    private Buffer buffer;

    public DataOutputBuffer() {
        this(new Buffer());
    }

    public DataOutputBuffer(int size) {
        this(new Buffer(size));
    }

    private DataOutputBuffer(Buffer buffer) {
        super(buffer);
        this.buffer = buffer;
    }

    public byte[] getData() {
        return buffer.getData();
    }

    public int getLength() {
        return buffer.getLength();
    }

    public DataOutputBuffer reset() {
        this.written = 0;
        buffer.reset();
        return this;
    }

    public void write(DataInput in, int length) throws IOException {
        buffer.write(in, length);
    }

    public void writeTo(OutputStream out) throws IOException {
        buffer.writeTo(out);
    }
}
