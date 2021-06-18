package com.jape.concurrent.Juc;

import java.util.concurrent.CountDownLatch;

/**
 * ����ʱͬ�������ȴ������߳�ִ�����
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"��������");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"���ݼ������,�ȴ������̼߳�����");
                latch.countDown();
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(Thread.currentThread().getName()+"��ʼ����");
        }).start();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"��������");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName()+"���ݼ������,�ȴ������̼߳�����");
                latch.countDown();
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.err.println(Thread.currentThread().getName()+"��ʼ����");
        }).start();

    }

}
