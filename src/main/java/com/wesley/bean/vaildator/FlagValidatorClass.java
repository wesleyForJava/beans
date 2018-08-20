package com.wesley.bean.vaildator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wesley.bean.interfaces.FlagVidator;

public class FlagValidatorClass  implements ConstraintValidator<FlagVidator, Object> {
	
	//临时变量保存flag值列表
	private String values;
    
	
	//初始化values的值
	@Override
	public void initialize(FlagVidator constraintAnnotation) {
		this.values=constraintAnnotation.values();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		//分割定义的有效值
		String[] value_array = values.split(",");
		boolean Flag =false;
		//遍历比对有效值
		for (int i = 0; i < value_array.length; i++) {
			if(value_array[i].equals(value)) {
                 Flag=true;	
                 break;
			}
		}
		//返回是否存在boolean
		return Flag;
	}



}
