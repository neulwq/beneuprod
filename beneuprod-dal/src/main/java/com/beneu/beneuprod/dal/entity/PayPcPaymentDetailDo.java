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
@TableName("pay_pc_payment_detail")
public class PayPcPaymentDetailDo extends BaseDo<Long> {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 支付单号 */
    private String paymentNo;

    /** 支付明细单号 */
    private String paymentDetailNo;

    /** 交易单号 */
    private String tradeNo;

    /** 交易事件单号 */
    private String tradeEventNo;

    /** 事件状态，0:初始化，1:支付提交，2:支付中,3:预支付成功，4:支付成功，5：支付失败，6：支付关闭 */
    private String state;

    /** 支付工具 */
    private String payChannelApi;

    /** 支付金额 */
    private Long amount;

    /** 退款金额 */
    private Long refundAmount;

    /** 交易币种 */
    private String currency;

    /** 合作方 */
    private String partnerId;

    /** 收款方 */
    private String payeeId;

    /** 付款方 */
    private String payerId;

    /** 支付完成时间 */
    private java.util.Date finishedTime;

    /** 过期时间 */
    private java.util.Date expireTime;

    /** 渠道单号 */
    private String channelNo;

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
