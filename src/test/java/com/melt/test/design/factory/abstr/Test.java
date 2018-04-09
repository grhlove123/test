package com.melt.test.design.factory.abstr;

public class Test {

	public static void main(String[] args) {
		/**
		 * 可以通过读取配置文件获取工厂类
		 */
		SkinFactory sf = new SpringFactory() ;
		
		sf.createButton().click();
		sf.createTextField().display();
	}
}
