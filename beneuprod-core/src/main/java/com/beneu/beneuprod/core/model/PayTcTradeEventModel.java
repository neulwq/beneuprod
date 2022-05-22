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
 * @createDate 2022/5/22 16:34
 */
@Getter
@Setter
@ToString(callSuper=true)
public class PayTcTradeEventModel extends BaseModel<Long> {

    /** 事件单号 */
    private String tradeEventNo;

    /** 交易单号 */
    private String tradeNo;

    /** 外部业务单号 */
    private String outBizNo;

    /** 外部交易单号 */
    private String outTradeNo;

    /** 事件状态 */
    private String state;

    /** 业务类型 */
    private String bizType;

    /** 业务流向码，第三码 */
    private String bizAction;

    /** 交易总金额 */
    private Long totalAmount;

    /** 实际金额 */
    private Long realAmount;

    /** 逆向总金额 */
    private Long revertAmount;

    /** 逆向中金额 */
    private Long revertingAmount;

    /** 事件类型 */
    private String eventType;

    /** 关联单号 */
    private String relationNo;

    /** 交易币种 */
    private String currency;

    /** 合作方 */
    private String partnerId;

    /** 收款方 */
    private String payeeId;

    /** 付款方 */
    private String payerId;

    /** 完成时间 */
    private java.util.Date finishedTime;

    /** 关单时间 */
    private java.util.Date closeTime;

    /** 过期时间 */
    private java.util.Date expireTime;

    /** 环境 */
    private String env;

    /** 业务标记 */
    private String tags;

    /** 业务透传字段 */
    private Map<String, String> attachment = new HashMap<>();

}
