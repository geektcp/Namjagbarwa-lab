package alpha.socket.reactor.text;

import alpha.socket.reactor.comparator.Writable;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Slf4j
public class ConnectionBody implements Writable {

    private String body;

    public ConnectionBody() {

    }

    public ConnectionBody(String body) {
        this.body = body;
    }

    public void readFields(DataInput in) throws IOException {
        body = Text.readString(in);
        if (body.isEmpty()) {
            body = null;
        } else {
            log.info("The body is: " + body);
        }
    }

    public void write(DataOutput out) throws IOException {
        Text.writeString(out, (body == null) ? "" : body);
    }

    public String getBody() {
        return body;
    }

    public String toString() {
        return body;
    }
}
