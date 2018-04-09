package com.melt.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureTest {

	public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newCachedThreadPool();
		Task task = new Task() ;
		Future<Integer> result = executor.submit(task) ;
		System.out.println("主线程执行任务...");
		TimeUnit.SECONDS.sleep(3);
		System.out.println("执行结果：" + result.get());
		
		System.out.println("所有任务执行完成");
		
	}
	
	
}
class Task implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		System.out.println("子线程运行中");
		TimeUnit.SECONDS.sleep(2);
		int sum = 0;
		for(int i = 0; i < 100;i++){
			sum += i ;
//			if( i== 10){
//				throw new Exception("子任务异常") ;
//			}
		}
		return sum;
	}
	
}
