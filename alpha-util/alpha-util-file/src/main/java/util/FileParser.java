package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by HaiyangWork on 2018/12/19.
 */
public class FileParser {
    private static String[] fields = null;

    private FileParser() {
    }

    public static JSONObject listPath(String path) {
        JSONObject ret = new JSONObject();
        File rootDir = new File(path);
        File[] subDirs = rootDir.listFiles();

        if (Objects.isNull(subDirs)) {
            return null;
        }
        for (File subDir : subDirs) {
            File[] subDirsBetas = subDir.listFiles();
            String type = subDir.getName();

            if (ret.containsKey(type)) {
                JSONObject typeObject = ret.getJSONObject(type);
                if (Objects.isNull(subDirsBetas)) {
                    continue;
                }
                for (File subDirsBeta : subDirsBetas) {
                    String table = subDirsBeta.getName();
                    if (!typeObject.containsKey(table)) {
                        typeObject.put(table, new JSONArray());
                    }
                    JSONArray tableObject = typeObject.getJSONArray(table);

                    File[] files = subDirsBeta.listFiles();
                    if (Objects.isNull(files)) continue;
                    for (File file : files) {
//                    System.out.println(type + "|" + table + "|" + file.getAbsolutePath());
                        tableObject.add(file.getAbsolutePath());
                    }
                }
            } else {
                ret.put(type, new JSONObject());
            }

        }

        return ret;
    }


    public static JSONObject readLines(File file, String encoding, long pos, int num) {
        JSONObject result = new JSONObject();
        JSONArray lines = new JSONArray();
        FileType fileType = FileType.JSON;
        try (BufferedRandomAccessFile reader = new BufferedRandomAccessFile(file, "r")) {
            reader.seek(pos);
            if (pos == 0) {
                String firstLine = reader.readLine();
                try {
                    JSONObject lineJson = JSONObject.parseObject(firstLine);
                    fileType = FileType.JSON;
                    lines.add(lineJson);
                } catch (Exception e) {
                    fields = firstLine.split(",");
                    fileType = FileType.CSV;
                }
            }

            for (int i = 0; i < num; i++) {
                String line = reader.readLine();
                if (StringUtils.isBlank(line)) {
                    break;
                }

                JSONObject lineJson = null;
                if (fileType.equals(FileType.CSV)) {
                    lineJson = transfer(line);
                } else if (fileType.equals(FileType.JSON)) {
                    lineJson = JSONObject.parseObject(line);
                }
//                lines.add(new String(line.getBytes("8859_1"), encoding));
                lines.add(lineJson);
            }

            result.put("lines", lines);
            result.put("pos", reader.getFilePointer());
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // used when csv
    private static List<String> transfer(List<String> lines) {
        List<String> ret = new ArrayList<>();
        for (String line : lines) {
            String[] valuesArr = line.split(",");
            JSONObject lineJson = new JSONObject(true);
            for (int j = 0; j < valuesArr.length; j++) {
                lineJson.put(fields[j], valuesArr[j]);
            }
            ret.add(lineJson.toJSONString());
        }
        lines.clear();

        return ret;
    }

    // used when csv
    private static JSONObject transfer(String line) {
        String[] valuesArr = line.split(",");
        JSONObject lineJson = new JSONObject(true);
        for (int j = 0; j < valuesArr.length; j++) {
            lineJson.put(fields[j], valuesArr[j]);
        }

        return lineJson;
    }
}
