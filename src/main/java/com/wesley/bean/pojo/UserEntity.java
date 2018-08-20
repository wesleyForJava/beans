package com.wesley.bean.pojo;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wesley.bean.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="t_user")
@EqualsAndHashCode(callSuper=false)
public class UserEntity extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_id")
	private Long id;
	
	@Column(name = "t_name")
	private String name;
	
	@Column(name = "t_age")
	private int age;
	
	@Column(name = "t_address")
	private String address;
	
    @Column(name = "t_pwd")
    private String pwd;

}
