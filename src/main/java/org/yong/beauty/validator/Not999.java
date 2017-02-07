package org.yong.beauty.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 自定义validator标签 （hibernate validator组合使用）
 * 
 * @author h7866
 *
 */
@Constraint(validatedBy = Not999Validator.class)
@Target({ java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface Not999 {

	String message() default "{org.yong.beauty.validator.not999}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
