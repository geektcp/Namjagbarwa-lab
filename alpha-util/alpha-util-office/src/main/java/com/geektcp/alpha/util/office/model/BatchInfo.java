package com.geektcp.alpha.util.office.model;

import lombok.Data;

/**
 * @author haiyang on 3/27/20 10:31 AM.
 */
@Data
public class BatchInfo {
    private String batchNo;
    private String title;
    private byte[] bytes;
}
