package com.beneu.beneuprod.core.repository;

import com.beneu.beneuprod.core.model.OrderInfoModel;
import com.beneu.beneuprod.dal.entity.OrderInfoDo;
import com.beneu.common.core.repository.IBaseDeletedRepository;

/**
 * <Description>:
 *
 * @author lwq
 * @version 1.0
 * @createDate 2020/5/27 21:12
 */
public interface OrderInfoRepository extends IBaseDeletedRepository<OrderInfoModel, OrderInfoDo, Long> {

    /**
     * 查询订单
     *
     * @param orderCode
     * @return
     */
    public OrderInfoModel queryByOrderCode(String orderCode);
}
