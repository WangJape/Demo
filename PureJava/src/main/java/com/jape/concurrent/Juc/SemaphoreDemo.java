package com.jape.concurrent.Juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore(�ź���)
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        // �̳߳�
        ExecutorService exec = Executors.newCachedThreadPool();
        // ֻ��5���߳�ͬʱ����
        final Semaphore semp = new Semaphore(5);

        Runnable run = ()-> {
            try {
                // ��ȡ���
                semp.acquire();
                System.out.println("Accessing: " + Thread.currentThread().getName());

                //ģ��ʵ��ҵ���߼�
                Thread.sleep((long) (Math.random() * 10000));
            } catch (InterruptedException e) {
            } finally {
                // ��������ͷ�
                semp.release();
            }
        };
        // ģ��20���ͻ��˷���
        for (int index = 0; index < 20; index++) {
            exec.execute(run);
        }
        //System.out.println(semp.getQueueLength());

        // �˳��̳߳�
        //exec.shutdown();
        //System.err.println("�̳߳عر�");
    }

}
