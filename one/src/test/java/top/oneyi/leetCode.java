package top.oneyi;

import org.junit.Test;

public class leetCode {

    /**
     * 给你一个只包含正整数的非空数组nums。
     * 请你判断是否可以将这个数组分割成两
     * 个子集，使得两个子集的元素和相等。
     */
    @Test
    public void test001(){

    }
    class Solution {
        public boolean canPartition(int[] nums) {
            //如果长度为1 不可能分为2个子集
            int len = nums.length;
            if(len < 2){
                return false;
            }
            //求和
            int sum = 0;
            int max = Integer.MIN_VALUE;
            for(int num : nums){
                sum += num;
                max = Math.max(max,num);
            }
            //奇数和无法构成
            if( sum % 2 != 0){
                return false;
            }
            //寻找目标子集和
            int target = sum/2;

            //如果target小于max，那么剩下的元素和肯定小于target 也无法构成
            if(target < max){
                return false;
            }
            //动态规划 判断数组是否能找子集和为target dp表示前i个数范围内能否构成目标为j的数
            boolean[][] dp = new boolean[len+1][target+1];
            //前i个中任意一个组合都可以构成目标为0的集合 也就是空集
            for(int i = 0 ; i < len+1; i++){
                dp[i][0] = true;
            }
            for(int i = 1; i < len + 1;i++){
                int num = nums[i-1];
                for(int j = 1 ; j <= target;j++){
                    if(num > j){
                        dp[i][j] = dp[i-1][j];
                    }else{
                        dp[i][j] = dp[i-1][j] || dp[i-1][j-num];
                    }
                }
            }
            return dp[len-1][target];
        }
    }

    /**
     * 插入排序
     */
    @Test
    public void test02(){

    }

}
