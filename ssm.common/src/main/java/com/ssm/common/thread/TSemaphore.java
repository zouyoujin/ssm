package com.ssm.common.thread;

import java.util.concurrent.Semaphore;

/**
 * 三、Semaphore 
 *      1.应用场景 
 *           多线程访问公共资源的情况在开发过程中经常遇见，如数据库连接，可能开启几十个线程进行并发读取，但是考虑到数据库连接性能和消耗，我们必须控制10个线程哪个是连接数据库。Semaphore就是用来控制同时访问特定资源的线程数量。 
 *      2.使用方式 
 *           Semaphore的构造方法Semaphore(int permits)，permits标识许可证数量。执行任务前，acquire()方法获取一个许可证；任务执行完成后调用relese()方法归还许可证。没有获得许可证的子线程就阻塞等待。 
 *      补充： 
 *           tryAcquire()：尝试获取许可证； 
 *           intavaliablePermits()：返回信号量中当前许可证的个数； 
 *           intgetQueueLength()：返回正在等待获取许可证的线程个数； 
 *           booleanhasQueueThreads()：是否有线程正在等待许可证； 
 *           reducePermits(int reduction)：减少reduction个许可证； 
 *           getQueuedThreads()：返回所有等待获取许可证的线程集合； 
 *
 * @author kitty
 *
 */
public class TSemaphore {

	// 创建2个许可证
	static Semaphore semaphore = new Semaphore(2);

	public static void main(String[] args) {
		System.out.println("start subThread doing...");
		// 同时开启4个子线程运行
		for (int i = 0; i < 4; i++) {
			SubThread subThread = new SubThread();
			subThread.start();
		}
	}

	static class SubThread extends Thread {
		@Override
		public void run() {
			try {
				// 执行任务前获取许可证
				semaphore.acquire();
				System.out.println(getName() + "doing things.");
				sleep(3000);
				// 执行完任务释放许可证
				semaphore.release();
				System.out.println(getName() + "finish things.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
