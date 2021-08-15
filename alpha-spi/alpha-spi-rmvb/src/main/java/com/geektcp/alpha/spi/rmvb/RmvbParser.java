package com.geektcp.alpha.spi.rmvb;

import com.geektcp.alpha.spi.parser.Parser;
import com.geektcp.alpha.spi.parser.ParserManager;
import com.geektcp.alpha.spi.parser.Song;

import java.util.Arrays;

/**
 * @author tanghaiyang on 2020/1/14.
 */
public class RmvbParser implements Parser {

    public final byte[] FORMAT = "RMVB".getBytes();

    public final int FORMAT_LENGTH = FORMAT.length;

    static {
        try {
            ParserManager.registerParser(new RmvbParser());
        } catch (Exception e) {
            throw new RuntimeException("Can't register parser!");
        }
    }

    @Override
    public Song parse(byte[] data) throws Exception {
        if (!isDataCompatible(data)) {
            throw new Exception("data format is wrong.");
        }
        //parse data by rmvb format type
        return new Song("AGA", "rmvb", "《Wonderful U》", 240L);
    }

    private boolean isDataCompatible(byte[] data) {
        byte[] format = Arrays.copyOfRange(data, 0, FORMAT_LENGTH);
        return Arrays.equals(format, FORMAT);
    }
}