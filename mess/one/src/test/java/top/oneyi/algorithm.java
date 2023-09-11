package top.oneyi;

import org.junit.Test;

import java.util.Arrays;

public class algorithm {

    /**
     * 贪心算法
     */
    @Test
    public void test01(){

        int[] arr = {100,50,20,5,1}; //用来存纸币面额
        int[] nums = new int[5]; //每种纸币的数量
        int money = 136;
        changeQuestions(arr, nums, money);


    }

    /**
     *  找零问题
     * @param arr 纸币面额
     * @param nums 每种纸币数量
     * @param money 一共的钱数
     */
    private void changeQuestions(int[] arr, int[] nums, int money) {
        for (int i = 0; i <arr.length ; i++) {
            nums[i]=money / arr[i];
            money = money % arr[i];
        }
        System.out.println(Arrays.toString(nums));
    }
}
