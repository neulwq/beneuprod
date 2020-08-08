package com.beneu.beneuprod.deploy;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2020/8/5 21:01
 */
public class TestMain {

    @Test
    public void testSort() {
        int[] array_1 = {1, 2, 3, 4};
        int[] array_2 = {2, 3, 5, 6};
        int[] result = sort(array_1, array_2);
        System.out.println(JSON.toJSON(result));
    }

    public int[] sort(int[] array_1, int[] array_2) {
        int[] target = new int[array_1.length + array_2.length];
        int index_1 = 0;
        int index_2 = 0;
        for (int i = 0; i < array_1.length + array_2.length; i++) {
            if (index_1 == array_1.length ) {
                //拷贝后续元素
                coopyArray(array_2, target, index_2, i);
                break;
            }
            if (index_2 == array_1.length ) {
                //拷贝后续元素
                coopyArray(array_1, target, index_1, i);
                break;
            }
            if (array_1[index_1] <= array_2[index_2]) {
                target[i] = array_1[index_1++];
            } else {
                target[i] = array_2[index_2++];
            }
        }
        return target;
    }

    public void coopyArray(int[] source, int[] target, int sourceBegin, int tragetBegin) {
        while(sourceBegin < source.length) {
            target[tragetBegin++] = source[sourceBegin++];
        }
    }
}
