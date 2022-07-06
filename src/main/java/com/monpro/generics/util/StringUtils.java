package com.monpro.generics.util;

import java.util.*;

class StringUtils {
  static int maximumPoints(String s, int x, int y) {
    // first find a,b
    // then b, a
    // use swap order to make sure ab is always larger than ba
    if (x < y) {
      int temp = x;
      x = y;
      y = temp;
      s = new StringBuilder(s).reverse().toString();
    }

    int countA = 0, countB = 0, result = 0;
    // baba
    for (char ch : s.toCharArray()) {
      if (ch == 'a') {
        countA += 1;
      } else if (ch == 'b') {
        if (countA > 0) {
          result += x;
          countA -= 1;
        } else {
          countB += 1;
        }
      } else {
        result += Math.min(countA, countB) * y;
        countA = 0;
        countB = 0;
      }
    }

    result += Math.min(countA, countB) * y;
    return result;
  }

  public static String getMaximumBinaryString(String s) {
    int oneCount = 0, zeroCount = 0, n = s.length();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      sb.append("1");
    }

    for (char ch : s.toCharArray()) {
      if (ch == '1' && zeroCount == 0) {
        oneCount += 1;
      } else if (ch == '0') {
        zeroCount += 1;
      }
    }

    if (oneCount < n) {
      sb.setCharAt(oneCount + zeroCount - 1, '0');
    }
    return sb.toString();
  }

  static String replacePalindrome(String palindrome) {
    int n = palindrome.length();
    char[] array = palindrome.toCharArray();
    // cannot be a palindrome after replace
    for (int i = 0; i < n / 2; i++) {
      if (array[i] != 'a') {
        array[i] = 'a';
        return String.valueOf(array);
      }
    }
    array[n - 1] = 'b';
    return n >= 2 ? String.valueOf(array) : "";
  }

  static char firstUniChar(String str) {
    if (str == null || str.length() == 0) {
      return ' ';
    }
    int[] count = new int[256];
    char[] array = str.toCharArray();
    for (char ch : array) {
      count[ch - 'a'] += 1;
    }
    for (char ch : array) {
      if (count[ch - 'a'] == 1) {
        return ch;
      }
    }
    return ' ';
  }

  static int maxNumberOfUniqueSubString(String s) {
    Set<String> set = new HashSet<>();
    return maxNumberOfUniqueSubStringDfs(set, 0, s);
  }

  private static int maxNumberOfUniqueSubStringDfs(Set<String> set, int index, String s) {
    if (index >= s.length()) {
      return 0;
    }
    int result = -1;
    for (int i = index + 1; i <= s.length(); i++) {
      String uniqueString = s.substring(index, i);
      if (set.contains(uniqueString)) {
        continue;
      }
      set.add(uniqueString);
      int nextNumber = maxNumberOfUniqueSubStringDfs(set, i, s);
      if (nextNumber >= 0) {
        result = Math.max(result, nextNumber + 1);
      }
      set.remove(uniqueString);
    }
    return result;
  }

  static int numEqualDistinctSplits(String s) {
    int n = s.length();
    int result = 0;
    int[] prefix = new int[n];
    Map<Character, Integer> count = new HashMap<>();
    char[] array = s.toCharArray();
    for (int i = 0; i < n; i++) {
      count.putIfAbsent(array[i], 1);
      prefix[i] = count.size();
    }
    count.clear();
    for (int i = n - 1; i >= 1; i--) {
      count.putIfAbsent(array[i], 1);
      if (prefix[i - 1] == count.size()) {
        result += 1;
      }
    }
    return result;
  }

  static boolean checkIfSubStringCouldBreak(String s1, String s2) {
    if (s1.length() != s2.length()) {
      return false;
    }
    int n = s1.length();
    int[] countS1 = new int[26];
    int[] countS2 = new int[26];

    for (int i = 0; i < s1.length(); i++) {
      countS1[s1.charAt(i) - 'a'] += 1;
      countS2[s2.charAt(i) - 'a'] += 1;
    }

    boolean s1LargerThanS2 = false;
    boolean s2LargerThanS1 = false;
    int countS1Num = 0, countS2Num = 0;
    for (int i = 0; i < 26; i++) {
      countS1Num += countS1[i];
      countS2Num += countS2[i];
      if (countS1Num > countS2Num) {
        if (s2LargerThanS1) return false;
        s1LargerThanS2 = true;
      }
      if (countS2Num > countS1Num) {
        if (s1LargerThanS2) return false;
        s2LargerThanS1 = true;
      }
    }
    return true;
  }

  static String reverseOnlyLetters(String S) {
    char[] array = S.toCharArray();
    int n = S.length();
    int left = 0, right = n - 1;
    while (left < right) {
      while (left < right && !Character.isLetter(array[left])) {
        left += 1;
      }
      while (left < right && !Character.isLetter(array[right])) {
        right -= 1;
      }
      if (left < right) {
        char temp = array[right];
        array[right] = array[left];
        array[left] = temp;
        left += 1;
        right -= 1;
      }
    }
    return new String(array);
  }

  static int balancedLRSplit(String s) {
    int result = 0, count = 0;
    for (char ch : s.toCharArray()) {
      count += ch == 'L' ? 1 : -1;
      if (ch != 'L' && ch != 'R') {
        throw new IllegalArgumentException();
      }
      if (count == 0) {
        result += 1;
      }
    }
    return result;
  }

  static String removeOuterParentheses(String S) {
    StringBuilder sb = new StringBuilder();
    if (S == null || S.length() == 0) {
      return sb.toString();
    }
    int openCount = 0;
    for (char ch : S.toCharArray()) {
      if (ch == '(' && openCount++ > 0) sb.append('(');
      if (ch == ')' && openCount-- > 1) sb.append(')');
    }
    return sb.toString();
  }

  static String removeDuplicatesInPlace(String S) {
    StringBuilder sb = new StringBuilder();
    if (S == null || S.length() == 0) {
      return sb.toString();
    }
    for (char ch : S.toCharArray()) {
      int size = sb.length();
      if (size > 0 && sb.charAt(size - 1) == ch) {
        sb.deleteCharAt(size - 1);
      } else {
        sb.append(ch);
      }
    }
    return sb.toString();
  }

  static String removeAdjacentKDuplicates(String s, int k) {
    if (s == null || s.length() == 0 || k <= 0) {
      return "";
    }
    Stack<Character> stack = new Stack<>();
    Stack<Integer> countStack = new Stack<>();

    for (char ch : s.toCharArray()) {
      if (!stack.isEmpty() && stack.peek() == ch) {
        countStack.push(countStack.peek() + 1);
      } else {
        countStack.push(1);
      }
      stack.push(ch);
      if (countStack.peek() == k) {
        for (int i = 0; i < k; i++) {
          stack.pop();
          countStack.pop();
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()) {
      sb.append(stack.pop());
    }
    return sb.reverse().toString();
  }

  public static int minSwaps(String s) {
    int ones = 0, zeros = 0;
    for(char c : s.toCharArray()) {
      if (c == '0') {
        zeros += 1;
      } else {
        ones += 1;
      }
    }

    if (Math.abs(zeros - ones) > 1) {
      return -1;
    }

    if (zeros - ones == 1) {
      return minSwapsStartWithCh(s, '0');
    } else if (ones - zeros == 1) {
      return minSwapsStartWithCh(s, '1');
    } else {
      return Math.min(minSwapsStartWithCh(s, '0'), minSwapsStartWithCh(s, '1'));
    }
  }

  private static int minSwapsStartWithCh(String s, char ch) {
    int count = 0;
    for(char c : s.toCharArray()) {
      if (c != ch) {
        count += 1;
      }
      ch ^= 1;
    }

    return count / 2;
  }

  public static int isPrefixOfWord(String sentence, String searchWord) {
    String[] words = sentence.split(" ");
    for(int i = 0; i < words.length; i++) {
      if (words[i].startsWith(searchWord)) {
        return i + 1;
      }
    }
    return -1;
  }

  public static int findMinFibonacciNumbers(int k) {
    if (k <= 1) {
      return k;
    }
    int a = 1, b = 1;
    while (b <= k) {
      b = a + b;
      a = b - a;
    }
    return 1 + findMinFibonacciNumbers(k - a);
  }
}
