package ru.sber.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Аспект проверяет, что аргументы метода не null и не пустые
 */
@Aspect
@Component
public class CheckerAspect {

    @Before("@annotation(ru.sber.annotation.NotEmpty)")
    public void isNotNull(JoinPoint joinPoint) {
        String s = Arrays.toString(joinPoint.getArgs());
        if (s.contains("null"))
            throw new RuntimeException("null string");

    }

    @Before("@annotation(ru.sber.annotation.NotEmpty)")
    public void isNotEmpty(JoinPoint joinPoint) {
        String s = Arrays.toString(joinPoint.getArgs());
        if (s.contains("[]"))
            throw new RuntimeException("empty argument");
    }

}
