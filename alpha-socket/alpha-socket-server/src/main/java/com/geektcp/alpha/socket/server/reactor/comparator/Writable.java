package com.geektcp.alpha.socket.server.reactor.comparator;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface Writable {

    void write(DataOutput out) throws IOException;

    void readFields(DataInput in) throws IOException;
}
