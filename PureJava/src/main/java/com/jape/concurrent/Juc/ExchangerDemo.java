package com.jape.concurrent.Juc;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger �� JDK 1.5 ��ʼ�ṩ��һ���������������߳�֮�佻�����ݵķ�װ�����࣬
 *  ��˵����һ���߳������һ���������������һ���߳̽������ݣ�
 *  ���һ�����ó����ݵ��̻߳�һֱ�ȴ��ڶ����̣߳�
 *  ֱ���ڶ����߳��������ݵ���ʱ���ܱ˴˽�����Ӧ���ݡ�
 *
 * ��һ���̵߳��� exchange ���õ�ʱ��
 * ��������̴߳�ǰ�Ѿ������˴˷�����
 * �������̻߳ᱻ���Ȼ��Ѳ���֮���ж��󽻻���Ȼ����Է��أ�
 *
 * ��������̻߳�û���ｻ���㣬��ǰ�̻߳ᱻ����
 * ֱ�������̵߳���Ż���ɽ������������أ����ߵ�ǰ�̱߳��жϻ�ʱ����
 *
 */
public class ExchangerDemo {

    public void a(Exchanger<String> exch){
        System.out.println("a����ִ��");
        try {
            Thread.sleep(2000);
            System.out.println("a�������ݽ�������");
            for(int i = 0;i < 10; i++){
                String result = exch.exchange("12345");
                System.err.println("a�������ص�����Ϊ["+ result +"]");
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void b(Exchanger<String> exch){
        System.out.println("b����ִ��");
        try {
            Thread.sleep(1000);
            System.out.println("b�������ݽ�������");
            for(int i = 0;i < 10; i++){
                String result = exch.exchange("��ɽ������");
                System.err.println("b�������ص�����Ϊ["+ result +"]");
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        ExchangerDemo task = new ExchangerDemo();
        Exchanger exch = new Exchanger();
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                task.a(exch);
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                task.b(exch);
            }
        });

    }

}
