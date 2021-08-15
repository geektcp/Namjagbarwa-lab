package com.geektcp.alpha.algorithm.tree.tree;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author tanghaiyang on 2019/4/10.
 *
 * 造一棵树用于图查询结果的遍历
 * arangodb的返回结果中可能带有重复节点，因为结果树中不同的方向的顶点可能和其他方向的顶点最终会重合
 * 这样的节点在这棵树里面，把它当成属性完全相同的不同节点就可以了
 * 最后寻找最短路径或者全路径，只需要判断终点的id是否是目的id即可
 */
@Slf4j
public class GraphNodeTest {
    /*
    * path: 1,2,5,7
    * */
    @Test
    public void test() {
        GraphNode graphNodeRoot = buildTree();
//        GraphNode graphNodeRoot = buildTree2();
//        GraphNode graphNodeRoot = buildTree3();

        JSONObject ret = new JSONObject();
        this.toString(graphNodeRoot, ret);
        log.info(JSON.toJSONString(ret, SerializerFeature.PrettyFormat));

        Stack<String> path = new Stack<>();
        GraphNode dst = new GraphNode("vertex/13");
        int depth = 5;

//        traverseDepthFirst(graphNodeRoot,path, dst, depth);

        //相当于5层展开
        traverseBreadthFirst(Lists.newArrayList(graphNodeRoot), path, dst, depth);

        printPath(graphNodeRoot);
    }

    /*
    * 深度优先遍历
    * */
    private boolean traverseDepthFirst(GraphNode node, Stack<String> path, GraphNode to, int depth) {
        if(depth==0) return true;
        depth--;
        path.push(node.getVertexId());
        Stack<String> currentPath = new Stack<>();
        currentPath.addAll(path);
        node.addPath(currentPath);
        LinkedList<GraphNode> children = node.getChildren();
        for(GraphNode child:children){
            traverseDepthFirst(child, path, to,depth);
            path.pop();    //同一根节点的兄弟节点之间遍历要出栈
        }
        node.addTraverse();
        return true;
    }

    /*
    * 广度优先遍历
    * */
    private boolean traverseBreadthFirst(List<GraphNode> nodeList, Stack<String> path, GraphNode to, int depth) {
        if(depth==0) return true;
        depth--;
        for(GraphNode node:nodeList){
            path.push(node.getVertexId());
            Stack<String> currentPath = new Stack<>();
            currentPath.addAll(path);
            node.addPath(currentPath);
            node.addTraverse();
        }
        for(GraphNode node:nodeList){
            LinkedList<GraphNode> children = node.getChildren();
            traverseBreadthFirst(children, path, to, depth);
            path.pop();    //同一根节点的兄弟节点之间遍历要出栈
        }
        return true;
    }

    /*
    * 0,1,2,4
    * 0,1,2,5,7,8
    * 0,1,2,5,7,9,10,13
    * 0,1,2,6
    * 0,1,3,10,13
    * */
    private GraphNode buildTree() {
        GraphNode graphNodeRoot = new GraphNode("vertex/0");

        GraphNode graphNode1 = new GraphNode("vertex/1");
        GraphNode graphNode2 = new GraphNode("vertex/2");
        GraphNode graphNode3 = new GraphNode("vertex/3");
        GraphNode graphNode4 = new GraphNode("vertex/4");
        GraphNode graphNode5 = new GraphNode("vertex/5");

        GraphNode graphNode6 = new GraphNode("vertex/6");
        GraphNode graphNode7 = new GraphNode("vertex/7");
        GraphNode graphNode8 = new GraphNode("vertex/8");
        GraphNode graphNode9 = new GraphNode("vertex/9");
        GraphNode graphNode10 = new GraphNode("vertex/10");
        GraphNode graphNode11 = new GraphNode("vertex/11");
        GraphNode graphNode12 = new GraphNode("vertex/12");
        GraphNode graphNode13 = new GraphNode("vertex/13");

        graphNodeRoot.getChildren().add(graphNode1);
        graphNode1.getChildren().add(graphNode2);
        graphNode1.getChildren().add(graphNode3);

        graphNode2.getChildren().add(graphNode4);
        graphNode2.getChildren().add(graphNode5);
        graphNode2.getChildren().add(graphNode6);

        graphNode5.getChildren().add(graphNode7);
//
        graphNode7.getChildren().add(graphNode8);
        graphNode7.getChildren().add(graphNode9);

        graphNode9.getChildren().add(graphNode10);
//        graphNode9.getChildren().add(graphNode11);

        graphNode11.getChildren().add(graphNode12);
        graphNode11.getChildren().add(graphNode13);

        graphNode3.getChildren().add(graphNode10);
        graphNode10.getChildren().add(graphNode13);

        ///// 禁止循环插入，遍历时导致死循环 /////
//        graphNode5.getChildren().add(graphNode2);

        return graphNodeRoot;
    }

    /*
    * 0,1,4,5
    * 0,2
    * */
    private GraphNode buildTree2() {
        GraphNode graphNodeRoot = new GraphNode("vertex/0");
        GraphNode graphNode1 = new GraphNode("vertex/1");
        GraphNode graphNode2 = new GraphNode("vertex/2");
        GraphNode graphNode4 = new GraphNode("vertex/4");
        GraphNode graphNode5 = new GraphNode("vertex/5");
        graphNodeRoot.getChildren().add(graphNode1);
        graphNodeRoot.getChildren().add(graphNode2);
        graphNode1.getChildren().add(graphNode4);
        graphNode4.getChildren().add(graphNode5);
        return graphNodeRoot;
    }

    private GraphNode buildTree3() {
        GraphNode graphNodeRoot = new GraphNode("vertex/0");
        GraphNode graphNode1 = new GraphNode("vertex/1");
        GraphNode graphNode2 = new GraphNode("vertex/2");
        graphNodeRoot.getChildren().add(graphNode1);
        graphNodeRoot.getChildren().add(graphNode2);
        return graphNodeRoot;
    }

    private void toString(GraphNode node, JSONObject ret){
        ret.put(node.getVertexId(), new JSONObject());
        LinkedList<GraphNode> children = node.getChildren();
        children.forEach(child ->{
            toString(child, ret.getJSONObject(node.getVertexId()));
        });
    }

    private void printPath(GraphNode graphNode){
        List<Stack<String>> pathList = graphNode.getPathList();
        LinkedList<GraphNode> children = graphNode.getChildren();
        if(graphNode.isPrinted()) return;
//        log.info("++++++++++++++++vid: {} | path size: {}", graphNode.getVertexId(),pathList.size());
        if(children.isEmpty()) {
            log.info("");
            log.info("vid: {} | pathList.size: {}", graphNode.getVertexId(), pathList.size());
            pathList.forEach(path -> {
                log.info("path: {}", path);
                graphNode.setPrinted(true);
            });
        }else {
            children.forEach(this::printPath);
        }
    }

}
