package com.monpro.generics.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreRankTest {

  @Test
  void scoreRankOperationTest() {
    ScoreRank scoreRank = new ScoreRank();
    scoreRank.addScore(0, 12);
    scoreRank.addScore(1, 33);
    scoreRank.addScore(2, 99);
    scoreRank.addScore(3, 1);
    scoreRank.addScore(4, 100);
    assertEquals(scoreRank.top(2), 199);
    assertEquals(scoreRank.top(1), 100);
    scoreRank.reset(4);
    assertEquals(scoreRank.top(1), 99);
  }
}
