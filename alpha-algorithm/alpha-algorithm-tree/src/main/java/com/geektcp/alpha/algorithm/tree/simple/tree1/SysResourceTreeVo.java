package com.geektcp.alpha.algorithm.tree.simple.tree1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Objects;

/**
 * @author tanghaiyang on 2019/1/8.
 */
public class SysResourceTreeVo {

    private SysResourceNodeVo root;

    public SysResourceTreeVo() {
        root = null;
    }

    public void insertNode(SysResourceNodeVo childNode) {
        if (root == null) {
            root = new SysResourceNodeVo();
        }
        SysResourceNodeVo currentNode = root;
        addNode(currentNode, childNode);
    }

    private boolean addNode(SysResourceNodeVo currentNode, SysResourceNodeVo childNode) {
        if( currentNode.id == childNode.parentId ){
            currentNode.add(childNode);
            return true;
        }
        if (Objects.nonNull(currentNode.childList)){
            for(int i = 0; i< currentNode.childList.size(); i++){
                SysResourceNodeVo currentChildNode  = currentNode.childList.get(i);
                addNode(currentChildNode, childNode);
            }
        }
        return false;
    }

    public SysResourceNodeVo getRoot(){
        return root;
    }

    public String toString(){
        return JSON.toJSONString(root, SerializerFeature.PrettyFormat);
    }

}
