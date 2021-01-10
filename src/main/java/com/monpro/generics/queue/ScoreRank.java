package com.monpro.generics.queue;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ScoreRank {
    // hashmap + priority queue
    Map<Integer, Integer> playerToScore;
    PriorityQueue<Integer> maxHeap;
    public ScoreRank() {
        playerToScore = new HashMap<>();
    }

    public void addScore(int playerId, int score) {
        playerToScore.put(playerId, playerToScore.getOrDefault(playerId, 0) + score);
    }

    public int top(int K) {
        int result = 0;
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for(Map.Entry<Integer, Integer> entry: playerToScore.entrySet()) {
            maxHeap.add(entry.getValue());
        }
        for(int i = 0; i < K; i++) {
            if(!maxHeap.isEmpty()) {
                result += maxHeap.poll();
            }
        }
        return result;
    }

    public void reset(int playerId) {
        playerToScore.put(playerId, 0);
    }
}
