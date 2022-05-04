package com.beneu.beneuprod.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beneu.common.dal.entity.BaseDo;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
* <Description>: 自动生成的Do实体
*
* @author sdalgen
* @version 1.0
* @createDate 2022-4-28 23:53:53
*/
@Getter
@Setter
@TableName("pay_tc_trade")
public class PayTcTradeDo extends BaseDo<Long> {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 交易单号 */
    private String tradeNo;

    /** 主事件单号 */
    private String mainEventNo;

    /** 外部交易单号 */
    private String outTradeNo;

    /** 外部交易子单号 */
    private String subOutTradeNo;

    /** 产品码，第一码 */
    private String bizProd;

    /** 业务模式码，第二码 */
    private String bizMode;

    /** 交易类型 */
    private String tradeType;

    /** 交易状态 */
    private String state;

    /** 结算状态 */
    private String settleState;

    /** 交易总金额 */
    private Long totalAmount;

    /** 支付总金额 */
    private Long payAmount;

    /** 退款总金额 */
    private Long refundAmount;

    /** 退款中金额 */
    private Long refundingAmount;

    /** 结算总金额 */
    private Long settleAmount;

    /** 结算中金额 */
    private Long settlingAmount;

    /** 分账总金额 */
    private Long sharedAmount;

    /** 分账中金额 */
    private Long sharingAmount;

    /** 退分账总金额 */
    private Long refundSharedAmount;

    /** 退分账中金额 */
    private Long refundSharingAmount;

    /** 交易币种 */
    private String currency;

    /** 合作方 */
    private String partnerId;

    /** 收款方 */
    private String payeeId;

    /** 付款方 */
    private String payerId;

    /** 结算时间 */
    private java.util.Date settleTime;

    /** 关单时间 */
    private java.util.Date closeTime;

    /** 过期时间 */
    private java.util.Date expireTime;

    /** 环境 */
    private String env;

    /** 业务标记 */
    private String tags;

    /** 业务透传字段 */
    private String attachment;


    /**
     * 重写toString，JSON格式
     * @return
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
