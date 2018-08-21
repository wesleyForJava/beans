package com.wesley.bean.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "t_logger_infos")
@Data
public class LoggerEntity {
	 //编号
    @Id
    @GeneratedValue
    @Column(name = "ali_id")
    private Long id;
    //客户端请求ip
    @Column(name = "ali_client_ip")
    private String clientIp;
    //客户端请求路径
    @Column(name = "ali_uri")
    private String uri;
    //终端请求方式,普通请求,ajax请求
    @Column(name = "ali_type")
    private String type;
    //请求方式method,post,get等
    @Column(name = "ali_method")
    private String method;
    //请求参数内容,json
    @Column(name = "ali_param_data")
    private String paramData;
    //请求接口唯一session标识
    @Column(name = "ali_session_id")
    private String sessionId;
    //请求时间
    @Column(name = "ali_time",insertable = false)
    private Timestamp time;
    //接口返回时间
    @Column(name = "ali_returm_time")
    private String returnTime;
    //接口返回数据json
    @Column(name = "ali_return_data")
    private String returnData;
    //请求时httpStatusCode代码，如：200,400,404等
    @Column(name = "ali_http_status_code")
    private String httpStatusCode;
    //请求耗时秒单位
    @Column(name = "ali_time_consuming")
    private int timeConsuming;
}