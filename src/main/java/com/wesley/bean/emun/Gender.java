package com.wesley.bean.emun;

public enum Gender {
	MALE("男性"),
	FEMALE("女性");
	
	private String value;

	private Gender(String value) {
		this.value=value;
	}
}
