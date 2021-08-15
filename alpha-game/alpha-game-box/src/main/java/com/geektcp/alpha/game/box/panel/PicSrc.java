package com.geektcp.alpha.game.box.panel;

import alpha.common.base.util.FileUtils;

import javax.swing.*;

/**
 * @author tanghaiyang on 2019/9/29.
 * 初始化所有图片;
 */
public class PicSrc{

    private static final String path = FileUtils.getResourcePath() + "/img/";
    private static final int picNum = 10;

    private static PicSrc singlePicSrc = null;
    private static ImageIcon[] img;

    private PicSrc(){
        img = new ImageIcon[picNum];
        for(int i = 0;i<picNum;i++){
            img[i] = new ImageIcon(path + i + ".gif",Integer.toString(i));
        }
        singlePicSrc = this;
    }

    public static PicSrc getInstance() {
        if(singlePicSrc == null){
            new PicSrc();
        }
        return singlePicSrc;
    }

    public ImageIcon getImgByIndex(int index) {
        return img[index];
    }
}
