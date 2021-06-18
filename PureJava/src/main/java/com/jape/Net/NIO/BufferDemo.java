package com.jape.Net.NIO;

import java.nio.IntBuffer;

/**
 * Buffer�ײ���һ�����飬һ��Buffer��Ӧһ��Channel
 * ��˫��ģ��ɶ���д��ʹ��flip()�л�
 */
public class BufferDemo {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }
        //�л���д
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            System.err.println(intBuffer.get());
        }
    }
}
