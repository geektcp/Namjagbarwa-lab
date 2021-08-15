package com.geektcp.alpha.game.box.panel;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.awt.*;

/**
 * @author tanghaiyang on 2019/9/29.
 * 每个块含有一个图片和一个类型,每个图片也有自己的类型
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class Block extends Rectangle {
    private ImageIcon icon;
    private int type;

    public Block(Point point, Dimension dimension, ImageIcon icon) {
        super(point, dimension);
        this.icon = icon;
        type = Integer.parseInt(icon.getDescription());
        if (BlockType.isMen(type) || BlockType.BOX == type) {
            type = BlockType.ROAD;
        } else if (BlockType.BOX_DONE == type) {
            type = BlockType.TERMINAL;
        }
    }

    public int getIconType() {
        return Integer.parseInt(icon.getDescription());
    }
}
