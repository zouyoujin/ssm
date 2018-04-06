package com.ssm.common.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/**
 * 二、CyclicBarrier 
 *   1.应用场景 
 *        如果当你遇见需要让一组线程达到同一个屏障（同步点）时被阻塞，直到最后一个线程达到屏障时，屏障才会打开的情况。 
 *   2.使用方式 
 *        CycliBarrier默认的构造方法CyclicBarrier(int parties)，参数标识屏障拦截的线程个数，每个线程调用await()方法告诉SyclicBarrier我们已经达到屏障了，然后当前线程被阻塞。当所有子线程都达到屏障后，则继续执行子线程的后续逻辑。 
 *   补充： 
 *        CyclicBarrier还提供了一个更高级的函数CyclicBarrier(int parties,Runnable barrierAction），用于在线程达到屏障时，优先执行barrierAction。 
 * 
 * @author kitty
 *
 */
public class TCyclicBarrier {

	// 拦截2个子线程屏障
	static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

	public static void main(String[] args) {
		System.out.println("start subThread doing...");
		SubThread subThread1 = new SubThread();
		SubThread subThread2 = new SubThread();
		subThread1.start();
		subThread2.start();
	}

	static class SubThread extends Thread {
		@Override
		public void run() {
			try {
				System.out.println(getName() + " doing first things.");
				// 模拟子线程执行第一个任务
				sleep(3000);
				System.out.println(getName() + " done first things.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				// 完成第一个任务，告知达到屏障
				cyclicBarrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}

			// 所有子线程都完成第一个任务后，继续运行每个子线程的下一个任务
			System.out.println(getName() + " doing other things.");
		}
	}
}
