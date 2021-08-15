package com.geektcp.alpha.algorithm.tree.tree;

import lombok.Data;

import java.util.*;

/**
 * @author tanghaiyang on 2019/4/10.
 */
@Data
public class GraphNode {

    private String key;
    private String vertexId;
    private String edgeId;

    // 遍历次数
    private int traverse = 0;

    private boolean printed = false;

    private LinkedList<GraphNode> children = new LinkedList<>();

    private List<Stack<String>> pathList = new Stack<>();

    public GraphNode(String id){
        this.vertexId = id;
    }

    public void addPath(Stack<String> path){
        pathList.add(path);
    }

    public void addTraverse(){
        this.traverse++;
    }

}
