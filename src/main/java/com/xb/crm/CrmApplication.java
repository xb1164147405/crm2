package com.xb.crm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = {"com.xb.crm"})
//@ComponentScan(basePackages = {"com.xb.crm"})
@MapperScan(basePackages = {"com.xb.crm.mapper"})
public class CrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }

    @Bean
    public PasswordEncoder createPwdEncoder(){
        //使密码进行多次MD5加盐
        return new BCryptPasswordEncoder();
    }

}
