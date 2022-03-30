package com.beneu.beneuprod.core;

import com.alibaba.fastjson.JSON;
import com.beneu.beneuprod.core.model.BinaryTree;
import com.beneu.beneuprod.core.model.SingleLinkedList;
import com.beneu.common.util.log.MessageFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/3/30 23:40
 */
@Slf4j
public class LinkedListTest {

    @Test
    public void testTreeNode() {
        SingleLinkedList<Integer> linkedList = new SingleLinkedList<Integer>();
        linkedList.add(new Integer[] {2,3,4,5,6,7,8,9});
        visitLinkedList(linkedList);
        log.info("镜像操作");
        //反转
        linkedList.revert();
        visitLinkedList(linkedList);
        log.info("再镜像操作");
        //再反转
        linkedList.revert();
        visitLinkedList(linkedList);

    }

    /**
     * 遍历链表
     *
     * @param linkedList
     */
    protected <T extends Comparable<T>> void visitLinkedList(SingleLinkedList<Integer> linkedList) {
        log.info(MessageFormatUtil.msgFormat("visit={0}", JSON.toJSONString(linkedList.visit())));
    }
}
