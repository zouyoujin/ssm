package com.ssm.common.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 一、CountDownLatch 
 * 1.应用场景 
 *    在实际多线程并发开发过程中，我们会碰见很多等待子线程完毕后在继续执行的情况，（如多个子线程下载文件，所有子线程执行完毕后再重命名为文件名）。 
 * 2.使用方式 
 *    CountDownLatch的构造函数接受一个int类型的参数作为计数器，调用countDwon()方法，计数器减1，await()方法阻塞当前线程，直到计数器变为0；
 * 补充： 
 *    计数器为0的时候，调用awaite()方法不会阻塞主线程； 
 *    初始化后，不能修改计数器的值； 
 *    可以使用await(long time,TimeUnit unit)等待特定时间后，就不阻塞主线程；
 * 
 * @author kitty
 *
 */
public class TCountDownLatch {

	// 等待2个子线程执行完毕，计数器为2
	static CountDownLatch countDownLatch = new CountDownLatch(2);

	public static void main(String[] args) {
		System.out.println("start subThread doing...");
		// 创建并开启2个子线程
		SubThread subThread1 = new SubThread();
		SubThread subThread2 = new SubThread();
		subThread1.start();
		subThread2.start();

		try {
			// 阻塞主线程，等待子线程结束
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("subThread are finish...");
	}

	static class SubThread extends Thread {
		@Override
		public void run() {
			// 模拟执行任务
			try {
				sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 子线程执行完毕，减少计数器
			System.out.println(getName() + " done...");
			countDownLatch.countDown();
		}
	}

}
