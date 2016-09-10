/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.aspect;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hasitha Lakmal
 */
@Component
@Aspect
public class LogingAspect {

    /**
     * Following is the definition for a pointcut to select all the methods
     * available. So advice will be called for all the methods.
     */
    @Pointcut("execution(* test(..))")
    private void AccessLogingpc() {
    }

    /**
     * This is the method which I would like to execute before a selected method
     * execution.
     * @param joinPoint
     */
    @Before("AccessLogingpc()")
    public void beforeAdvice_AccessLogingpc(JoinPoint joinPoint) {
        System.out.println("**************Start Method***************");
        System.out.println(joinPoint.getSignature());
        System.out.println(Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * This is the method which I would like to execute after a selected method
     * execution.
     * @param joinPoint
     */
    @After("AccessLogingpc()")
    public void afterAdvice_AccessLogingpc(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature());
        System.out.println("**************End Method***************");
       
    }

}
