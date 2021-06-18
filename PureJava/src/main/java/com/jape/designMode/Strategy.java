package com.jape.designMode;

/**
 * ����ģʽ
 * һ�������Ϊ�����㷨����������ʱ���ġ�
 * �������͵����ģʽ������Ϊ��ģʽ
 * �ڲ���ģʽ�У����Ǵ�����ʾ���ֲ��ԵĶ����һ����Ϊ���Ų��Զ���ı���ı�� context ����
 * ���Զ���ı� context �����ִ���㷨��
 */
public class Strategy {
    public static void main(String[] args) {
        Zombie zombie1 = new SimpleZombie();
        zombie1.setMoveAble(new MoveNormal());
        zombie1.setAttackAble(new AttackEat());
        zombie1.move();
        zombie1.attack();

        zombie1.setMoveAble(new MoveQuickly());
        zombie1.setAttackAble(new AttackStolenFromTheSky());
        zombie1.move();
        zombie1.attack();
    }
}

//�ƶ�����
interface MoveAble{
    void move();
}

class MoveNormal implements MoveAble{
    @Override
    public void move() {
        System.err.println("����1X������ǰ��");
    }
}

class MoveQuickly implements MoveAble{
    @Override
    public void move() {
        System.err.println("����2X��ǰ��");
    }
}

//��������
interface AttackAble{
    void attack();
}

class AttackEat implements AttackAble{
    @Override
    public void attack() {
        System.err.println("����ץ�ų�ֲ��");
    }
}

class AttackStolenFromTheSky implements AttackAble{
    @Override
    public void attack() {
        System.err.println("������͵��ֲ��");
    }
}

//��ʬ
abstract class Zombie{
    MoveAble moveAble;
    AttackAble attackAble;

    abstract void appearance();
    abstract void move();
    abstract void attack();

    public MoveAble getMoveAble() {
        return moveAble;
    }

    public void setMoveAble(MoveAble moveAble) {
        this.moveAble = moveAble;
    }

    public AttackAble getAttackAble() {
        return attackAble;
    }

    public void setAttackAble(AttackAble attackAble) {
        this.attackAble = attackAble;
    }
}

class SimpleZombie extends Zombie{

    @Override
    void appearance() {
        System.err.println("��ͨ��ʬ");
    }

    @Override
    void move() {
        moveAble.move();
    }

    @Override
    void attack() {
        attackAble.attack();
    }
}


