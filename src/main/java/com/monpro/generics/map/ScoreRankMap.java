package com.monpro.generics.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ScoreRankMap {
    Map<Integer, Integer> playerToScore;
    TreeMap<Integer, Integer> scoreCount;
    public ScoreRankMap() {
        playerToScore = new HashMap<>();
        scoreCount = new TreeMap<>(Collections.reverseOrder());
    }

    public void addScore(int playerId, int score) {
        if(playerToScore.containsKey(playerId)) {
            int prevScore = playerToScore.remove(playerId);
            scoreCount.put(prevScore, scoreCount.get(prevScore) - 1);
            if(scoreCount.get(prevScore) == 0) {
                scoreCount.remove(prevScore);
            }
            score += prevScore;
        }
        playerToScore.put(playerId, score);
        scoreCount.put(score, scoreCount.getOrDefault(score, 0) + 1);
    }

    public int top(int K) {
        int cur = 0, result = 0;
        for(Map.Entry<Integer, Integer> entry: scoreCount.entrySet()) {
            int score = entry.getKey();
            int count = entry.getValue();
            int temp = Math.min(K - cur, count);
            result += temp * score;
            cur += temp;
            if(cur == K) {
                break;
            }
        }
        return result;
    }

    public void reset(int playerId) {
        int score = playerToScore.remove(playerId);
        scoreCount.put(score, scoreCount.get(score) - 1);
        if(scoreCount.get(score) == 0) {
            scoreCount.remove(score);
        }
    }
}
