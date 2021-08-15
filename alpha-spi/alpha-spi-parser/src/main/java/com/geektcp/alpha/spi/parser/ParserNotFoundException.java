package com.geektcp.alpha.spi.parser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author tanghaiyang on 2020/1/14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ParserNotFoundException extends RuntimeException {

    private String code;

    private String message;

}
