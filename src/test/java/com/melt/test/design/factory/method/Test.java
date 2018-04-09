package com.melt.test.design.factory.method;

import com.melt.test.design.factory.simple.Human;

public class Test {

	public static void main(String[] args) {
		/**
		 * 一个实现一工厂
		 */
		HumanFactory factory = new YellowFactory() ;
		Human human = factory.newInstance() ;
		human.sayHi(null);
		
		factory = new WhiteFactory() ;
		human = factory.newInstance() ;
		human.sayHi(null) ;
	}
}
