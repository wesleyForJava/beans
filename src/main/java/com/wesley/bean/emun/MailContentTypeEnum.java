package com.wesley.bean.emun;

public enum MailContentTypeEnum {
	HTML("text/html; Charset=-tf-8"), TEXT("text");
	private String value;

	MailContentTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
