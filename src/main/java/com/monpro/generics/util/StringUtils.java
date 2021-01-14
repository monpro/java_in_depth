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
}
