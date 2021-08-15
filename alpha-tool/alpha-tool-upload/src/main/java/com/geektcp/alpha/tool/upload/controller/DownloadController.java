package com.geektcp.alpha.tool.upload.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author tanghaiyang on 2019/10/13 9:43.
 */
@RestController
@RequestMapping("/")
@Slf4j
public class DownloadController {

    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        response.setContentType("application/force-download");
        String fileName = "application.yml";
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        String resourcePath =  this.getClass().getResource("/").getPath();
        String path = resourcePath + fileName;
        File tempFile = new File(path);
        try (InputStream inputStream = new FileInputStream(tempFile)) {
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.flush();
            os.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}
