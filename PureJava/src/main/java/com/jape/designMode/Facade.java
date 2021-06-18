package com.jape.designMode;

/**
 * ����ģʽ
 * Ϊ���ӵĵײ�ҵ������һ������㣬�û�ֻ�õ�������㷽����������֪���ײ㸴�ӵ��߼�
 *
 * Tomcat�е�RequestFacadeʵ��HttpServletRequest�ӿڣ�����Request���󷽷���������ģʽ
 *  RequestFacade��ΪRequest�����棬�ڲ�����Request����
 *  ResponseFacade��ΪResponse�����棬�ڲ�����Response����
 *  StandardSessionFacade��ΪHttpSession�����棬�ڲ�����HttpSession����
 *  ApplicationContextFacade��ΪApplicationContext�����棬�ڲ�����ApplicaitonContext����
 */
public class Facade {
    public static void main(String[] args) {
        ModuleFacade moduleFacade = new ModuleFacade();
        moduleFacade.service();

    }
}

class ModuleFacade {
    //ʾ�ⷽ��������ͻ�����Ҫ�Ĺ���
    public void service(){
        ModuleA a = new ModuleA();
        a.serviceA();
        ModuleB b = new ModuleB();
        b.serviceB();
        ModuleC c = new ModuleC();
        c.serviceC();
    }
}

class ModuleA {
    //ʾ�ⷽ��
    public void serviceA(){
        System.out.println("����ModuleA�е�serviceA����");
    }
}

class ModuleB {
    //ʾ�ⷽ��
    public void serviceB(){
        System.out.println("����ModuleB�е�serviceB����");
    }
}

class ModuleC {
    //ʾ�ⷽ��
    public void serviceC(){
        System.out.println("����ModuleC�е�serviceC����");
    }
}