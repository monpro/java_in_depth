package com.monpro.generics.util;

public class StringUtils {
    public static int maximumPoints(String s, int x, int y) {
        // first find a,b
        // then b, a
        // use swap order to make sure ab is always larger than ba
        if(x < y) {
            int temp = x;
            x = y;
            y = temp;
            StringBuffer sb = new StringBuffer(s);
            s = sb.reverse().toString();
        }

        int countA = 0, countB = 0, result = 0;
        // baba
        for(char ch: s.toCharArray()) {
            if(ch == 'a') {
                countA += 1;
            }
            else if (ch == 'b') {
                if (countA > 0) {
                    result += x;
                    countA -= 1;
                } else {
                   countB += 1;
                }
            }
            else {
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
        for(int i = 0; i < n; i++) {
            sb.append("1");
        }

        for(char ch: s.toCharArray()) {
            if (ch == '1' && zeroCount == 0) {
                oneCount += 1;
            } else if(ch == '0') {
                zeroCount += 1;
            }
        }

        if(oneCount < n) {
            sb.setCharAt(oneCount + zeroCount - 1, '0');
        }
        return sb.toString();
    }

    public static String replacePalindrome(String palindrome) {
        int n = palindrome.length();
        char[] array = palindrome.toCharArray();
        // cannot be a palindrome after replace
        for(int i = 0; i < n / 2; i++) {
            if(array[i] != 'a') {
                array[i] = 'a';
                return String.valueOf(array);
            }
        }
        array[n - 1] = 'b';
        return n >= 2 ? String.valueOf(array) : "";
    }
}
