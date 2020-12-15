package com.hive4bee.vo;

import lombok.Data;

@Data
public class StartVO {
	private int page;
	public StartVO() {
		this(1);
	}
	public StartVO(int page) {
		this.page=page;
	}
}
