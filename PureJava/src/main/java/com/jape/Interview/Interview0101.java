package com.jape.Interview;

/**
 * ʵ��һ���㷨��ȷ��һ���ַ��� s �������ַ��Ƿ�ȫ����ͬ��
 *
 * ʾ�� 1��
 * ����: s = "leetcode"
 * ���: false
 *
 * ʾ�� 2��
 * ����: s = "abc"
 * ���: true
 *
 * ���ƣ�
 * 0 <= len(s) <= 100
 * ����㲻ʹ�ö�������ݽṹ����ܼӷ֡�
 *
 * ��Դ�����ۣ�LeetCode��
 * ���ӣ�https://leetcode-cn.com/problems/is-unique-lcci
 * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 */
public class Interview0101 {

    public static void main(String[] args) {
        Interview0101 interview = new Interview0101();
        String s1 = "leetcode";
        String s2 = "abc";

        System.err.println(interview.isUnique(s2));

    }

    public boolean isUnique(String astr) {
        for (int i = 0; i < astr.length(); i++) {
            System.err.println("���["+ astr.charAt(i) +"],���"+ astr.indexOf(astr.charAt(i),i));
            if(astr.indexOf(astr.charAt(i),i+1) != -1){
                return false;
            }
        }
        return true;
    }


}
