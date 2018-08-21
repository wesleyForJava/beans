package com.wesley.bean.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "api_token_infos",schema = "jwt")
@Data
public class TokenInfoEntity {
    @Id
    @GeneratedValue
    @Column(name = "ati_id")
    private Long id;
    @Column(name = "ati_app_id")
    private  String appId;
    @Column(name = "ati_token")
    private byte[] token;
    @Column(name = "ati_build_time")
    private String buildTime;
}
