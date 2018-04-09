/**
 * 
 */
package com.melt.test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author rhguo
 *
 * 2018-01-11 上午10:50:12
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		Semaphore mutex = new Semaphore(5,true) ;
		for(int i = 0; i < 10; i++){
			new Thread(new ResourceTask(mutex)).start();
		}
	}
	
}
class ResourceTask implements Runnable {
	private final Semaphore mutex ;
	
	public ResourceTask(Semaphore mutex) {
		super();
		this.mutex = mutex;
	}

	@Override
	public void run() {
		String name  = Thread.currentThread().getName() ;
		try {
			System.out.println(name + "尝试获取到资源。。。。");
			mutex.acquire();
			System.out.println(name + "成功获取到资源");
			System.out.println(name + "操作资源中");
			TimeUnit.SECONDS.sleep(2);
			System.out.println(name + "操作资源完成,释放资源占用");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.release();
		}
		
	}
	
}
