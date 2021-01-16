package com.monpro.generics.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilsTest {
    @Test
    void maximumPointsTest() {
        assertEquals(StringUtils.maximumPoints("abab", 4, 5), 9);
        assertEquals(StringUtils.maximumPoints("baba", 4, 5), 10);
        assertEquals(StringUtils.maximumPoints("babacdab", 10, 2), 22);
    }

    @Test
    void replacePalindromeTest(){
        assertEquals(StringUtils.replacePalindrome("abcba"), "aacba");
        assertEquals(StringUtils.replacePalindrome("aba"), "abb");
    }
}
