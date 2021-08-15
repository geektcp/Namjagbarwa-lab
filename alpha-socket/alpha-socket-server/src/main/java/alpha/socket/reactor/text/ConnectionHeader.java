package alpha.socket.reactor.text;

import alpha.socket.reactor.comparator.Writable;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Slf4j
public class ConnectionHeader implements Writable {

    private String protocol;

    public ConnectionHeader() {

    }

    public ConnectionHeader(String protocol) {
        this.protocol = protocol;
    }

    public void readFields(DataInput in) throws IOException {
        protocol = Text.readString(in);
        if (protocol.isEmpty()) {
            protocol = null;
        } else {
            log.info("The protocol is: " + protocol);
        }
    }

    public void write(DataOutput out) throws IOException {
        Text.writeString(out, (protocol == null) ? "" : protocol);
    }
}
