package com.jape.Net.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * һ��channel�����Ӧһ��buffer,����buffer����
 *
 *
 * FileChannel
 * ServerSocketChannel -> SocketChannel
 *
 */
public class ChannelDemo {

    public static void main(String[] args) throws IOException {

        // ����ͨ��ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // �󶨶˿ڲ�����
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        // ����buffer,����ʵ��һ�����飬����Buffer�ķ�ɢ��ۼ�
        ByteBuffer[] buffers = new ByteBuffer[2];
        buffers[0] = ByteBuffer.allocate(256);
        buffers[1] = ByteBuffer.allocate(512);

        while (true){
            // �ȴ��ͻ�������
            SocketChannel socketChannel = serverSocketChannel.accept();

            // ��ͨ������buffer��
            socketChannel.read(buffers);

            //�����������ֻ�ǲ���
            Arrays.asList(buffers).forEach(byteBuffer -> {
                String request = new String(byteBuffer.array());
                System.err.println(request);
            });

            // bufferд�뷭תΪ��ȡ
            Arrays.asList(buffers).forEach(byteBuffer -> byteBuffer.flip());

            // ������Ӧ
            socketChannel.write(buffers);

            // ���buffers
            Arrays.asList(buffers).forEach(byteBuffer -> byteBuffer.clear());
        }

    }

}
