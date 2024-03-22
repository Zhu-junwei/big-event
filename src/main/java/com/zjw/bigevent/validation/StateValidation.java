package com.zjw.bigevent.validation;

import com.zjw.bigevent.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 文章状态校验
 * @author 朱俊伟
 * @since 2024/03/16 17:35
 */
public class StateValidation implements ConstraintValidator<State, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return "草稿".equals(value) || "已发布".equals(value);
    }
}
