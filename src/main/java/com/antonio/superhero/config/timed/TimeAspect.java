package com.antonio.superhero.config.timed;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
public class TimeAspect {

    /**
     * Configs the annotation package
     */
    @Pointcut("@annotation(com.antonio.superhero.config.timed.Timer)")
    private void pointcut() {}

    /**
     * Executes the local time read
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        long start = System.currentTimeMillis();
        log.info(String.format("[%s] - %s(). Start.", className, methodName));

        Object result = joinPoint.proceed();

        long time = System.currentTimeMillis() - start;
        log.info(String.format("[%s] - %s(). Finish %s ms.", className, methodName, time));

        return result;
    }

}
