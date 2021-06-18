package com.jape.concurrent.Juc.ProductConsumer;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {

        //�ź���
        Semaphore semaphore = new Semaphore(3);

        for(int i = 0; i < 10; i++){
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "���һ���ź���");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.err.println(Thread.currentThread().getName() + "�ͷ�һ���ź���");
                    semaphore.release();
                }
            }).start();
        }


    }
}
