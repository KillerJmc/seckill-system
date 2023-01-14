package com.lingyuango.seckill.config;

import com.jmc.lang.Tries;
import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.ReflectionHints;
import org.springframework.aot.hint.annotation.ReflectiveProcessor;
import org.springframework.lang.NonNull;

import java.lang.reflect.AnnotatedElement;

/**
 * 基于类注解的AOT反射处理器
 * @author Jmc
 */
public class TypedReflectiveProcessor implements ReflectiveProcessor {
    @Override
    public void registerReflectionHints(@NonNull ReflectionHints hints, @NonNull AnnotatedElement element) {
        if (element instanceof Class<?> type) {
            // 加入构造器反射信息
            hints.registerConstructor(
                    Tries.tryReturnsT(type::getDeclaredConstructor),
                    ExecutableMode.INVOKE
            );

            // 遍历所有类方法
            for (var m : type.getDeclaredMethods()) {
                // 加入类方法反射信息
                hints.registerMethod(m, ExecutableMode.INVOKE);
            }
        }
    }
}
