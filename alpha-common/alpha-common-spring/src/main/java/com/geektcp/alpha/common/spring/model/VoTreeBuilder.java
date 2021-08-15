package com.geektcp.alpha.common.spring.model;

import alpha.common.base.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author tanghaiyang on 2019/1/10.
 */
public class VoTreeBuilder {

    private static <T extends BaseTreeNodeVo> boolean insertNode(T currentNode, T childNode) {
        if( currentNode.getId().equals(childNode.getParentId()) ){
            currentNode.add(childNode);
            return true;
        }
        if (Objects.nonNull(currentNode.getChildren())){
            currentNode.getChildren().forEach(currentChildNode ->{
                insertNode(currentChildNode, childNode);
            });
        }
        return false;
    }

    /*
    * para list will be modified when excute recursive inserting, so need deepcopy
    *
    * */
    @SuppressWarnings("all")
    public static <T extends BaseTreeNodeVo> T createTree(List<T> list, Class<T> clazz)
            throws IllegalAccessException, InstantiationException{
        T currentNode = clazz.newInstance();
        List<T> listCopy = (List<T>) ObjectUtils.deepCopy(list);
        if (Objects.nonNull(listCopy)) {
            listCopy.forEach(childNode -> {
                insertNode(currentNode, childNode);
            });
        }
        return currentNode;
    }

    public static <T extends BaseTreeNodeVo> List createTreeList(List<T> list, Class<T> clazz)
            throws IllegalAccessException, InstantiationException {
        return createTree(list, clazz).getChildren();
    }


}
