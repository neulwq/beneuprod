package com.beneu.beneuprod.deploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <Description>:
 *
 * @author lwq
 * @version 1.0
 * @createDate 2020/6/3 21:57
 */
@SpringBootApplication
@EnableTransactionManagement
public class BeneuprodDeployApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeneuprodDeployApplication.class, args);
	}
}
