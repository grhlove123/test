package com.melt.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

	public static void main(String[] args) throws Exception {
		int n = 3 ;
		CyclicBarrier barrier = new CyclicBarrier(n,new Runnable() {
			
			@Override
			public void run() {
				System.out.println("nice,所有写入已完成，可以读了" + Thread.currentThread().getName());
			}
		}) ;
		for(int i=0;i<n;i++){
			new Thread(new Writer(barrier)).start();
		}
		
//		Thread.sleep(5000);
//		barrier.reset();
//		for(int i=0;i<n;i++){
//			new Thread(new Writer(barrier)).start();
//		}
	}
	
	static class Writer implements Runnable{

		private CyclicBarrier barrier ;
		
		public Writer(CyclicBarrier barrier) {
			super();
			this.barrier = barrier;
		}

		public void run() {
			System.out.println(Thread.currentThread().getName() + "子线程正在解析入库..");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "子线程完成");
			
			try {
				barrier.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "继续做自已的事");
		}
		
	}

}
