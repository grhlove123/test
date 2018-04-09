package com.melt.test.design.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

public class Test {

	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer() ;
		//被代理实现类
		enhancer.setSuperclass(UserServiceImpl.class);
		//回调方法(拦截逻辑)
		enhancer.setCallback(new TrascMethodInterceptor());
		UserServiceImpl service = (UserServiceImpl)enhancer.create() ;
		service.save(null) ;
		service.query(null);
	}

}
