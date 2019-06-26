package com.wesley.bean.domain;

import lombok.Data;

@Data
public class SysAuth {
	private Integer sysAuthId;
    private String sysAuthCode; //权限编号
    private String sysAuthName; //权限名称
    private String sysAuthUrl; //权限请求的url 例如: user/login
    private String sysAuthPermission; //权限的的名称例如 user:login
    private Byte sysAuthAva; //权限是否有效
    private Byte sysAuthType; //权限类型。菜单还是按钮
    private String sysAuthDes; //权限描述
    
    
}
