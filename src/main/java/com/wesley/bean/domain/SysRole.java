package com.wesley.bean.domain;

import lombok.Data;

@Data
public class SysRole {
	private Integer sysRoleId;
    private Byte sysRoleAva; //角色是否生效
    private String sysRoleDes;//角色描述
    private String sysRoleName;//角色名称
}
