package com.jape.concurrent.Juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueDemo {

    public static void main(String[] args) {

        /**
         * �����ģ�put(),take()
         * �������ģ�add(),remove()
         */
        BlockingQueue queue = new ArrayBlockingQueue(100);
        new LinkedBlockingQueue<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int n = 0;
                String msg;
                while (true) {
                    n++;
                    try {
                        msg = "��" + n + "����Ϣ";
                        queue.put(msg);
                        System.err.println("������Ϣ[" + msg + "]����ǰ����������ʣ����Ϣ����[" + queue.size() + "]");
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int n = 0;
                while (true) {
                    n++;
                    String msg;
                    try {
                        msg = (String) queue.take();
                        System.out.println("���յ���Ϣ��[" + msg + "]����ǰ����������ʣ����Ϣ����[" + queue.size() + "]");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
        //queue.clear();


    }

}
