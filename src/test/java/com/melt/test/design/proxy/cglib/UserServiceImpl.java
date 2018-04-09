package com.melt.test.design.proxy.cglib;

public class UserServiceImpl {

	public boolean save(String msg) {
		System.out.println("real save invoke [cglib]");
		return false;
	}
	
	public void query(String msg) {
		System.out.println("real query invoke [cglib]");
	}
	
	public void query2(String msg) {
		System.out.println("real query invoke [cglib]");
	}
}
