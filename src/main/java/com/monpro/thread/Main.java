package com.monpro.thread;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] words = paragraph.replaceAll("[.,!?;\\[\\]]", "").split(" ");


        String hello = " apple Apple appLE apple ";
        Pattern pattern = Pattern.compile("\\bapple\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(hello);

        System.out.println(matcher.find());
        int result = 0;
        while(matcher.find()) {
            result += 1;
        }
        System.out.println(result);

        ArrayList<BigInteger> res = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
//        Stack<Integer> stack = new Stack<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(12);
        stack.add(13);
        System.out.println(stack.poll());
        System.out.println(stack.poll());
        BigInteger[] ans = new BigInteger[10];

        BigInteger[] convertedRes = (BigInteger[])res.toArray();

    }
}