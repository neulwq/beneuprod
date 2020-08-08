package com.beneu.beneuprod.core.repository.impl;

import com.beneu.beneuprod.core.model.OrderInfoModel;
import com.beneu.beneuprod.core.repository.OrderInfoRepository;
import com.beneu.beneuprod.dal.dao.OrderInfoDao;
import com.beneu.beneuprod.dal.entity.OrderInfoDo;
import com.beneu.common.core.repository.impl.BaseDeletedRepositoryImpl;
import com.beneu.common.dal.dao.IBaseDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <Description>:
 *
 * @author lwq
 * @version 1.0
 * @createDate 2020/5/27 21:39
 */
@Repository
public class OrderInfoRepositoryImpl extends BaseDeletedRepositoryImpl<OrderInfoModel, OrderInfoDo, Long> implements OrderInfoRepository {

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Override
    protected IBaseDao<OrderInfoDo> getBaseDao() {
        return orderInfoDao;
    }

    @Override
    public OrderInfoModel queryByOrderCode(String orderCode) {
        Assert.isTrue(StringUtils.isNotBlank(orderCode), "查询订单orderCode不能为空.");
        Map<String, Object> params = new HashMap<>();
        params.put("orderCode", orderCode);
        List<OrderInfoModel> orderInfoModels = this.selectList(params);
        return CollectionUtils.isEmpty(orderInfoModels) ? null : orderInfoModels.get(0);
    }
}
