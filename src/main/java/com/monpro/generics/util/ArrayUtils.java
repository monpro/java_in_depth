package com.monpro.generics.util;

public class ArrayUtils {
    private ArrayUtils() {
    }

    public static boolean skipToLastIndex(int[] nums) {
        /**
         *  O(n^2) solution
         *
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        int n = nums.length;
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(dp[j] == true&& j + nums[j] >= i) {
                    dp[i] = true;
                }
            }
        }
        return dp[n - 1];
         **/
        int maxSkipPosition = 0;
        for(int i = 0; i < nums.length; i++) {
            if(maxSkipPosition < i) {
                return false;
            }
            if (i + nums[i] > maxSkipPosition) {
                maxSkipPosition = i + nums[i];
            }
        }
        return true;
    }

    public static int minStepsToLastIndex(int[] nums) {
        int result = 0;
        int curFurthest = 0, curEnd = 0;

        for(int cur = 0; cur < nums.length - 1; cur++) {
            curFurthest = Math.max(cur + nums[cur], curFurthest);
            if(cur == curEnd) {
                result += 1;
                curEnd = curFurthest;
            }
        }
        return result;
    }
}
