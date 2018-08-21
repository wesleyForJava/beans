package com.wesley.bean.interfaces;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import com.wesley.bean.vaildator.FlagValidatorClass;

@Target({ElementType.PARAMETER,ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=FlagValidatorClass.class)
public @interface FlagVidator {
    //flag的有效值多个使用','隔开
    String values();
    //提示内容
    String message() default"flag 不存在";
    Class<?> [] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
