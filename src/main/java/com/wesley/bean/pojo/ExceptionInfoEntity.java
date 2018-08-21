package com.wesley.bean.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 系统异常基本信息实体
 * ========================
 * Created with IntelliJ IDEA.
 * Date：2018/1/7
 * Time：下午3:35
 * 码云：http://git.oschina.net/jnyqy
 * ========================
 * @author yuqiyu
 */
@Data
@Entity
@Table(name = "sys_exception_info")
public class ExceptionInfoEntity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 异常消息编号
     */
    @Id
    @GeneratedValue
    @Column(name = "EI_ID")
    private Integer id;
    /**
     * 异常消息错误码
     */
    @Column(name = "EI_CODE")
    private String code;
    /**
     * 异常消息内容
     */
    @Column(name = "EI_MESSAGE")
    private String message;
}

