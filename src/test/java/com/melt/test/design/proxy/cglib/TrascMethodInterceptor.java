package com.melt.test.design.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TrascMethodInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object obj, Method method, Object[] arg, MethodProxy proxy) throws Throwable {
//		System.out.println("obj:--" + obj);
		System.out.println("method:--" + method);
		System.out.println("proxy:--" + proxy);
		System.err.println("-----before----");
		Object result = proxy.invokeSuper(obj, arg) ;
		System.err.println("-----after----");
		
		return result ;
	}

}
