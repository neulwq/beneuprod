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
@TableName("pay_pc_payment_contract")
public class PayPcPaymentContractDo extends BaseDo<Long> {

    @TableId(type = IdType.AUTO)
    private Long id;

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
