package com.beneu.beneuprod.deploy;

import com.beneu.beneuprod.dal.dao.PayTcTradeDao;
import com.beneu.beneuprod.dal.entity.PayTcTradeDo;
import com.beneu.common.util.log.LoggerFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PayBizTest {

    @Autowired
    private PayTcTradeDao payTcTradeDao;

    @Test
    public void testPayBiz() {
        PayTcTradeDo payTcTradeDo = payTcTradeDao.selectById(1L);
        LoggerFormatUtil.info(log, "payTcTradeDo={0}", payTcTradeDo);
    }
}
