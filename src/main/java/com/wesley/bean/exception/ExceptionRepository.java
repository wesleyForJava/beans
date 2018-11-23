package com.wesley.bean.exception;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wesley.bean.pojo.ExceptionInfoEntity;

/**
 * 异常数据接口定义
 * ========================
 */
public interface ExceptionRepository
    extends JpaRepository<ExceptionInfoEntity,Integer>
{
    /**
     * 根据异常码获取异常配置信息
     * @param code 异常码
     * @return
     */
    ExceptionInfoEntity findTopByCode(String code);
}

