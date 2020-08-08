package com.beneu.beneuprod.core.model;

import com.beneu.common.core.model.BaseDeletedModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <Description>:
 *
 * @author lwq
 * @version 1.0
 * @createDate 2020/5/27 21:24
 */
@Getter
@Setter
public class OrderInfoModel extends BaseDeletedModel<Long> {

    /** 主键 */
    private Long id;

    /** 订单号 */
    private String orderCode;

    /** 订单类型 */
    private String orderType;
}
