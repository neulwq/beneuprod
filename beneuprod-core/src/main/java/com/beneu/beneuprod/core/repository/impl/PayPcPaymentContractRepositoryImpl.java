package com.beneu.beneuprod.core.repository.impl;

import com.beneu.beneuprod.core.model.PayPcPaymentContractModel;
import com.beneu.beneuprod.core.repository.PayPcPaymentContractRepository;
import com.beneu.beneuprod.dal.dao.PayPcPaymentContractDao;
import com.beneu.beneuprod.dal.entity.PayPcPaymentContractDo;
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
public class PayPcPaymentContractRepositoryImpl extends BaseBizRepositoryImpl<PayPcPaymentContractModel, PayPcPaymentContractDo, Long> implements PayPcPaymentContractRepository {

    @Autowired
    private PayPcPaymentContractDao payPcPaymentContractDao;

    @Override
    protected IBaseDao<PayPcPaymentContractDo> getBaseDao() {
        return payPcPaymentContractDao;
    }
}
