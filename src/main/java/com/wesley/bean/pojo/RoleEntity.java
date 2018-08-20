package com.wesley.bean.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class RoleEntity implements Serializable
{	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "r_id")
    private Long id;
    @Column(name = "r_name")
    private String name;
    @Column(name = "r_flag")
    private String flag;
}

