package com.monpro.generics.util;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

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

    public static boolean canReachToIndexWithValueZero(int[] nums, int start) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> set = new HashSet<>();
        stack.add(start);

        while(!stack.isEmpty()) {
            int index = stack.pop();
            if(nums[index] == 0) {
                return true;
            }
            int left = index - nums[index];
            int right = index + nums[index];
            if(left >= 0 && !set.contains(left)) {
                stack.add(left);
                set.add(left);
            }
            if(right < nums.length && !set.contains(right)) {
                stack.add(right);
                set.add(right);
            }
        }
        return false;
    }
}
