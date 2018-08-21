package com.wesley.bean.pojo;

import java.io.Serializable;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.wesley.bean.interfaces.FlagVidator;

import lombok.Data;

@Data
public class DemoEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Length(min=2,max=10)
	@NotEmpty
	private String name;

	@Min(value=1)
	private String password;
    
	@NotBlank
	@Email
	private String email;
	
	@FlagVidator(values="1,2,3")
	private String flag;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
