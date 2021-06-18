package com.jape.Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ʵ��һ���򵥵���Ϸս���߼�
 * Ҫ��:
 * 1��һ�������࣬һ����Ԫ��
 * 2������x ��������ÿ������������y ����Ԫ��
 * 3��ÿ����Ԫ��100������ֵ������ֵС��1ʱ��������
 * 4��ÿ���غ�ÿ��Ԫ�����һ�����ŵ�������Ԫ���10��15���˺���ֱ��û���������ŵĵ�Ԫ��ս��������
 * 5�����߳���ɡ��������ȡ���ӡ���ս�����̡�
 */
public class Game1 {


    public static void main(String[] args) {

        int x = 2;//����
        int y = 5;//��Ԫ

        ExecutorService threadPool = Executors.newFixedThreadPool(x);
        //CyclicBarrier barrier = new CyclicBarrier(x);
        CountDownLatch latch = new CountDownLatch(x);

        /**
         * ѭ�����г����Ĺ����غϣ�ֱ����ʤ����
         */
        Runnable fighting = () -> {
            System.out.println("[" + Thread.currentThread().getName() + "]�߳����С�");
            Scenes scenes = new Scenes(y);
            while (scenes.roundStart() != 1) {
                System.err.println("һ���غϽ���");
            }
            try {
                latch.countDown();
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("[" + Thread.currentThread().getName() + "]������Ϸ��ʤ���ߣ�" + scenes.getSurviveUnits().get(0).name);
        };

        for (int i = 0; i < x; i++) {
            threadPool.execute(fighting);
        }
        threadPool.shutdown();
    }

}

/**
 * ����
 */
class Scenes {

    private List<Unit> surviveUnits;

    private List<Unit> deadUnits;

    public Scenes(int y) {
        surviveUnits = new ArrayList<>();
        deadUnits = new ArrayList<>();
        for (int i = 0; i < y; i++) {
            surviveUnits.add(new Unit("��Ԫ" + i));
        }
    }

    /**
     * �ڳ����п�ʼһ�λغϣ������ַ����ι���
     * @return
     */
    public int roundStart() {
        int surviveNum = surviveUnits.size();
        for (int i = 0; i < surviveNum; i++) {
            int attackIndex = generateAttackIndex(i);
            int attackValue = generateAttackValue();

            Unit attackTarget = surviveUnits.get(attackIndex);
            if (attackTarget.minusLife(attackValue) < 1) {
                surviveUnits.remove(attackIndex);
                deadUnits.add(attackTarget);
                System.err.println(attackTarget.name + "����");
                surviveNum = surviveUnits.size();
            }
        }
        return surviveUnits.size();
    }

    /**
     * ����Ҫ������Ŀ�����������ֵ
     * @param currentIndex
     * @return
     */
    private int generateAttackIndex(int currentIndex) {
        int surviveNum = surviveUnits.size();
        Random r = new Random();
        int attackIndex = r.nextInt(surviveNum-1);
        if (attackIndex >= currentIndex) {
            attackIndex += 1;
        }
        System.err.print(surviveUnits.get(currentIndex).name + "-����-" + surviveUnits.get(attackIndex).name);
        return attackIndex;
    }

    /**
     * ����Ҫ�����۳�������ֵ
     * @return
     */
    private int generateAttackValue() {
        Random r = new Random();
        int attackValue = r.nextInt(6) + 10;
        System.err.print(",����ֵ�۳�" + attackValue);
        return attackValue;
    }

    /**
     * �õ���ǰ���Ķ���
     * @return
     */
    public List<Unit> getSurviveUnits(){
        return surviveUnits;
    }

}

/**
 * ��Ԫ
 */
class Unit {

    public String name;

    private int lifeValue = 100;

    private boolean isDead = false;

    public Unit(String name){
        this.name = name;
    }

    /**
     * �۳�����ֵ
     * @param value
     * @return
     */
    public int minusLife(int value) {
        lifeValue -= value;
        System.err.println(",ʣ��Ѫ����" + lifeValue);
        return lifeValue;
    }

}