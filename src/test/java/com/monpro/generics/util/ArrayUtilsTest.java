package com.monpro.generics.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayUtilsTest {
    @Test
    void skipToLastIndexTest() {
        int[] nums = new int[]{2,3,1,1,4};
        assertEquals(ArrayUtils.skipToLastIndex(nums), true);
        nums = new int[]{3,2,1,0,4};
        assertEquals(ArrayUtils.skipToLastIndex(nums), false);
        assertEquals(ArrayUtils.skipToLastIndex(new int[]{}), true);
    }

    @Test
    void minStepsToLastIndexTest() {
        int[] nums = new int[]{2,3,1,1,4};
        assertEquals(ArrayUtils.minStepsToLastIndex(nums), 2);
        nums = new int[]{3,2,1,0,4};
        assertEquals(ArrayUtils.minStepsToLastIndex(nums), 2);
        nums = new int[]{2,6,1,0,4,5,4};
        assertEquals(ArrayUtils.minStepsToLastIndex(nums), 2);
    }

    @Test
    void canReachToIndexWithValueZero() {
        int[] nums = new int[]{4,2,3,0,3,1,2};
        int start = 5;
        assertEquals(ArrayUtils.canReachToIndexWithValueZero(nums, start), true);
        nums = new int[]{4,2,3,0,3,1,2};
        start = 0;
        assertEquals(ArrayUtils.canReachToIndexWithValueZero(nums, start), true);
        nums = new int[]{3,0,2,1,2};
        start = 2;
        assertEquals(ArrayUtils.canReachToIndexWithValueZero(nums, start), false);
    }

}
