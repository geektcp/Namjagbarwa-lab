package com.geektcp.alpha.game.box.panel;

import alpha.common.base.util.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanghaiyang on 2019/9/29.
 *         读入地图数据
 */
public class Map {
    private static String path = FileUtils.getResourcePath() + "/map/";
    private List<String> mapData;

    public Map(String mapName) {
        BufferedReader stream;
        try {
            stream = new BufferedReader(new FileReader(new File(path + mapName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String s = null;
        mapData = new ArrayList<>();
        try {
            while ((s = stream.readLine()) != null) {
                mapData.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<String> getMapData() {
        return mapData;
    }

    public static List<String> creatMapData(Block[] blocks, int row, int column) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            String data = "";
            for (int j = 0; j < column; j++) {
                data += blocks[i * column + j].getIconType();
            }
            res.add(data);
        }
        return res;
    }

    public static List<String> getAllMapName() {
        File[] files = new File(path).listFiles();
        List<String> res = new ArrayList<>();
        for (File file : files) {
            res.add(file.getName());
        }
        return res;
    }
//	public static void main(String[] args) {
//		List<String> tList  = new Map("1.map").getMapData();
//		for (String string : tList) {
//			System.out.println(string);
//		}
//	}
}
