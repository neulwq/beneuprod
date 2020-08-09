package com.beneu.beneuprod.util;

import java.lang.reflect.Array;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2020/8/8 21:22
 */
public class ArrayMergeUtil {

    /**
     * 合并两个有序数组
     *
     * @param left
     * @param right
     * @return
     */
    public static <T extends Comparable<T>> T[] mergeArray(T[] left, T[] right) {
        T[] target = (T[]) Array.newInstance(left[0].getClass(), left.length + right.length);
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 0; i < target.length; i++) {
            if (leftIndex == left.length ) {
                //拷贝后续元素
                coopyArray(right, target, rightIndex, i, right.length - rightIndex);
                break;
            }
            if (rightIndex == right.length ) {
                //拷贝后续元素
                coopyArray(left, target, leftIndex, i, left.length - leftIndex);
                break;
            }

            if (left[leftIndex].compareTo(right[rightIndex]) <= 0) {
                target[i] = left[leftIndex++];
            } else {
                target[i] = right[rightIndex++];
            }
        }
        return target;
    }

    /**
     * 合并两个有序数组
     *
     * @param left
     * @param leftBegin
     * @param leftEnd
     * @param right
     * @param rightBegin
     * @param rightEnd
     * @return
     */
    public static <T extends Comparable<T>> T[] mergeArray(T[] left, int leftBegin, int leftEnd, T[] right, int rightBegin, int rightEnd) {
        //left的长度 + right的长度
        int resultLength = (leftEnd - leftBegin + 1) + (rightEnd - rightBegin + 1);
        T[] target = (T[]) Array.newInstance(left[0].getClass(), resultLength);
        int leftIndex = leftBegin;
        int rightIndex = rightBegin;
        for (int i = 0; i < resultLength; i++) {
            if (leftIndex == leftEnd + 1) {
                //拷贝后续元素
                coopyArray(right, target, rightIndex, i, (rightEnd - rightIndex + 1));
                break;
            }
            if (rightIndex == rightEnd + 1) {
                //拷贝后续元素
                coopyArray(left, target, leftIndex, i, (leftEnd - leftIndex + 1));
                break;
            }
            if (left[leftIndex].compareTo(right[rightIndex]) <= 0) {
                target[i] = left[leftIndex++];
            } else {
                target[i] = right[rightIndex++];
            }
        }
        return target;
    }

    /**
     * 数组拷贝
     *
     * @param source
     * @param target
     * @param sourceBegin
     * @param tragetBegin
     * @param size
     */
    protected static <T>  void coopyArray(T[] source, T[] target, int sourceBegin, int tragetBegin, int size) {
        while(size > 0) {
            target[tragetBegin++] = source[sourceBegin++];
            size--;
        }
    }
}
