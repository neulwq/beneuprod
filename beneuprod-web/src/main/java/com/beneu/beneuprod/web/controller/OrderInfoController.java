package com.beneu.beneuprod.web.controller;

import com.alibaba.fastjson.JSON;
import com.beneu.beneuprod.core.repository.OrderInfoRepository;
import com.beneu.common.util.log.LoggerFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderInfoController {


    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @GetMapping("query")
    public String queryOrderInfo(@RequestParam String orderCode) {
        LoggerFormatUtil.info(log, "查询订单，orderCode={0}", orderCode);
        return JSON.toJSONString(orderInfoRepository.queryByOrderCode(orderCode));
    }
}
