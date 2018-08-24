package com.wesley.bean.failureanalyzer;

import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
/**
 * 故障分析器，提供解决方案
 * @author wesley
 * FailureAnalyzer 用于分析故障并提供可以向用户显示的诊断信息。
 * Package org.springframework.boot.diagnostics
 * https://docs.spring.io/spring-boot/docs/1.5.15.RELEASE/api/org/springframework/boot/diagnostics/package-summary.html
 */
public class  MyBeanNotOfRequiredTypeFailureAnalyzer extends AbstractFailureAnalyzer<BeanNotOfRequiredTypeException>{

	@Override
	protected FailureAnalysis analyze(Throwable rootFailure, BeanNotOfRequiredTypeException cause) {
		return new FailureAnalysis(getDescription(cause), getAction(cause), cause);
	}


	 
    private String getDescription(BeanNotOfRequiredTypeException ex) {
        return String.format("The bean %s 不能注入 %s "
          + "因为它属于类型 %s",
          ex.getBeanName(),
          ex.getRequiredType().getName(),
          ex.getActualType().getName());
    }
 
    private String getAction(BeanNotOfRequiredTypeException ex) {
        return String.format("考虑创建名为: %s of type %s",
          ex.getBeanName(),
          ex.getRequiredType().getName());
    }
}
