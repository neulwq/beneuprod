package com.beneu.beneuprod.core;

import com.alibaba.fastjson.JSON;
import com.beneu.beneuprod.core.model.AvlTree;
import com.beneu.beneuprod.core.model.AvlTreeNode;
import com.beneu.common.util.log.MessageFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/1/15 23:11
 */
@Slf4j
public class AvlTreeTest {

    @Test
    public void testLevelTreeNode() {
        AvlTree<Integer> tree = new AvlTree<Integer>();
        tree.add(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
        visitTree(tree);

        List<List<AvlTreeNode<Integer>>> wideList = tree.breadthLevelVisit();
        for (List<AvlTreeNode<Integer>> nodes : wideList) {
            log.info(MessageFormatUtil.msgFormat("level nodes={0}", JSON.toJSONString(nodes)));
        }

        wideList = tree.breadthLevelVisitWithFill();
        for (List<AvlTreeNode<Integer>> nodes : wideList) {
            log.info(MessageFormatUtil.msgFormat("level nodes={0}", JSON.toJSONString(nodes)));
        }

        log.info("\n" + tree.treeView());

        tree.add(25);
        tree.add(26);
        tree.add(27);
        tree.add(28);
        tree.add(29);
        tree.add(30);
        tree.add(31);



        log.info("\n" + tree.treeView());

    }

    /**
     * 遍历树
     *
     * @param tree
     */
    protected <T extends Comparable<T>> void visitTree(AvlTree<T> tree) {
        log.info(MessageFormatUtil.msgFormat("pre    ={0}", JSON.toJSONString(tree.preVisit())));
        log.info(MessageFormatUtil.msgFormat("mid    ={0}", JSON.toJSONString(tree.midVisit())));
        log.info(MessageFormatUtil.msgFormat("post   ={0}", JSON.toJSONString(tree.postVisit())));
        log.info(MessageFormatUtil.msgFormat("breadth={0}", JSON.toJSONString(tree.breadthVisit())));
        log.info(MessageFormatUtil.msgFormat("depth  ={0}", tree.height()));
    }
}
