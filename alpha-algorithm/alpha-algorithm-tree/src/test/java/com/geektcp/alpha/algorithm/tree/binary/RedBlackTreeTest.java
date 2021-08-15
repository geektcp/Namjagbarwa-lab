package com.geektcp.alpha.algorithm.tree.binary;

import alpha.common.base.util.FileUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author tanghaiyang on 2019/9/16.
 */
@Slf4j
public class RedBlackTreeTest {

    @Test
    public void RedBlackTree() {
        ArrayList<String> words = new ArrayList<>();
        words.add("1");
        words.add("1");
        words.add("1");
        words.add("2");
        words.add("3");
        words.add("4");
        words.add("5");
        words.add("6");
        words.add("77");
        words.add("4");
        RedBlackTree<String, Integer> redBlackTree = new RedBlackTree<>();
        for (String word : words) {
            if (redBlackTree.contains(word)) {
                redBlackTree.set(word, redBlackTree.get(word) + 1);
            } else {
                redBlackTree.add(word, 1);
            }
            redBlackTree.print();
            System.out.println("===========");
        }

        System.out.println("共有不同单词数：" + redBlackTree.getSize());
        System.out.println("出现4的次数: " + redBlackTree.get("4"));
        System.out.println("出现1的次数: " + redBlackTree.get("1"));
        Assert.assertTrue(true);
    }

    @Test
    public void RedBlackTreeFromFile() {
        ArrayList<String> words = new ArrayList<>();
        String path = FileUtils.getResourcePath();
        if (readFile(path + "/" + "pride-and-prejudice.txt", words)) {
            System.out.println("共有单词数：" + words.size());
            RedBlackTree<String, Integer> redBlackTree = new RedBlackTree<>();
            for (String word : words) {
                if (redBlackTree.contains(word)) {
                    redBlackTree.set(word, redBlackTree.get(word) + 1);
                } else {
                    redBlackTree.add(word, 1);
                }
            }
            redBlackTree.print();
            System.out.println("共有不同单词数：" + redBlackTree.getSize());
            System.out.println("出现pride的次数: " + redBlackTree.get("pride"));
            System.out.println("出现prejudice的次数: " + redBlackTree.get("prejudice"));
            System.out.println("出现license的次数: " + redBlackTree.get("license"));
            System.out.println("出现ebook的次数: " + redBlackTree.get("ebook"));
            System.out.println("出现sete的次数: " + redBlackTree.get("set"));
        }
        Assert.assertTrue(true);
    }


    /**
     * 读取一个文本文件所有单词，存入List
     *
     * @param filename 文件的绝对路径
     * @param words    结果集合
     * @return 是否读取成功
     */
    private static boolean readFile(String filename, ArrayList<String> words) {
        if (filename == null || words == null) {
            System.out.println("文件名或words不能为空");
            return false;
        }

        //文件读取
        Scanner scanner;
        try {
            File file = new File(filename);
            System.out.println(file.getAbsoluteFile());
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            } else {
                return false;
            }
        } catch (IOException ioe) {
            System.out.println("不能打开" + filename);
            return false;
        }

        /*
         * 简单分词
         * 这个分词方式相对简陋，没有考虑很多文本处理中的特殊问题
         * 这里只做demo展示用
         */
        if (scanner.hasNextLine()) {
            String contents = scanner.useDelimiter("\\A").next();
            int start = firstCharacterIndex(contents, 0);
            for (int i = start + 1; i <= contents.length(); ) {
                if (i == contents.length() || !Character.isLetter(contents.charAt(i))) {
                    String word = contents.substring(start, i).toLowerCase();
                    words.add(word);
                    start = firstCharacterIndex(contents, i);
                    i = start + 1;
                } else {
                    i++;
                }
            }
        }

        return true;
    }

    /**
     * 寻找字符串s中，从start的位置开始的第一个字母字符的位置
     *
     * @param s     目标字符串
     * @param start 寻找的起始位置
     * @return 从起始位置开始的第一个字母的位置
     */
    private static int firstCharacterIndex(String s, int start) {
        for (int i = start; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                return i;
            }
        }
        return s.length();
    }
}
