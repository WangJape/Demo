package com.jape.concurrent.Juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

/**
 * Java 8�������µĶ�д�� StampedLock
 * ���л�ȡ���ķ�����������һ���ʴ���Stamp����StampΪ0��ʾ��ȡʧ�ܣ����඼��ʾ�ɹ���
 * �����ͷ����ķ���������Ҫһ���ʴ���Stamp�������Stamp�����Ǻͳɹ���ȡ��ʱ�õ���Stampһ��
 *
 *
 * StampedLock�ṩ���ֹ۶�������ȡ��ReadWriteLock�Խ�һ��������������
 * StampedLock �ǲ���������
 * StampedLock��ReadWriteLock��ȣ��Ľ�֮�����ڣ�
 *  ���Ĺ�����Ҳ�����ȡд����д�룡����һ�������Ƕ������ݾͿ��ܲ�һ�£����ԣ���Ҫһ�����Ĵ������ж϶��Ĺ������Ƿ���д�룬���ֶ�����һ���ֹ�����
 *  �ֹ�������˼�����ֹ۵ع��ƶ��Ĺ����д���ʲ�����д�룬��˱���Ϊ�ֹ�����
 *  �����������������Ƕ��Ĺ����оܾ���д�룬Ҳ����д�����ȴ�����Ȼ�ֹ����Ĳ���Ч�ʸ��ߣ���һ����С���ʵ�д�뵼�¶�ȡ�����ݲ�һ�£���Ҫ�ܼ��������ٶ�һ����С�
 */
public class StampedLockDemo {

    private double x;
    private StampedLock s1 = new StampedLock();//����StampedLock��

    void write(double deltaX) {
        long stamp = s1.writeLock();//д��
        try {
            x += deltaX;
            System.out.println(Thread.currentThread().getName() + "д��:" +deltaX);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            s1.unlockWrite(stamp);//�˳��ٽ���,�ͷ�д��
        }
    }

    double read() {//ֻ������
        long stamp = s1.tryOptimisticRead();  //��ͼ����һ���ֹ۶� ����һ��������ʱ������ʴ�����stamp ���stamp�Ϳ�����Ϊ��һ������ȡ��ƾ֤
        double currentX = x;//��ȡx��y��ֵ,��ʱ�����ǲ���ȷ��x��y�Ƿ���һ�µ�
        /**
         * �ж����stamp�Ƿ��ڶ����̷����ڼ䱻�޸Ĺ�,
         * ���stampû�б��޸Ĺ�,����Ϊ��ζ�ȡʱ��Ч��,��˾Ϳ���ֱ��return��,
         * ��֮,���stamp�ǲ����õ�,����ζ���ڶ�ȡ�Ĺ�����,���ܱ������̸߳�д������,
         * ���,�п��ܳ������,��������������,���ǿ�����CAS����������һ����ѭ����һֱʹ���ֹ���,֪���ɹ�Ϊֹ
         */
        if (!s1.validate(stamp)) {
            System.err.println(Thread.currentThread().getName() + "��ȡʱ����д�룬��Ϊ���۶���");
            stamp = s1.readLock();//�����ֹ����ļ���,���ֹ�����Ϊ������, �����ǰ�������ڱ��޸�,�������������ܵ����̹߳���.
            try {
                currentX = x;
            } finally {
                s1.unlockRead(stamp);//�˳��ٽ���,�ͷŶ���
            }
        }
        return currentX;
    }





    public static void main(String[] args) throws InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        StampedLockDemo a = new StampedLockDemo();

        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.err.println(Thread.currentThread().getName() + "����:" +a.read());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                while(true){
                    a.write(1);
                    System.out.println(Thread.currentThread().getName() + "д�����");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        threadPool.execute(writeTask);
        Thread.sleep(1600);
        threadPool.execute(writeTask);
        threadPool.execute(readTask);
        threadPool.shutdown();

    }


}
