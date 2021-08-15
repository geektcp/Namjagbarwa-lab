package com.geektcp.alpha.tool.upload.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * @author tanghaiyang on 2019/10/13 9:43.
 */
@Controller
@Slf4j
public class UploadController {

    private static final String SAVE_PATH = "F:\\tmp\\upload";

    @PostMapping("/upload")
    @ResponseBody
    public JSONObject upload(MultipartFile file, String name) {
        try {
            File destFile = new File(SAVE_PATH, name);
            boolean isRead = destFile.setReadable(true);
            boolean isWrite = destFile.setWritable(true);
            boolean isExecute = destFile.setExecutable(true);
            boolean isRWE= isRead && isWrite && isExecute;
            if(!isRWE){
                log.error("no permission!");
            }
           boolean isNewFile =  destFile.createNewFile();
            if(isNewFile) {
                file.transferTo(destFile);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        JSONObject result = new JSONObject();
        result.put("msg","successful");
        return result;
    }
}
