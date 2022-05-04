package com.beneu.beneuprod.core;

import com.alibaba.fastjson.JSON;
import com.beneu.beneuprod.core.model.BinaryTree;
import com.beneu.common.util.log.MessageFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2020/8/7 22:57
 */
@Slf4j
public class TreeTest {

    @Test
    public void testTreeNode() {
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.add(new Integer[] {9, 5,3,2,4,7,6,8,13,11,10,12,15,14,16});
        visitTree(tree);
        log.info("镜像操作");
        //反转
        tree.breadthMirror();
        visitTree(tree);
        log.info("再镜像操作");
        //再反转
        tree.recursiveMirror();
        visitTree(tree);

    }

    /**
     * 遍历树
     *
     * @param tree
     */
    protected <T extends Comparable<T>> void visitTree(BinaryTree<T> tree) {
        log.info(MessageFormatUtil.msgFormat("pre    ={0}", JSON.toJSONString(tree.preVisit())));
        log.info(MessageFormatUtil.msgFormat("mid    ={0}", JSON.toJSONString(tree.midVisit())));
        log.info(MessageFormatUtil.msgFormat("post   ={0}", JSON.toJSONString(tree.postVisit())));
        log.info(MessageFormatUtil.msgFormat("breadth={0}", JSON.toJSONString(tree.breadthVisit())));
        log.info(MessageFormatUtil.msgFormat("depth  ={0}", tree.height()));
    }

}
