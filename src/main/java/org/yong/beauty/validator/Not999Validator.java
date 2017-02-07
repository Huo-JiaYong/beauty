package org.yong.beauty.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义验证
 * @author h7866
 *
 */
public class Not999Validator implements ConstraintValidator<Not999, Long> {

	@Override
	public void initialize(Not999 constraintAnnotation) {
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		if (value == 999) {
			return false;
		} else {
			return true;
		}
	}

}
