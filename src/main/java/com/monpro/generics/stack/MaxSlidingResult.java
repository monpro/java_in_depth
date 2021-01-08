package com.monpro.generics.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MaxSlidingResult {
    public static int[] getMaxSlidingResults(int[] nums, int k) {
        if(nums.length == 0 || k == 0) {
            return new int[0];
        }
        int[] result = new int[nums.length - k + 1];
        int left = 0;
        Deque<Integer> deque = new ArrayDeque<>();

        for(int right = 0; right < nums.length; right++) {

            while(!deque.isEmpty() && deque.peekFirst() < right - k + 1) {
                deque.pollFirst();
            }

            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[right]) {
                deque.pollLast();
            }
            deque.offer(right);
            if(right >= k - 1) {
                result[left++] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(getMaxSlidingResults(nums, 3)));
    }
}
