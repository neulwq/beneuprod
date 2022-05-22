package com.beneu.beneuprod.core.repository.impl;

import com.alibaba.fastjson.JSON;
import com.beneu.beneuprod.core.model.PayTcTradeEventModel;
import com.beneu.beneuprod.core.repository.PayTcTradeEventRepository;
import com.beneu.beneuprod.dal.dao.PayTcTradeEventDao;
import com.beneu.beneuprod.dal.entity.PayTcTradeEventDo;
import com.beneu.common.dal.dao.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/5/22 16:38
 */
@Repository
public class PayTcTradeEventRepositoryImpl extends BaseBizRepositoryImpl<PayTcTradeEventModel, PayTcTradeEventDo, Long> implements PayTcTradeEventRepository {

    @Autowired
    private PayTcTradeEventDao payTcTradeEventDao;

    @Override
    protected IBaseDao<PayTcTradeEventDo> getBaseDao() {
        return payTcTradeEventDao;
    }

    /**
     * Do -> Model
     *
     * @param entity
     * @return
     */
    protected PayTcTradeEventModel convert2Model(PayTcTradeEventDo entity) {
        PayTcTradeEventModel model = super.convert2Model(entity);
        if (model == null) {
            return null;
        }
        Map<String, String> attachment = JSON.parseObject(entity.getAttachment(), Map.class);
        model.setAttachment(attachment == null ? new HashMap<>() : attachment);

        return model;
    }

    /**
     * Model -> Do
     *
     * @param model
     * @return
     */
    protected PayTcTradeEventDo convertDo(PayTcTradeEventModel model) {
        PayTcTradeEventDo entity = super.convertDo(model);
        if (entity == null) {
            return null;
        }
        entity.setAttachment(JSON.toJSONString(model.getAttachment()));
        return entity;
    }
}
