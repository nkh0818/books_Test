package com.__28.books.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectAOP_Books {
    @Before("execution(* com.__28.books..*.*(..))")
    public void BeforeMessage(JoinPoint joinPoint) {
        System.out.println("메서드 실행전 메세지.");
    }

    @Around("execution(* com.__28.books..*.*(..))")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(">>> [전] 매개변수: " + java.util.Arrays.toString(joinPoint.getArgs()));
        Object result = joinPoint.proceed(); // 메서드 실행
        System.out.println("<<< [후] 반환값: " + result);
        return result;
    }
}
