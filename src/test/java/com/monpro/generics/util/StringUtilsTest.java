package com.monpro.generics.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringUtilsTest {

  @Test
  void maximumPointsTest() {
    assertEquals(StringUtils.maximumPoints("abab", 4, 5), 9);
    assertEquals(StringUtils.maximumPoints("baba", 4, 5), 10);
    assertEquals(StringUtils.maximumPoints("babacdab", 10, 2), 22);
  }

  @Test
  void replacePalindromeTest() {
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

  @Test
  void checkIfSubStringCouldBreakTest() {
    assertEquals(StringUtils.checkIfSubStringCouldBreak("abc", "abe"), true);
    assertEquals(StringUtils.checkIfSubStringCouldBreak("abc", "ab"), false);
    assertEquals(StringUtils.checkIfSubStringCouldBreak("adc", "abe"), false);
  }

  @Test
  void reverseOnlyLettersTest() {
    assertEquals(StringUtils.reverseOnlyLetters("a-bC-dEf-ghIj"), "j-Ih-gfE-dCba");
    assertEquals(StringUtils.reverseOnlyLetters("Test1ng-Leet=code-Q!"), "Qedo1ct-eeLg=ntse-T!");
    assertEquals(StringUtils.reverseOnlyLetters("13abf4"), "13fba4");
    assertEquals(StringUtils.reverseOnlyLetters(""), "");
  }

  @Test
  void balancedLRSplitTest() {
    assertEquals(StringUtils.balancedLRSplit("RLRRLLRLRL"), 4);
    assertEquals(StringUtils.balancedLRSplit("RLLLLRRRLR"), 3);
    assertThrows(IllegalArgumentException.class, () -> StringUtils.balancedLRSplit("LrR"));
  }

  @Test
  void removeOuterParenthesesTest() {
    assertEquals(StringUtils.removeOuterParentheses(""), "");
    assertEquals(StringUtils.removeOuterParentheses("()()"), "");
    assertEquals(StringUtils.removeOuterParentheses("(()())"), "()()");
    assertEquals(StringUtils.removeOuterParentheses("(()))"), "()");
  }

  @Test
  void removeDuplicatesInPlaceTest() {
    assertEquals(StringUtils.removeDuplicatesInPlace(""), "");
    assertEquals(StringUtils.removeDuplicatesInPlace("aabcdd"), "bc");
    assertEquals(StringUtils.removeDuplicatesInPlace("abbaca"), "ca");
  }

  @Test
  void removeAdjacentKDuplicatesTest() {
    assertEquals(StringUtils.removeAdjacentKDuplicates("", 1), "");
    assertEquals(StringUtils.removeAdjacentKDuplicates("aac", -1), "");
    assertEquals(StringUtils.removeAdjacentKDuplicates("aaacb", 2), "acb");
    assertEquals(StringUtils.removeAdjacentKDuplicates("aaacb", 3), "cb");
    assertEquals(StringUtils.removeAdjacentKDuplicates("deeedbbcccbdaa", 3), "aa");
  }

  @Test
  void minSwapsTest() {
    assertEquals(StringUtils.minSwaps("11010"), 2);
  }

  @Test
  void isPrefixOfWordTest() {
    assertEquals(StringUtils.isPrefixOfWord("i love eating burger", "burg"), 4);
  }

  @Test
  void findMinFibonacciNumbersTest() {
    assertEquals(StringUtils.findMinFibonacciNumbers(7), 2);
  }

  @Test
  void monotoneIncreasingDigitsTest() {
    assertEquals(StringUtils.monotoneIncreasingDigits(12322), 12299);
  }

  @Test
  void truncateSentenceTest() {
    assertEquals(StringUtils.truncateSentence("Hello how are you Contestant", 4), "Hello how are you");
    assertEquals(StringUtils.truncateSentence("Hello how are you", 15), "Hello how are you");
  }

  @Test
  void nextGreatestLetterTest() {
    assertEquals(StringUtils.nextGreatestLetter(new char[]{'c','f','j'}, 'a'), 'c');
    assertEquals(StringUtils.nextGreatestLetter(new char[]{'c','f','j'}, 'q'), 'c');
  }
}
