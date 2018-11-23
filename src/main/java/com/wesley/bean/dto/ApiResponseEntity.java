package com.wesley.bean.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 接口响应实体
 * ========================
 */
@Data
@Builder
public class ApiResponseEntity<T extends Object> {
    /**
     * 错误消息
     */
    private String errorMsg;
    /**
     * 数据内容
     */
    private T data;
}
