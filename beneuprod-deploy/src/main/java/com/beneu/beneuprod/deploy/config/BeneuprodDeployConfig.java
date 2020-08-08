package com.beneu.beneuprod.deploy.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <Description>:
 *
 * @author lwq
 * @version 1.0
 * @createDate 2020/6/3 21:59
 */
@Configuration
@ComponentScan({"com.beneu.beneuprod"})
@MapperScan("com.beneu.beneuprod.dal.dao")
public class BeneuprodDeployConfig {
}
