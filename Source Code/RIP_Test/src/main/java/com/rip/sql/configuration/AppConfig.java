package com.rip.sql.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.rip.sql")
public class AppConfig {

   /* @Bean
    public UserServiceImpl userService() {
        return new UserServiceImpl();
    }*/

   
}
