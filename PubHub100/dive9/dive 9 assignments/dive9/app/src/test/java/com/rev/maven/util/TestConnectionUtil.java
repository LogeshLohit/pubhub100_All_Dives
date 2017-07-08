package com.rev.maven.util;

public class TestConnectionUtil {
	public static void main(String[] args) {
		ConnectionUtil connectionUtil = new ConnectionUtil();
		System.out.println(connectionUtil.dataSource());
		System.out.println(connectionUtil.getJdbcTemplate());
	}
}
