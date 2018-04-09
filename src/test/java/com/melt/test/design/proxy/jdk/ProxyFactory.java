package com.melt.test.design.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory implements InvocationHandler {
	
	private Object target ;

	public ProxyFactory(Object target) {
		super();
		this.target = target;
	}
	
	@Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始事务");
        //执行目标对象方法,这里是target而不是proxy
        Object returnValue = method.invoke(target, args);
        System.out.println("提交事务");
        return returnValue;
    }
	
	public Object newInstanceProxy(){
		return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
				
	}
	

//	@Override
//	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//		System.out.println("log in ");
//		Object result = method.invoke(proxy, args) ;
//		System.out.println("log out ");
//		return result ;
//	}
	
	

}
