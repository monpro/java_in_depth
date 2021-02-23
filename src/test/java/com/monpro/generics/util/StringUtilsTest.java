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


    @Test
    void firstUniCharTest() {
        assertEquals(StringUtils.firstUniChar("abaccdef"), 'b');
        assertEquals(StringUtils.firstUniChar("abbcac"), ' ');
        assertEquals(StringUtils.firstUniChar("abbefefgcc"), 'a');
    }

    @Test
    void maxNumberOfUniqueSubStringTest() {
    assertEquals(StringUtils.maxNumberOfUniqueSubString("ababccc"), 5);
    assertEquals(StringUtils.maxNumberOfUniqueSubString("abc"), 3);
    }

    @Test
    void numEqualDistinctSplitsTest() {
        assertEquals(StringUtils.numEqualDistinctSplits(""), 0);
        assertEquals(StringUtils.numEqualDistinctSplits("aacaba"), 2);
        assertEquals(StringUtils.numEqualDistinctSplits("abcd"), 1);
    }
}
