package com.zy.rj;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement
public class RjApplication {
    public static void main(String[] args) {
        SpringApplication.run(RjApplication.class,args);
        log.info("项目启动成功----------------------------------------------");

        //              http://localhost:8080/backend/page/login/login.html
        //http://localhost:8080/backend/index.html

        //这里是002提交
        
        //这里是003github上提交
        
    }
}