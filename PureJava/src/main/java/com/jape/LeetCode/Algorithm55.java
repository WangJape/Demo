package com.jape.LeetCode;

/**
 * ����һ���Ǹ��������飬�����λ������ĵ�һ��λ�á�
 *
 * �����е�ÿ��Ԫ�ش������ڸ�λ�ÿ�����Ծ����󳤶ȡ�
 *
 * �ж����Ƿ��ܹ��������һ��λ�á�
 *
 * ʾ�� 1:
 * ����: [2,3,1,1,4]
 * ���: true
 * ����: ���ǿ������� 1 ������λ�� 0 ���� λ�� 1, Ȼ���ٴ�λ�� 1 �� 3 ���������һ��λ�á�
 *
 * ʾ�� 2:
 * ����: [3,2,1,0,4]
 * ���: false
 * ����: �������������ܻᵽ������Ϊ 3 ��λ�á�����λ�õ������Ծ������ 0 �� ��������Զ�����ܵ������һ��λ�á�
 *
 * ��Դ�����ۣ�LeetCode��
 * ���ӣ�https://leetcode-cn.com/problems/jump-game
 * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 */
public class Algorithm55 {

    public static void main(String[] args) {
        Algorithm55 algorithm = new Algorithm55();

        int[] nums1 = {2,3,1,1,4};
        int[] nums2 = {3,2,1,0,4};
        int[] nums3 = new int[25002];
        for(int i = 0; i<25000;i++){
            nums3[i] = 25000-i;
        }
        nums3[24999] = 1;
        nums3[25000] = 0;
        nums3[25001] = 0;

        //long startTime=System.currentTimeMillis();//��ȡ��ʼʱ��
        long startTime=System.nanoTime();   //��ȡ��ʼʱ��

        //System.err.println(algorithm.canJump(nums1));
        System.err.println(algorithm.canJump(nums3));

        //long endTime=System.currentTimeMillis(); //��ȡ����ʱ��
        long endTime=System.nanoTime(); //��ȡ����ʱ��
        System.out.println("��������ʱ�䣺["+(endTime-startTime)+"]ns");
    }

    /**
     * ̰��
     * @param nums
     * @return
     */
    /*public boolean canJump(int[] nums) {
        int total = nums.length;//ȫ��
        int transferStation = total-1;//��תվ����ʼΪ�յ�
        for(int i = total-1; i>=0; i--){//���յ㵹������תվ
            if(i + nums[i] >= transferStation){//����ܵ�����תվ������ܵ����յ�
                transferStation = i;//ȷ���µ���תվ
            }
        }
        return transferStation==0;//������ҲΪ��תվ������Ե���

    }*/

    enum Index {
        GOOD, BAD, UNKNOWN
    }

    /**
     * �Ե����ϵĶ�̬�滮
     * �Ե����Ϻ��Զ����¶�̬�滮��������������˻��ݣ���ʵ��ʹ���У��Ե����µķ����и��õ�ʱ��Ч����Ϊ���ǲ�����Ҫջ�ռ䣬���Խ�ʡ�ܶ໺�濪��������Ҫ���£��������֮������Ż��Ŀռ䡣����ͨ����ͨ����ת��̬�滮�Ĳ�����ʵ�ֵġ�
     *
     * ������������ÿ��ֻ��������������ζ��������Ǵ��ұ߿�ʼ��̬�滮��ÿ�β�ѯ�ұ߽ڵ����Ϣ�������Ѿ�������˵ģ�������Ҫ����ĵݹ鿪������Ϊ����ÿ���� memo ���ж������ҵ������
     *
     * ���ߣ�LeetCode
     * ���ӣ�https://leetcode-cn.com/problems/jump-game/solution/tiao-yue-you-xi-by-leetcode/
     * ��Դ�����ۣ�LeetCode��
     * ����Ȩ���������С���ҵת������ϵ���߻����Ȩ������ҵת����ע��������
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }
        return memo[0] == Index.GOOD;
    }


    /**
     * ʧ�ܣ�ÿ�������ģ�������������ͻ��ݣ�
     * @param nums
     * @return
     */
    /*public boolean canJump(int[] nums) {

        int[] stack = new int[nums.length];//���ݶ�ջ
        int stackIndex = -1;//���ݶ�ջ ջ��ָ��

        int hopNumber = 0;

        int i = 0;
        while (i < nums.length-1) {
            System.err.print("��ǰ����" + i);

            hopNumber = nums[i];

            stackIndex++;
            System.err.print("[��ջ" + stackIndex + "��ջ" + hopNumber + "]");
            stack[stackIndex] = hopNumber;//���ݶ�ջ�ݴ�

            while(hopNumber == 0){
                stackIndex--;
                if(stackIndex == -1){
                    System.err.println("\n");
                    return false;
                }
                i -= stack[stackIndex];//����
                hopNumber = --stack[stackIndex];//������1
                System.err.print("  ����Խ��Ϊ0�����ݵ���һ��,��ǰ����" + i);

            }

            System.err.print("  ����Խ��" + hopNumber);
            i += hopNumber;
            System.err.println("  ��Ծ������" + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return true;
    }*/
}
