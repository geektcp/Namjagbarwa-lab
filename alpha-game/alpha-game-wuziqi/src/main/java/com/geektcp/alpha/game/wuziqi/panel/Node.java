package com.geektcp.alpha.game.wuziqi.panel;

import lombok.Data;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author haiyang on 2019/9/20.
 * 树节点
 */
@Data
public class Node {

    private Node bestChild = null;
    private ArrayList<Node> child = new ArrayList<>();
    private Point point = new Point();
    private int mark;

    Node() {
        this.child.clear();
        bestChild = null;
        mark = 0;
    }

//    public void setPoint(Point r) {
//        point.x = r.x;
//        point.y = r.y;
//    }

    public void addChild(Node r) {
        this.child.add(r);
    }

    public Node getLastChild() {
        return child.get(child.size() - 1);
    }

}
