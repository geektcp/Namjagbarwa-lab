package com.geektcp.alpha.algorithm.tree.simple.tree1;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tanghaiyang on 2019/1/8.
 */
@Slf4j
public class AlphaTest {

    public static void main(String[] args) {

        SysResourceNodeVo node1 = new SysResourceNodeVo(11L, 0, "dddd", "一级资源");
        SysResourceNodeVo node2 = new SysResourceNodeVo(12L, 11, "dddd", "一级资源");
        SysResourceNodeVo node3 = new SysResourceNodeVo(13L, 12, "dddd", "二级资源");
        SysResourceNodeVo node4 = new SysResourceNodeVo(14L, 12, "dddd", "二级资源");
        SysResourceNodeVo node5 = new SysResourceNodeVo(15L, 14, "dddd", "三级资源");

        SysResourceTreeVo tree = new SysResourceTreeVo();
        tree.insertNode(node1);
        tree.insertNode(node2);
        tree.insertNode(node3);
        tree.insertNode(node4);
        tree.insertNode(node5);

        System.out.println(tree.toString());
    }

    @Test
    public void test2(){
        SysResourceNodeVo node1 = new SysResourceNodeVo(11L, 0, "dddd", "一级资源");
        SysResourceNodeVo node2 = new SysResourceNodeVo(12L, 11, "dddd", "一级资源");
        SysResourceNodeVo node3 = new SysResourceNodeVo(13L, 12, "dddd", "二级资源");
        SysResourceNodeVo node4 = new SysResourceNodeVo(14L, 12, "dddd", "二级资源");
        SysResourceNodeVo node5 = new SysResourceNodeVo(15L, 14, "dddd", "三级资源");
        List<SysResourceNodeVo> list = new LinkedList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);

        SysResourceTreeVo tree = new SysResourceTreeVo();

//        List<SysResourceNodeVo> list2 = Collections.unmodifiableList(list);
//        for(SysResourceNodeVo node: list2){
//            tree.insertNode(node);
//        }
//
//        Iterator<Map.Entry<String, Object>> it = monitorTable.entrySet().iterator();

        Iterator<SysResourceNodeVo> list2 = list.listIterator();
        list2.forEachRemaining(node->{
            tree.insertNode(node);
        });

        System.out.println(list.size());
        System.out.println(tree.toString());
    }


}
