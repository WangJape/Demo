package com.jape.concurrent;

import java.util.Random;
import java.util.concurrent.*;

public class ThreadPoolUseCallable{

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        Callable task = new Callable() {
            @Override
            public Integer call() throws Exception {
                System.out.print("["+ Thread.currentThread().getName() +"]�߳����С�");
                System.out.println("��ǰ����߳���["+ Thread.activeCount() +"]");
                Random r = new Random();
                return r.nextInt();
            }
        };


        /**
         * submit�з���ֵ����executeû��
         * ��Ҫ����ֵ����submit()����
         * ����Ҫ����ֵ����execute()����
         */
        for(int i = 0;i<5;i++){
            Future f = threadPool.submit(task);
            System.out.println(f.get());
        }
        threadPool.shutdown();
    }
}
