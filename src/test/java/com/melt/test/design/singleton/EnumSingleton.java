package com.melt.test.design.singleton;

/**
 * 枚举方式
 *
 * @author rhguo
 *
 * 2018-01-11 下午3:10:17
 */
public enum EnumSingleton {


	/**
	 * 单成员
	 */
	INSTANCE ;
	
	public void doSomething(){
		System.out.println("do.....");
	}
	
	public static void main(String[] args) {
		EnumSingleton es1 = EnumSingleton.INSTANCE ;
		System.out.println(es1 == EnumSingleton.INSTANCE);
		System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
		System.out.println(Runtime.getRuntime().freeMemory()/1024/1024);
		System.out.println(Runtime.getRuntime().availableProcessors());
	}

}
