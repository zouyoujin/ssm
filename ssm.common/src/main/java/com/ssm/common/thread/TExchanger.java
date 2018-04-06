package com.ssm.common.thread;

import java.util.concurrent.Exchanger;

/**
 * 四、Exchanger 
 * 1.应用场景 
 *      在某些实际业务如流水录入中，为了避免错误。采用两个人同时录入，并对比录入的结果是否一致。Exchanger用于进行线程之间的数据交换，它提供了一个同步点，两个线程可以交换彼此的数据。 
 * 2.使用方式 
 *      两个线程通过exchange()方法交换数据，如果一个线程执行exchange()方法，它会一直等待第二个线程也执行exchange()方法。当两个线程都达到同步点时，就可以交换数据，将本线程产生的数据传递给对方。 
 *
 * @author kitty
 *
 */
public class TExchanger {
	// 用户线程间交换数据(String)对象exchanger
	static Exchanger<String> exchanger = new Exchanger<String>();

	public static void main(String[] args) {
		// 创建2个子线程分别执行
		SubThread1 subThread1 = new SubThread1();
		SubThread2 subThread2 = new SubThread2();
		subThread1.start();
		subThread2.start();
	}

	static class SubThread1 extends Thread {
		@Override
		public void run() {
			try {
				System.out.println(getName() + "start doing...");
				// 模拟执行完成后，获取结果result1，并将result1交换给对方线程
				sleep(3000);
				String result1 = "3000";
				String result2 = exchanger.exchange(result1);
				// 待两个线程都执行完毕后，交换数据进行比较
				System.out.println(getName() + " thread1 result:" + result1 + " is equals thread2 result:" + result2
						+ "," + result1.equals(result2));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static class SubThread2 extends Thread {   
        @Override   
        public void run() {   
            try {   
                System.out.println(getName() + "start doing...");   
                //模拟执行完成后，获取结果result2，并将result2交换给对方线程   
                sleep(2000);   
                String result2 = "2000";   
                String result1 = exchanger.exchange(result2);   
                //待两个线程都执行完毕后，交换数据进行比较   
                System.out.println(getName() + " thread1 result:" + result1 + " is equals thread2 result:" + result2 +   
                        "," + result1.equals(result2));   
            } catch (InterruptedException e) {   
                e.printStackTrace();   
            }   
        }   
	}
}
