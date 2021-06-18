package com.jape.designMode;

public class TemplateMethod {
    public static void main(String[] args) {
        Template demo = new ImplTemplateMethod();
        demo.doSomething();
    }
}

abstract class Template{
    public void doSomething(){
        System.err.println("��ģ�巽��ǰ�Ĳ���");
        method();
        System.err.println("��ģ�巽����Ĳ���");
    }
    abstract void method();
}

class ImplTemplateMethod extends Template {

    @Override
    void method() {
        System.err.println("��������Ӻ�������ʵ��");
    }
}
