package com.jape.designMode;

/**
 * װ����ģʽ
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        GameCharacter angele = new Angela();
        angele.attack();

        GameCharacter skinnedAngele = new MindHacker(angele);
        skinnedAngele.attack();
    }
}

//��Ϸ����
interface GameCharacter{
    void attack();
}

class Angela implements GameCharacter{
    @Override
    public void attack() {
        System.err.println("��ͨ�������100�㷨���˺�");
    }
}

//Ƥ��������
abstract class appearance implements GameCharacter{
    GameCharacter gameCharacter;
    public appearance(GameCharacter gameCharacter) {
        this.gameCharacter = gameCharacter;
    }
    @Override
    public void attack() {
        gameCharacter.attack();
    }
}

//���麧��-Ƥ��
class MindHacker extends appearance{
    public MindHacker(GameCharacter gameCharacter) {
        super(gameCharacter);
    }
    @Override
    public void attack() {
        AttackSpecialEffects();
        gameCharacter.attack();
    }
    //������Ч
    private void AttackSpecialEffects(){
        System.err.print("��ͨ�����Ǵ�����ĸ��״->");
    }
}


