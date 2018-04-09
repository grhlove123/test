package com.melt.test.design.factory.simple;

public class Test {

	public static void main(String[] args) {
		Human human = HumanFactory.newInstance(1) ;
		human.sayHi(null);
		human = HumanFactory.newInstance(2) ;
		human.sayHi(null);
	}
}
