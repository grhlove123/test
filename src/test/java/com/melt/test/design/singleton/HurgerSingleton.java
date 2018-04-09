package com.melt.test.design.singleton;

/**
 * 饿汉式
 *
 * @author rhguo
 *
 * 2018-01-11 下午2:06:38
 */
public class HurgerSingleton {
	
	private static final HurgerSingleton instance = new HurgerSingleton() ;
	
	private HurgerSingleton(){}
	
	public static HurgerSingleton newInstance(){
		return instance ;
	}

}
