package com.melt.test.design.proxy.cglib;

import net.sf.cglib.proxy.FixedValue;

public class MyFixedValue implements FixedValue {

	@Override
	public Object loadObject() throws Exception {
		System.out.println("fixed value");
		return 999;
	}

}
