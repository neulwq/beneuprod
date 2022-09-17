package com.beneu.beneuprod.deploy;

import com.beneu.beneuprod.core.model.PayPcPaymentContractModel;
import com.beneu.beneuprod.core.model.PayPcPaymentDetailModel;
import com.beneu.beneuprod.core.model.PayTcTradeEventModel;
import com.beneu.beneuprod.core.model.PayTcTradeModel;
import com.beneu.beneuprod.core.repository.PayPcPaymentContractRepository;
import com.beneu.beneuprod.core.repository.PayPcPaymentDetailRepository;
import com.beneu.beneuprod.core.repository.PayTcTradeEventRepository;
import com.beneu.beneuprod.core.repository.PayTcTradeRepository;
import com.beneu.beneuprod.core.util.BizCodeUtil;
import com.beneu.common.util.log.LoggerFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PayBizTest {

    @Autowired
    private PayTcTradeRepository payTcTradeRepository;

    @Autowired
    private PayTcTradeEventRepository payTcTradeEventRepository;

    @Autowired
    private PayPcPaymentContractRepository payPcPaymentContractRepository;

    @Autowired
    private PayPcPaymentDetailRepository payPcPaymentDetailRepository;

    @Test
    public void testPayBiz() {
        for (int i = 0; i < 50; ++i) {
            LoggerFormatUtil.info(log, "{0} BinaryString is {1} ", i, Integer.toBinaryString(i));
            LoggerFormatUtil.info(log, "{0} BinaryString is {1} ", -i, Integer.toBinaryString(-i));
        }
        for (int i = 0; i < 50; ++i) {
            PayTcTradeModel payTcTradeModel = genePayTcTradeModel();
            payTcTradeRepository.insert(payTcTradeModel);

            PayTcTradeModel payTcTradeModelQuery = payTcTradeRepository.selectById(payTcTradeModel.getId());
            //LoggerFormatUtil.info(log, "payTcTradeModel={0}", payTcTradeModel);
            LoggerFormatUtil.info(log, "payTcTradeModelQuery={0}", payTcTradeModelQuery);

            PayTcTradeEventModel payTcTradeEventModel = genePayTcTradeEventModel(payTcTradeModelQuery);
            payTcTradeEventRepository.insert(payTcTradeEventModel);
            PayTcTradeEventModel quryEventModel = payTcTradeEventRepository.selectById(payTcTradeEventModel.getId());

            //LoggerFormatUtil.info(log, "payTcTradeEventModel={0}", payTcTradeEventModel);
            LoggerFormatUtil.info(log, "quryEventModel={0}", quryEventModel);


            PayPcPaymentContractModel contractModel = genePayPcPaymentContractModel(quryEventModel);
            payPcPaymentContractRepository.insert(contractModel);
            PayPcPaymentContractModel queryContractModel = payPcPaymentContractRepository.selectById(contractModel.getId());
            //LoggerFormatUtil.info(log, "contractModel={0}", contractModel);
            LoggerFormatUtil.info(log, "queryContractModel={0}", queryContractModel);

            PayPcPaymentDetailModel paymentDetailModel = genePayPcPaymentDetailModel(quryEventModel, contractModel);
            payPcPaymentDetailRepository.insert(paymentDetailModel);
            PayPcPaymentDetailModel queryPaymentDetailModel = payPcPaymentDetailRepository.selectById(paymentDetailModel.getId());

            //LoggerFormatUtil.info(log, "paymentDetailModel={0}", paymentDetailModel);
            LoggerFormatUtil.info(log, "queryPaymentDetailModel={0}", queryPaymentDetailModel);
        }
    }

    protected String[] bizProds = new String[]{"1005", "1006", "1007"};
    protected String[] bizModes = new String[]{"001", "002", "003", "004"};
    protected String[] partnerIds = new String[]{"80001230058", "80001230059", "80001230060"};
    protected String[] payeeIds = new String[]{"2783290779", "2793290779", "2803290779", "2813290779", "2823290779"};

    protected String[] payChannelApis = new String[]{"90010001", "90010003", "90020002", "90020004", "9002"};
    Random random = new Random();

    protected PayTcTradeModel genePayTcTradeModel() {
        PayTcTradeModel payTcTradeModel = new PayTcTradeModel();
        payTcTradeModel.setTradeNo(BizCodeUtil.geneTradeno());
        payTcTradeModel.setOutTradeNo(BizCodeUtil.geneOrderNo());
        payTcTradeModel.setBizProd(bizProds[random.nextInt(bizProds.length)]);
        payTcTradeModel.setBizMode(bizModes[random.nextInt(bizModes.length)]);
        payTcTradeModel.setTradeType("PAY");
        payTcTradeModel.setState("4");
        payTcTradeModel.setSettleState("0");
        payTcTradeModel.setTotalAmount(100L);
        payTcTradeModel.setPayAmount(100L);
        payTcTradeModel.setCurrency("CNY");
        payTcTradeModel.setPartnerId(partnerIds[random.nextInt(partnerIds.length)]);
        payTcTradeModel.setPayeeId(payeeIds[random.nextInt(payeeIds.length)]);
        payTcTradeModel.setPayerId(String.valueOf(System.currentTimeMillis()));
        payTcTradeModel.setExpireTime(new Date());
        payTcTradeModel.setEnv("PROD");
        payTcTradeModel.setCreateTime(new Date());
        payTcTradeModel.setModifyTime(new Date());
        payTcTradeModel.getExtendField().put("order_no", payTcTradeModel.getOutTradeNo());
        payTcTradeModel.getAttachment().put("bizProd", payTcTradeModel.getBizProd());
        payTcTradeModel.getAttachment().put("src", "ubuntu");
        return payTcTradeModel;
    }

    protected PayTcTradeEventModel genePayTcTradeEventModel(PayTcTradeModel payTcTradeModel) {
        PayTcTradeEventModel payTcTradeEventModel = new PayTcTradeEventModel();
        payTcTradeEventModel.setTradeEventNo(BizCodeUtil.geneEventNo());
        payTcTradeEventModel.setTradeNo(payTcTradeModel.getTradeNo());
        payTcTradeEventModel.setOutBizNo(payTcTradeModel.getOutTradeNo());
        payTcTradeEventModel.setOutTradeNo(payTcTradeModel.getOutTradeNo());
        payTcTradeEventModel.setState("4");
        payTcTradeEventModel.setBizType("COMMON");
        payTcTradeEventModel.setBizAction("PAY");
        payTcTradeEventModel.setTotalAmount(payTcTradeModel.getTotalAmount());
        payTcTradeEventModel.setRealAmount(payTcTradeModel.getPayAmount());
        payTcTradeEventModel.setEventType("PAY");
        payTcTradeEventModel.setCurrency(payTcTradeModel.getCurrency());
        payTcTradeEventModel.setPartnerId(payTcTradeModel.getPartnerId());
        payTcTradeEventModel.setPayeeId(payTcTradeModel.getPayeeId());
        payTcTradeEventModel.setPayerId(payTcTradeModel.getPayerId());
        payTcTradeEventModel.setExpireTime(new Date());
        payTcTradeEventModel.setEnv(payTcTradeModel.getEnv());
        payTcTradeEventModel.setCreateTime(new Date());
        payTcTradeEventModel.setModifyTime(new Date());
        payTcTradeEventModel.getExtendField().put("order_no", payTcTradeModel.getOutTradeNo());
        payTcTradeEventModel.getAttachment().put("bizProd", payTcTradeModel.getBizProd());

        return payTcTradeEventModel;
    }

    protected PayPcPaymentContractModel genePayPcPaymentContractModel(PayTcTradeEventModel eventModel) {
        PayPcPaymentContractModel contractModel = new PayPcPaymentContractModel();
        contractModel.setPaymentNo(BizCodeUtil.genePayContractNo());
        contractModel.setTradeNo(eventModel.getTradeNo());
        contractModel.setTradeEventNo(eventModel.getTradeEventNo());
        contractModel.setOutTradeNo(eventModel.getOutTradeNo());
        contractModel.setState("4");
        contractModel.setBizType("COMMON");
        contractModel.setBizAction("PAY");
        contractModel.setPayAmount(eventModel.getRealAmount());
        contractModel.setCurrency(eventModel.getCurrency());
        contractModel.setFinishedTime(new Date());
        contractModel.setEnv(eventModel.getEnv());
        contractModel.setCreateTime(new Date());
        contractModel.setModifyTime(new Date());
        contractModel.getExtendField().put("order_no", eventModel.getOutTradeNo());
        contractModel.getAttachment().put("bizAction", eventModel.getBizAction());

        return contractModel;
    }

    protected PayPcPaymentDetailModel genePayPcPaymentDetailModel(PayTcTradeEventModel eventModel, PayPcPaymentContractModel contractModel) {
        PayPcPaymentDetailModel paymentDetailModel = new PayPcPaymentDetailModel();
        paymentDetailModel.setPaymentNo(contractModel.getPaymentNo());
        paymentDetailModel.setPaymentDetailNo(BizCodeUtil.genePayDetailNo());
        paymentDetailModel.setTradeNo(eventModel.getTradeNo());
        paymentDetailModel.setTradeEventNo(eventModel.getTradeEventNo());
        paymentDetailModel.setState("4");
        paymentDetailModel.setPayChannelApi(payChannelApis[random.nextInt(payChannelApis.length)]);
        paymentDetailModel.setAmount(eventModel.getRealAmount());
        paymentDetailModel.setCurrency(eventModel.getCurrency());
        paymentDetailModel.setPartnerId(eventModel.getPartnerId());
        paymentDetailModel.setPayeeId(eventModel.getPayeeId());
        paymentDetailModel.setPayerId(eventModel.getPayerId());
        paymentDetailModel.setExpireTime(new Date());
        paymentDetailModel.setEnv(contractModel.getEnv());
        paymentDetailModel.setCreateTime(new Date());
        paymentDetailModel.setModifyTime(new Date());
        paymentDetailModel.getExtendField().put("order_no", contractModel.getOutTradeNo());
        paymentDetailModel.getAttachment().put("bizAction", contractModel.getBizAction());
        return paymentDetailModel;
    }
}
