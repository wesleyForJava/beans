package com.wesley.bean.exception;

import org.springframework.util.ObjectUtils;

public class ErrorMessageTools {
	/**
     * 异常消息获取
     *
     * @param errCode 异常消息码
     * @param params  格式化异常参数所需参数列表
     * @return
     */
    public static String getErrorMessage(String errCode, Object... params) {
        //获取业务逻辑消息实现
        LogicExceptionMessage logicExceptionMessage = SpringBeanTools.getBean(LogicExceptionMessage.class);
        if (ObjectUtils.isEmpty(logicExceptionMessage)) {
            try {
                throw new Exception("请配置实现LogicExceptionMessage接口并设置实现类被SpringIoc所管理。");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //获取错误消息内容
        String errMsg = logicExceptionMessage.getMessage(errCode);
        //格式化错误消息内容
        return ObjectUtils.isEmpty(params) ? errMsg : String.format(errMsg, params);
    }
}

