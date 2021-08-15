package com.geektcp.alpha.spi.parser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author tanghaiyang on 2020/1/14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    private String format;

    private String name;

    private String author;

    private long time;

}
