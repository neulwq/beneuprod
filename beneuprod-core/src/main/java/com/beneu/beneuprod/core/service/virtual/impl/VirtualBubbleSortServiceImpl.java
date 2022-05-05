package com.beneu.beneuprod.core.service.virtual.impl;

import com.beneu.beneuprod.core.service.impl.BaseSortService;
import com.beneu.common.util.log.LoggerFormatUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/5/5 22:18
 */
@Slf4j
public class VirtualBubbleSortServiceImpl<T extends Comparable<T>> extends BaseSortService<T> {

    /** [begin,end] */
    @Override
    public void sort(T[] data, int begin, int end) {
        LoggerFormatUtil.info(log, "VirtualBubbleSortServiceImpl.sort[{0}, {1}]", begin, end);
        //N - 1 趟
        for (int i = begin; i < end; ++i) {
            for (int j = begin; j < end - (i - begin); ++j) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    swap(data, j, j+1);
                }
            }
        }
    }
}
