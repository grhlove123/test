package com.melt.test.design.singleton;

/**
 * 懒汉式，内部类方式
 *
 * @author rhguo
 *
 * 2018-01-11 下午2:38:50
 */
public class LazySingletonInner {
	
	private LazySingletonInner(){};

	static class LazySingletonHolder {
		static LazySingletonInner instance = new LazySingletonInner() ;
	}
	
	public static LazySingletonInner newInstance(){
		return LazySingletonHolder.instance ;
	}

	public static void main(String[] args) {
		LazySingletonInner singleTon = LazySingletonInner.newInstance() ;
	}

}
