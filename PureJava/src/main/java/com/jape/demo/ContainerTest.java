package com.jape.demo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * �����������ԣ������Ƿ��ܷ���null
 */
public class ContainerTest {

    public static void main(String[] args) {

        //��Ϊnull
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(null);

        //��Ϊnull
        LinkedList<Object> linkedList = new LinkedList<>();
        linkedList.add(null);

        //��Ϊnull
        HashSet<Object> hashSet = new HashSet<>();
        hashSet.add(null);

        //����Ϊ��
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(null, null);

        //����Ϊ��
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(null, null);

        //������Ϊnull
        Hashtable<Object, Object> hashtable = new Hashtable<>();
        hashtable.put("", "");

        //������Ϊnull
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("", "");

        //key����Ϊnull
        TreeMap<Object, Object> treeMap = new TreeMap<>();
        treeMap.put("", null);

        //����Ϊnull
        TreeSet<Object> treeSet = new TreeSet<>();
        treeSet.add("");

        //˳����������ڲ�Ϊlong���飬һ��long8Byte,64λ
        BitSet bitSet = new BitSet();
        bitSet.set(0, 5);//11111-31
        bitSet.clear(1,3);//11001-25

    }
}
