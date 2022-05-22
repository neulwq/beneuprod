package com.beneu.beneuprod.core.util;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/5/22 18:47
 */
public class CircleInteger {

    private final int maxVal;
    private int val;

    public CircleInteger(int val, int maxVal) {
        if (val > maxVal || maxVal < 0) {
            throw new UnsupportedOperationException("val > maxVal || maxVal < 0");
        }
        this.maxVal = maxVal;
        this.val = val;
    }

    protected synchronized int getAndIncrement() {
        ++val;
        if (val >= maxVal) {
            val = 0;
        }
        return val;
    }

    public String getSequence(int length) {
        int cur = getAndIncrement();
        return StringUtils.leftPad(String.valueOf(cur), length, "0");
    }
}
