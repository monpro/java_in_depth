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
}
