package com.melt.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {

	private static ExecutorService executor = Executors.newFixedThreadPool(10) ;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i = 0; i < 20 ;i++){
			executor.execute(new Task());
		}
		System.out.println("main");
		System.out.println(Thread.activeCount());
		executor.shutdownNow();
		while(Thread.activeCount() > 1){
			System.out.println(Thread.activeCount());
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(Thread.activeCount() == 1){
				System.out.println("shutdown");
				executor.shutdown();
			}
			
//			System.exit(0);
		}
	}
	
	static class Task implements Runnable{

		@Override
		public void run() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getThreadGroup() + "--" + Thread.currentThread().getName());
		}
		
	}
}
