package com.beneu.beneuprod.core;

import com.alibaba.fastjson.JSON;
import com.beneu.beneuprod.core.model.SList;
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
public class ListTest {

    @Test
    public void testTreeNode() {
        log.info("5/2=" + 5/2);
        log.info("6/2=" + 6/2);
        SList<Integer> linkedList = new SList<Integer>();
        linkedList.add(new Integer[] {1,3,5,7,9});
        visitLinkedList(linkedList);
        log.info("镜像操作");
        //反转
        linkedList.revert();
        visitLinkedList(linkedList);
        log.info("再镜像操作");
        //再反转
        linkedList.revert();
        visitLinkedList(linkedList);

        SList<Integer> subLinkedList = new SList<Integer>();
        subLinkedList.add(new Integer[] {2,4,6,8,10});
        linkedList.merge(subLinkedList);
        visitLinkedList(linkedList);

        log.info("middle=" + JSON.toJSONString(linkedList.middle()));

        linkedList.remove(10);
        visitLinkedList(linkedList);

        log.info("middle=" + JSON.toJSONString(linkedList.middle()));

        log.info("hasCycle=" + linkedList.hasCycle());
    }

    /**
     * 遍历链表
     *
     * @param linkedList
     */
    protected <T extends Comparable<T>> void visitLinkedList(SList<Integer> linkedList) {
        log.info(MessageFormatUtil.msgFormat("visit={0}", JSON.toJSONString(linkedList.visit())));
    }
}
