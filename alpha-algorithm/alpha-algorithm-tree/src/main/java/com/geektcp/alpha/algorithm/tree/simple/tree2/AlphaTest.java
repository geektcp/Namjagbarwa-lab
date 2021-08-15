package com.geektcp.alpha.algorithm.tree.simple.tree2;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tanghaiyang on 2019/1/11.
 */
@Slf4j
public class AlphaTest {

    @Test
    public void test(){
        SysResourceVo node1 = new SysResourceVo(11L, 0L, "aaaaaa", "一级资源", "http://xxxx");
        SysResourceVo node2 = new SysResourceVo(21L, 0L, "bbbbbb", "一级资源", "http://xxxx");

        SysResourceVo node3 = new SysResourceVo(12L, 11L, "cccccc", "二级资源", "http://xxxx");
        SysResourceVo node4 = new SysResourceVo(14L, 21L, "dddddd", "二级资源", "http://xxxx");

        SysResourceVo node5 = new SysResourceVo(15L, 14L, "eeeeeee", "四级资源", "http://xxxx");
        SysResourceVo node6 = new SysResourceVo(13L, 12L, "ffffffff", "三级资源", "http://xxxx");

        List<SysResourceVo> list = new ArrayList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        list.add(node6);

//        BaseTreeNodeVo retTree = VoTreeBuilder.createTree(list);
//        System.out.println("+++++++++++ retTree +++++++++++++++++++++");
//        LOG.info(retTree);

        List<SysResourceVo> listTree = VoTreeBuilder.createTreeList(list);
        System.out.println("++++++++++++ listTree ++++++++++++++++++++: " + list.size());
        log.info(listTree.toString());
    }


    @Test
    public void cloneTest() {
        ArrayList<Integer> a1 = new ArrayList<Integer>();
        a1.add(1);
        a1.add(10);

        ArrayList<Integer> a2 = new ArrayList<Integer>();
        a2.add(21);
        a2.add(20);

        ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
        a.add(a1);a.add(a2);

        ArrayList<ArrayList<Integer>> b = (ArrayList<ArrayList<Integer>>) a.clone();

        List<String> bb = new LinkedList<>();

        try {

            List<String> cc = new ArrayList<>();
            cc.add("ddddd");
//            List<String> dd = cc.clone();
            List<String> ddd = (List)((LinkedList )cc).clone();

            Integer stJack = new Integer(13);
            Integer stTom = new Integer(15);

            List<Integer> ee = new ArrayList<>();
            ee.add(stJack);
            ee.add(stTom);
            List<Integer> listCopy = (List<Integer>)((ArrayList)ee).clone();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
