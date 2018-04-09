package com.melt.test.design.proxy.jdk;

public class UserServiceImpl implements UserService {

	@Override
	public boolean save(String msg) {
		System.out.println("real save invoke");
		return false;
	}

}
