package com.geektcp.alpha.game.box.panel;

/**
 * @author tanghaiyang on 2019/9/29.
 */
public class BlockType{
    public static final Integer SPACE = 0;
    public static final Integer BORDER = 1;
    public static final Integer ROAD = 2;
    public static final Integer BOX = 3;
    public static final Integer TERMINAL = 4;
    public static final Integer MEN_DOWN = 5;
    public static final Integer MEN_LEFT = 6;
    public static final Integer MEN_RIGHT = 7;
    public static final Integer MEN_UP = 8;
    public static final Integer BOX_DONE = 9;

    public static boolean isMen(int type) {
        if(type==MEN_DOWN || type==MEN_LEFT ||
                type==MEN_RIGHT || type == MEN_UP)return true;
        return false;
    }

    public static boolean isBox(int type) {
        return type==BOX || type==BOX_DONE;
    }

    public static boolean isRoad(int type) {
        return type==ROAD || type==TERMINAL;
    }
}
