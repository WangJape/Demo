package com.jape.Interview;

import java.util.*;

/**
 * ��Ŀ1���ؼ�����ȡ�ͱ�ע��Ҫ�����£�
 * 1). �������1Ϊ�ؼ���ģ�塣
 * ģ��ĸ�ʽ���£�
 * [type1] ['-'] [value1|value2|...] [';'] [type2] ['-'] [value1|value2|...] [';'] [type3] ['-'] [value1|value2|...] ...
 * 2). �������2Ϊ�ı����ݡ�
 * 3). ���Ҫ���ڹؼ��ʺ����ע�����ͣ���'/'�ָ����ؼ��ʺ������ı�֮��ӿո�
 * ���磬
 * �������1Ϊ��"��Ա-������|�ܽ���;����-�ܽ�|�ܽ���;����-�໨��|˫�ع�;��Ӱ-�����|Ӣ��"
 * �������2Ϊ��"�໨�����ܽ����ݳ���һ�׸������ɷ���ɽ���ʡ�"
 * ���������Ϊ��"�໨��/���� �� �ܽ���/��Ա/���� �ݳ���һ�׸������ɷ���ɽ���ʡ�"
 */
public class KeyWords {

    public static void main(String[] args) {

        String template = "��Ա-������|�ܽ���;����-�ܽ�|�ܽ���;����-�໨��|˫�ع�;��Ӱ-�����|Ӣ��";
        String content = "�໨�����ܽ����ݳ���һ�׸������ɷ���ɽ���ʡ�";

        KeyWords keyWords = new KeyWords();
        System.err.println(keyWords.callout(template, content));

    }

    /**
     * ��ע
     */
    private String callout(String template, String content) {

        Map<String, String> keyWordMap = pickUp(template);
        Map<String, String> havePriorityMap = priority(keyWordMap);

        List<String> alreadyMatched = new ArrayList();

        Iterator it = havePriorityMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            //System.out.println("key:" + entry.getKey() + "   value:" + entry.getValue());
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            int matchStart = content.indexOf(key);
            if (matchStart >= 0 && nonInAlreadyMatched(alreadyMatched, content, matchStart)) {
                content = content.replace(key, key + "/" + value + " ");
                //System.err.println(content);
                alreadyMatched.add(key);
            }
        }

        return content;
    }

    /**
     * ��ǰƥ��ĵط����Ƿ� �����Ѿ�ƥ����˵ĵط�
     *
     * @param alreadyMatched
     * @param content
     * @param matchStart
     * @return
     */
    private boolean nonInAlreadyMatched(List<String> alreadyMatched, String content, int matchStart) {

        for (String key : alreadyMatched) {
            if (content.indexOf(key) == matchStart) {
                return false;
            }
        }
        return true;
    }

    /**
     * ��ȡ
     */
    private Map pickUp(String template) {

        Map<String, String> keyWordMap = new HashMap();
        String[] typeValueStrArr = template.split(";");
        for (String typeValueStr : typeValueStrArr) {
            String[] typeValue = typeValueStr.split("-");
            String type = typeValue[0];
            String valueStr = typeValue[1];
            String[] valueArr = valueStr.split("\\|");
            for (String value : valueArr) {
                String alreadyType = keyWordMap.get(value);
                if (alreadyType != null) {
                    alreadyType = alreadyType + "/" + type;
                    keyWordMap.put(value, alreadyType);
                } else {
                    keyWordMap.put(value, type);
                }
            }
        }
        //System.err.println(keyWordMap);
        return keyWordMap;
    }

    /**
     * ���ķ�ǰ��
     *
     * @param keyWordMap
     * @return
     */
    private Map<String, String> priority(Map<String, String> keyWordMap) {

        Map<String, String> havePriorityMap = new LinkedHashMap<>();

        Object[] keySet = keyWordMap.keySet().toArray();

        String[] keyPriorityArr = new String[keySet.length];
        for (int i = 0; i < keySet.length; i++) {
            keyPriorityArr[i] = (String) keySet[i];
        }
        Arrays.sort(keyPriorityArr, new LengthComparator());

        for (int i = 0; i < keyPriorityArr.length; i++) {
            havePriorityMap.put(keyPriorityArr[i], keyWordMap.get(keyPriorityArr[i]));

        }
        //System.err.println(havePriorityMap);

        return havePriorityMap;

    }

}

/**
 * ���ַ������������õ�
 */
class LengthComparator implements Comparator<String> {
    public int compare(String a, String b) {
        return b.length() - a.length();
    }
}
