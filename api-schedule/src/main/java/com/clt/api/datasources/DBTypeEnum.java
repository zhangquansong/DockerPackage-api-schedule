package com.clt.api.datasources;

public enum DBTypeEnum {

	bizDataSource("bizDataSource"), qrtzDataSource("qrtzDataSource");

	private String value;

	DBTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}