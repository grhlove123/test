package com.melt.test.design.proxy.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class Test02 {

	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer() ;
		enhancer.setSuperclass(UserServiceImpl.class);
		/**
		 * 针对不同方法执行不同的过滤：
		 * CallbackFilter里指定下标,下标和Callbacks相对应
		 * callback可以为：
		 * 自定义、NoOp.INSTANCE(不过滤，和没有代理效果一样、FixedValue固定值)
		 */
		enhancer.setCallbackFilter(new TransCallbackFilter());
		Callback[] callbacks = {new TrascMethodInterceptor(),NoOp.INSTANCE,new MyFixedValue()} ;
		enhancer.setCallbacks(callbacks);
//		enhancer.setCallback(new TrascMethodInterceptor());
		UserServiceImpl service = (UserServiceImpl)enhancer.create() ;
		service.save(null) ;
		service.query(null);
		service.query2(null);
	}

}
