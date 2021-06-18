package com.jape.concurrent.Juc;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �ɻ��ռ���������ͬ���������ϣ������CountDownLatch�ɻ��ռ���
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        Runnable callback = ()->{
            System.err.println("һ�ּ����Ѿ����");
        };

        CyclicBarrier barrier = new CyclicBarrier(2, callback);

        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName()+"��ʼ��һ�ֵļ�������");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"���ݼ������,�ȴ������̼߳�����");
                barrier.await();
                System.err.println(Thread.currentThread().getName()+"��ʼ����");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 5; i++) {
            threadPool.execute(task);
            threadPool.execute(task);

        }


    }

}
