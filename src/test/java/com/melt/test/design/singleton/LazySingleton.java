package com.melt.test.design.singleton;

/**
 * 懒汉式
 *
 * @author rhguo
 *
 * 2018-01-11 下午2:06:38
 */
public class LazySingleton {
	
	private static volatile LazySingleton instance = null  ;
	
	private LazySingleton(){}
	
	public static LazySingleton newInstance(){
		
		if(instance == null){
			synchronized (LazySingleton.class) {
				if(instance == null){
					/**
					 * 包含：
					 * 1. 分配内存
					 * 2. 把内存地址赋值给引用
					 * 3. 初始化对象（花时间）
					 * 
					 * 为了给一个初始完成后的数据，所以引用了临时变量
					 */
					LazySingleton tmp = new LazySingleton() ;
					instance = tmp;
				}
			}
		}
		
		return instance ;
	}

}
