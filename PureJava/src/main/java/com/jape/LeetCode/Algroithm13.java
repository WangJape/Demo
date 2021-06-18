package com.jape.LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * �������ְ������������ַ�:?I��?V��?X��?L��C��D?��?M��
 *
 * �ַ�          ��ֵ
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * ���磬 �������� 2 д��?II?����Ϊ�������е� 1��12 д��?XII?����Ϊ?X?+?II?�� 27 д��??XXVII, ��Ϊ?XX?+?V?+?II?��
 *
 * ͨ������£�����������С�������ڴ�����ֵ��ұߡ���Ҳ�������������� 4 ��д��?IIII������?IV������ 1 ������ 5 ����ߣ�����ʾ�������ڴ��� 5 ��С�� 1 �õ�����ֵ 4 ��ͬ���أ����� 9 ��ʾΪ?IX���������Ĺ���ֻ�������������������
 *
 * I?���Է���?V?(5) ��?X?(10) ����ߣ�����ʾ 4 �� 9��
 * X?���Է���?L?(50) ��?C?(100) ����ߣ�����ʾ 40 ��?90��?
 * C?���Է���?D?(500) ��?M?(1000) ����ߣ�����ʾ?400 ��?900��
 * ����һ���������֣�����ת��������������ȷ���� 1?�� 3999 �ķ�Χ�ڡ�
 *
 * ʾ��?1:
 *
 * ����:?"III"
 * ���: 3
 * ʾ��?2:
 *
 * ����:?"IV"
 * ���: 4
 * ʾ��?3:
 *
 * ����:?"IX"
 * ���: 9
 * ʾ��?4:
 *
 * ����:?"LVIII"
 * ���: 58
 * ����: L = 50, V= 5, III = 3.
 * ʾ��?5:
 *
 * ����:?"MCMXCIV"
 * ���: 1994
 * ����: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * ��Դ�����ۣ�LeetCode��
 * ���ӣ�https://leetcode-cn.com/problems/roman-to-integer
 * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 */
public class Algroithm13 {

    public static void main(String[] args) {
        Algroithm13 algroithm13 = new Algroithm13();

        String romanStr = "IX";

        long startTime=System.nanoTime();
        System.err.println(algroithm13.romanToInt(romanStr));
        long time=System.nanoTime()-startTime;
        System.out.println("��������ʱ�䣺["+time+"]ns");

    }

    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap(16);
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int sum = 0, number = 0, preNumber = 0;
        for (int i = 0; i < s.length(); i++) {
            number = map.get(s.charAt(i));
            sum += number;
            if(preNumber < number && preNumber*10 >= number){
                sum -= 2* preNumber;
            }
            preNumber = number;
        }
        int a=1,b=2;
        return sum;
    }

    /**
     * 58%,5%
     * @param s
     * @return
     */
    public int romanToInt1(String s) {
        Map<Character, Integer> map = new HashMap();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int number = 0;
        char roman, nextRoman;
        for (int i = 0; i < s.length(); i++) {
            roman = s.charAt(i);
            number += map.get(roman);
            if(i+1 < s.length()){
                nextRoman = s.charAt(i+1);
                switch (roman){
                    case 'I': {
                        if(nextRoman=='V' || nextRoman=='X'){
                            number += map.get(nextRoman) - 2 * map.get(roman);
                            i++;
                        }
                        break;
                    }
                    case 'X': {
                        if(nextRoman=='L' || nextRoman=='C'){
                            number += map.get(nextRoman) - 2 * map.get(roman);
                            i++;
                        }
                        break;
                    }
                    case 'C': {
                        if(nextRoman=='D' || nextRoman=='M'){
                            number += map.get(nextRoman) - 2 * map.get(roman);
                            i++;
                        }
                        break;
                    }
                }
            }
        }
        return number;
    }

}
