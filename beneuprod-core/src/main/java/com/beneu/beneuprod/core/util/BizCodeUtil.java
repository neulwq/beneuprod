package com.beneu.beneuprod.core.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/5/22 18:33
 */
public class BizCodeUtil {

    public final static String TRADE_NO_TYPE = "01";
    public final static String EVENT_NO_TYPE = "02";
    public final static String PAY_DETAIL_NO_TYPE = "03";
    public final static String PAY_CONTRACT_NO_TYPE = "04";
    public final static String ORDER_NO_TYPE = "05";
    public final static String DATE_FORMAT = "yyyyMMddhhmmss";

    private final static CircleInteger TRADE_NO_SEQUENCE = new CircleInteger(0, 1000);
    private final static CircleInteger EVENT_NO_SEQUENCE = new CircleInteger(0, 1000);
    private final static CircleInteger PAY_DETAIL_NO_SEQUENCE = new CircleInteger(0, 1000);
    private final static CircleInteger PAY_CONTRACT_NO_SEQUENCE = new CircleInteger(0, 1000);
    private final static CircleInteger ORDER_NO_SEQUENCE = new CircleInteger(0, 1000);

    private final static int SEQUENCE_LENGTH = 3;
    /**
     * 构造trade_no
     *
     * @return
     */
    public static String geneTradeno() {
        return getDayFormatString() + TRADE_NO_TYPE + TRADE_NO_SEQUENCE.getSequence(SEQUENCE_LENGTH);
    }

    /**
     * 构造订单号
     *
     * @return
     */
    public static String geneOrderNo() {
        return "E" + getDayFormatString() + ORDER_NO_TYPE + ORDER_NO_SEQUENCE.getSequence(SEQUENCE_LENGTH);
    }

    /**
     * 构造事件单号
     *
     * @return
     */
    public static String geneEventNo() {
        return getDayFormatString() + EVENT_NO_TYPE + EVENT_NO_SEQUENCE.getSequence(SEQUENCE_LENGTH);
    }

    /**
     * 构造支付明细单号
     *
     * @return
     */
    public static String genePayDetailNo() {
        return getDayFormatString() + PAY_DETAIL_NO_TYPE + PAY_DETAIL_NO_SEQUENCE.getSequence(SEQUENCE_LENGTH);
    }

    /**
     * 构造支付合约单号
     *
     * @return
     */
    public static String genePayContractNo() {
        return getDayFormatString() + PAY_CONTRACT_NO_TYPE + PAY_CONTRACT_NO_SEQUENCE.getSequence(SEQUENCE_LENGTH);
    }

    /**
     * 获取日期格式
     *
     * @return
     */
    protected static String getDayFormatString() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.format(date);
    }
}
