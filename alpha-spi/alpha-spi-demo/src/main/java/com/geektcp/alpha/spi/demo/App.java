package com.geektcp.alpha.spi.demo;

import com.geektcp.alpha.spi.parser.ParserManager;
import com.geektcp.alpha.spi.parser.Song;

/**
 * @author tanghaiyang on 2020/1/14.
 */
public class App {
    /**
    * Every time will scan all implement class
    * */
    public static void main(String[] args) {
//        testMp3Parser();    //success, scan Mp3Parser,Mp4Parser
        testMp4Parser();    //success, scan Mp3Parser,Mp4Parser
//        testRmvbParser();   //success, scan Mp3Parser,Mp4Parser,RmvbParser
    }

    /**
     * !! may throw ParserNotFoundException
     */
    public static void testRmvbParser() {
        Song song = ParserManager.getSong(mockSongData("RMVB"));
        System.out.println("------------------------");
        System.out.println("Name:" + song.getName());
        System.out.println("Author:" + song.getAuthor());
        System.out.println("Time:" + song.getTime());
        System.out.println("Format:" + song.getFormat());
    }

    public static void testMp4Parser() {
        Song song = ParserManager.getSong(mockSongData("MP4"));
        System.out.println("------------------------");
        System.out.println("Name:" + song.getName());
        System.out.println("Author:" + song.getAuthor());
        System.out.println("Time:" + song.getTime());
        System.out.println("Format:" + song.getFormat());
    }

    public static void testMp3Parser() {
        Song song = ParserManager.getSong(mockSongData("MP3"));
        System.out.println("------------------------");
        System.out.println("Name:" + song.getName());
        System.out.println("Author:" + song.getAuthor());
        System.out.println("Time:" + song.getTime());
        System.out.println("Format:" + song.getFormat());
    }

    /**
     * 制造歌曲数据
     *
     * @param formatType
     * @return
     */
    private static byte[] mockSongData(String formatType) {
        return new String(formatType).getBytes();
    }
}