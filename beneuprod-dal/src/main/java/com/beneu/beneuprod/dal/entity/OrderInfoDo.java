package com.beneu.beneuprod.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beneu.common.dal.entity.BaseDeletedDo;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <Description>: 自动生成的Do实体
 *
 * @author sdalgen
 * @version 1.0
 * @createDate 2020-5-31 22:05:31
 */
@Getter
@Setter
@TableName("order_info")
public class OrderInfoDo extends BaseDeletedDo<Long> {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 订单号 */
    private String orderCode;

    /** 订单类型 */
    private String orderType;


    /**
     * 重写toString，JSON格式
     * @return
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}