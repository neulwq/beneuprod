package com.beneu.beneuprod.core;

import com.beneu.beneuprod.core.service.SortService;
import com.beneu.beneuprod.core.service.impl.InsertSortServiceImpl;
import com.beneu.beneuprod.core.service.impl.MergeSortServiceImpl;
import com.beneu.beneuprod.core.service.impl.QuickSortServiceImpl;
import com.beneu.beneuprod.core.service.impl.UnRecursiveQuickSortServiceImpl;
import com.beneu.beneuprod.util.ArrayMergeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2020/8/8 19:04
 */
@Slf4j
public class ArraySortTest {

    @Test
    public void testQuickSort() {
        Integer[] data = buildArray();

        Integer[] dataCopy = Arrays.copyOf(data, data.length);
        Arrays.sort(dataCopy);
        String sortResult = Arrays.toString(dataCopy);

        dataCopy = Arrays.copyOf(data, data.length);
        //递归快排实现
        SortService<Integer> sortService = new QuickSortServiceImpl<>();
        sortService.sort(dataCopy);
        log.info(Arrays.toString(dataCopy));
        Assert.assertEquals(Arrays.toString(dataCopy), sortResult);

        dataCopy = Arrays.copyOf(data, data.length);
        //非递归快排实现
        sortService = new UnRecursiveQuickSortServiceImpl<>();
        sortService.sort(dataCopy);
        log.info(Arrays.toString(dataCopy));
        Assert.assertEquals(Arrays.toString(dataCopy), sortResult);
    }

    @Test
    public void testMergeSort() {
        Integer[] data = buildArray();

        Integer[] dataCopy = Arrays.copyOf(data, data.length);
        Arrays.sort(dataCopy);
        String sortResult = Arrays.toString(dataCopy);

        dataCopy = Arrays.copyOf(data, data.length);
        //归并排序递归实现
        SortService<Integer> sortService = new MergeSortServiceImpl<>();
        sortService.sort(dataCopy);
        log.info(Arrays.toString(dataCopy));
        Assert.assertEquals(Arrays.toString(dataCopy), sortResult);
    }

    @Test
    public void testInsertSort() {
        Integer[] data = buildArray();

        Integer[] dataCopy = Arrays.copyOf(data, data.length);
        Arrays.sort(dataCopy);
        String sortResult = Arrays.toString(dataCopy);

        dataCopy = Arrays.copyOf(data, data.length);
        //插入排序实现
        SortService<Integer> sortService = new InsertSortServiceImpl<>();
        sortService.sort(dataCopy);
        log.info(Arrays.toString(dataCopy));
        Assert.assertEquals(Arrays.toString(dataCopy), sortResult);

    }

    @Test
    public void testArrayMerge() {
        Integer[] array_1 = {11, 23};
        Integer[] array_2 = {45, 59};
        Integer[] result = ArrayMergeUtil.mergeArray(array_1, array_2);
        log.info("array_1=" + Arrays.toString(array_1));
        log.info("array_2=" + Arrays.toString(array_2));
        log.info("result=" + Arrays.toString(result));
    }

    /**
     * 构造数组
     * @return
     */
    protected Integer[] buildArray() {
        Integer[] array = new Integer[100];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        log.info("source array="+ Arrays.toString(array));
        return array;
    }


}
