package com.beneu.beneuprod.core.repository.impl;

import com.alibaba.fastjson.JSON;
import com.beneu.beneuprod.core.model.PayTcTradeModel;
import com.beneu.beneuprod.core.repository.PayTcTradeRepository;
import com.beneu.beneuprod.dal.dao.PayTcTradeDao;
import com.beneu.beneuprod.dal.entity.PayTcTradeDo;
import com.beneu.common.dal.dao.IBaseDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/5/22 16:23
 */
@Repository
public class PayTcTradeRepositoryImpl extends BaseBizRepositoryImpl<PayTcTradeModel, PayTcTradeDo, Long> implements PayTcTradeRepository {

    @Autowired
    private PayTcTradeDao payTcTradeDao;

    @Override
    protected IBaseDao<PayTcTradeDo> getBaseDao() {
        return payTcTradeDao;
    }

    /**
     * Do -> Model
     *
     * @param entity
     * @return
     */
    protected PayTcTradeModel convert2Model(PayTcTradeDo entity) {
        if (entity == null) {
            return null;
        }
        PayTcTradeModel model = super.convert2Model(entity);
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
    protected PayTcTradeDo convertDo(PayTcTradeModel model) {
        if (model == null) {
            return null;
        }
        PayTcTradeDo entity = newDoInstance();
        BeanUtils.copyProperties(model, entity, "extendField", "deleted", "attachment");
        entity.setId(model.getId());
        entity.setExtendField(JSON.toJSONString(model.getExtendField()));
        entity.setAttachment(JSON.toJSONString(model.getAttachment()));
        return entity;
    }
}
