package com.zjw.bigevent.anno;

import com.zjw.bigevent.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 文章状态注解
 *
 * @author 朱俊伟
 * @since 2024/03/16 17:26
 */
@Documented
@Constraint(validatedBy = {StateValidation.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface State {

    String message() default "{文章状态只能是草稿或已发布}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
