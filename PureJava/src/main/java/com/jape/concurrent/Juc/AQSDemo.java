package com.jape.concurrent.Juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class AQSDemo {

    public static void main(String[] args) {
        MyLock lock = new MyLock();

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName()+"������������");
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"�����");
            try {
                Thread.sleep(2000);
            } catch (Exception e){
                System.err.println(e.getCause());
            } finally {
                System.out.println(Thread.currentThread().getName()+"�ͷ���");
                lock.unlock();
            }
        };

        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();

    }

}

//�Զ�����������������
class MyLock implements Lock{

    /**
     * ��ռ��
     */
    class MySync extends AbstractQueuedSynchronizer{

        /**
         * ���Ի������һ�Σ�
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0,1)){
                //�����ɹ�,���ó�����Ϊ��ǰ�߳�
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            setState(0);

            return true;
        }

        /**
         * �Ƿ���ж�ռ��
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * ����һ����������
         * @return
         */
        public Condition newCondition(){
            return new ConditionObject();
        }
    }

    private MySync sync = new MySync();


    /**
     * ���������ɹ����뵽�ȴ�����
     */
    @Override
    public void lock() {
        sync.acquire(1);
    }

    /**
     * ���жϵļ���
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    /**
     * ���Լ���һ��
     * @return
     */
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    /**
     * ����ʱ�ĳ��Լ���
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    /**
     * ����
     */
    @Override
    public void unlock() {
        sync.release(0);
    }

    /**
     * ������������
     * @return
     */
    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}