package com.geektcp.alpha.util.office.service;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
/**
 * @author haiyang on 3/26/20 2:22 PM.
 */
public class ReadWordService {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ReadWordService word = new ReadWordService();
        String data = word.readDocx("word.docx");
        System.out.println(data);
    }

    /**
     * 读取doc格式的word文档
     */
    public String readDoc(String path) {
        String suffix = path.substring(path.lastIndexOf("."));
        if (!suffix.equals(".doc")) {
            return "文件格式错误，请选择.doc格式文档";
        }
        File file = new File(path);
        FileInputStream read = null;
        String docText = "";
        try {
            read = new FileInputStream(file);
            WordExtractor result =  new WordExtractor(read);
            docText = result.getText();
            result.close();
            read.close();
        } catch (Exception e) {
            System.out.println("读取文件错误");
            e.printStackTrace();
        }
        return docText;
    }

    /**
     * 读取docx格式的word文档
     */
    public String readDocx(String path) {
        String suffix = path.substring(path.lastIndexOf("."));
        if(!suffix.equals(".docx")) {
            return "文件格式错误，请选择.docx格式文档";
        }
        File file = new File(path);
        FileInputStream read = null;
        XWPFWordExtractor result = null;
        try {
            read = new FileInputStream(file);
            XWPFDocument doc = new XWPFDocument(read);
            result = new XWPFWordExtractor(doc);
        } catch (Exception e) {
            System.out.println("读取文件错误");
            e.printStackTrace();
        }
        return result.getText();
    }
}
