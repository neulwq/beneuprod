package com.beneu.beneuprod.core;

import com.alibaba.fastjson.JSON;
import com.beneu.beneuprod.core.model.TreeNode;
import com.beneu.common.util.log.MessageFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2020/8/7 22:57
 */
@Slf4j
public class TreeNodeTest {

    @Test
    public void testTree() {
        TreeNode<Long> tree = buildTree();
        visitTree(tree);
        log.info("镜像操作");
        //反转
        tree.breadthMirror();
        visitTree(tree);
        log.info("再镜像操作");
        //再反转
        tree.noBreadthMirror();
        visitTree(tree);

        log.info("添加元素");
        TreeNode<Long> treeNode = new TreeNode<Long>(5L);
        TreeNode<Long> leftNode = treeNode.add(3L);
        leftNode.add(2L);
        leftNode.add(4L);
        TreeNode<Long> rightNode = treeNode.add(7L);
        rightNode.add(6L);
        rightNode.add(8L);
        visitTree(treeNode);

    }

    /**
     * 遍历树
     *
     * @param tree
     */
    protected void visitTree(TreeNode<Long> tree) {
        log.info(MessageFormatUtil.msgFormat("pre    ={0}", JSON.toJSONString(tree.preVisit())));
        log.info(MessageFormatUtil.msgFormat("mid    ={0}", JSON.toJSONString(tree.midVisit())));
        log.info(MessageFormatUtil.msgFormat("post   ={0}", JSON.toJSONString(tree.postVisit())));
        log.info(MessageFormatUtil.msgFormat("breadth={0}", JSON.toJSONString(tree.breadthVisit())));
        log.info(MessageFormatUtil.msgFormat("depth  ={0}", tree.getDepth()));
    }

    protected TreeNode<Long> buildTree() {
        TreeNode<Long> tree = new TreeNode<Long>(5L);
        tree.setLeft(buildTree(3L, 2L, 4L));
        tree.setRight(buildTree(7L, 6L, 8L));
        return tree;
    }

    /**
     * 构造子树
     *
     * @param value
     * @param leftValue
     * @param rightValue
     * @return
     */
    protected TreeNode<Long> buildTree(long value, long leftValue, long rightValue) {
        TreeNode<Long> tree = new TreeNode<Long>(value);
        tree.setLeft(new TreeNode<Long>(leftValue));
        tree.setRight(new TreeNode<Long>(rightValue));
        return tree;
    }

}
