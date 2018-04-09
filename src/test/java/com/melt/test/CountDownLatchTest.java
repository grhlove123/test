package com.melt.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

	public static void main(String[] args) throws Exception {
		CountDownLatch latch = new CountDownLatch(3) ;
		ExecutorService executor = Executors.newFixedThreadPool(3) ;
		for(int i=0 ; i<3;i++){
			executor.execute(new Task(latch));
		}
		System.out.println("主线程继续进行");


		latch.await();
//		new Thread(new Task2(latch)).start();
		
		System.out.println("所有子线程执行完成");
		executor.shutdown();
	}
	
	static class Task implements Runnable {
		private CountDownLatch latch ;

		public Task(CountDownLatch latch) {
			super();
			this.latch = latch;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "正在计算");
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			latch.countDown();
			
		}
		
	}

//	static class Task2 implements Runnable {
//		private CountDownLatch latch ;
//
//		public Task2(CountDownLatch latch) {
//			super();
//			this.latch = latch;
//		}
//
//		@Override
//		public void run() {
//
//			try {
//				latch.await();
//				System.out.println("11111111111");
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//
//		}
//
//	}
}


