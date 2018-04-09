package com.melt.test.design.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;

public class TransCallbackFilter implements CallbackFilter {

	@Override
	public int accept(Method method) {
		String name = method.getName() ;
		if("save".equals(name)){
			return 0 ;
		} else if("query2".equals(name)){
			return 2 ;
		}
		return 1;
	}

}
