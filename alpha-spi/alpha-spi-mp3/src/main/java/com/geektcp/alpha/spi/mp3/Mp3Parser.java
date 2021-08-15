package com.geektcp.alpha.spi.mp3;

import com.geektcp.alpha.spi.parser.ParserManager;
import com.geektcp.alpha.spi.parser.Song;
import com.geektcp.alpha.spi.parser.Parser;

import java.util.Arrays;

/**
 * @author tanghaiyang on 2020/1/14.
 */
public class Mp3Parser implements Parser {

    public final byte[] FORMAT = "MP3".getBytes();

    public final int FORMAT_LENGTH = FORMAT.length;

    static {
        try {
            ParserManager.registerParser(new Mp3Parser());
        } catch (Exception e) {
            throw new RuntimeException("Can't register parser!");
        }
    }

    @Override
    public Song parse(byte[] data) throws Exception {
        if (!isDataCompatible(data)) {
            throw new Exception("data format is wrong.");
        }
        //parse data by mp3 format type
        return new Song("刘千楚", "mp3", "《北京东路的日子》", 220L);
    }

    private boolean isDataCompatible(byte[] data) {
        byte[] format = Arrays.copyOfRange(data, 0, FORMAT_LENGTH);
        return Arrays.equals(format, FORMAT);
    }
}
