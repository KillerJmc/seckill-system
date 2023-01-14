package com.lingyuango.seckill.payment.config;

import org.springframework.aot.hint.annotation.Reflective;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 基于类型的AOT反射信息生成的注解
 * @author Jmc
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Reflective(TypedReflectiveProcessor.class)
public @interface TypedReflective {

}
