package com.beneu.beneuprod.core.model;

import com.beneu.common.core.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/5/22 16:37
 */
@Getter
@Setter
@ToString(callSuper=true)
public class PayPcPaymentContractModel extends BaseModel<Long> {

    /** 支付单号 */
    private String paymentNo;

    /** 交易单号 */
    private String tradeNo;

    /** 交易事件单号 */
    private String tradeEventNo;

    /** 外部交易单号 */
    private String outTradeNo;

    /** 关联单号 */
    private String relationNo;

    /** 事件状态 */
    private String state;

    /** 业务类型 */
    private String bizType;

    /** 业务流向码，第三码 */
    private String bizAction;

    /** 支付金额 */
    private Long payAmount;

    /** 交易币种 */
    private String currency;

    /** 支付完成时间 */
    private java.util.Date finishedTime;

    /** 环境 */
    private String env;

    /** 业务标记 */
    private String tags;

    /** 业务透传字段 */
    private Map<String, String> attachment = new HashMap<>();

}
