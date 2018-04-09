package com.melt.test.design.factory.abstr;

public class SpringButton implements Button{

	@Override
	public void click() {
		System.out.println("i am spring button");
	}
	
}
