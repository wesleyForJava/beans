package com.wesley.bean.pojo;

import lombok.Data;

/**
 * 服务端向浏览器发送的实体
 * @author pc
 *
 */
@Data
public class WisleyResponse {
	private String responseMessage;

	public WisleyResponse(String responseMessage) {
		this.responseMessage = responseMessage;
	}
}
