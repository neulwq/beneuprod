package com.beneu.beneuprod.core.repository.impl;

import com.beneu.beneuprod.core.model.PayPcPaymentDetailModel;
import com.beneu.beneuprod.core.repository.PayPcPaymentDetailRepository;
import com.beneu.beneuprod.dal.dao.PayPcPaymentDetailDao;
import com.beneu.beneuprod.dal.entity.PayPcPaymentDetailDo;
import com.beneu.common.dal.dao.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/5/22 17:03
 */
@Repository
public class PayPcPaymentDetailRepositoryImpl  extends BaseBizRepositoryImpl<PayPcPaymentDetailModel, PayPcPaymentDetailDo, Long> implements PayPcPaymentDetailRepository {

    @Autowired
    private PayPcPaymentDetailDao payPcPaymentDetailDao;

    @Override
    protected IBaseDao<PayPcPaymentDetailDo> getBaseDao() {
        return payPcPaymentDetailDao;
    }
}
