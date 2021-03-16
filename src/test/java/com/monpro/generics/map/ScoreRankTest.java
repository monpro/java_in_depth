package com.monpro.generics.map;

import com.monpro.generics.queue.ScoreRank;
import com.monpro.generics.tool.TimingExtension;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TimingExtension.class)
class ScoreRankTest {

  @Rule public TimingExtension stopwatch = new TimingExtension();

  @Test
  void scoreRankTreeMapOperationTest() {
    ScoreRankMap scoreRank = new ScoreRankMap();
    for (int i = 0; i < 1000; i++) {
      scoreRank.addScore(i, 1000);
    }
    for (int i = 0; i < 1000; i++) {
      assertEquals(scoreRank.top(1), 1000);
    }
  }

  @Test
  void scoreRankQueueOperationTest() {
    ScoreRank scoreRank = new ScoreRank();
    for (int i = 0; i < 1000; i++) {
      scoreRank.addScore(i, 1000);
    }
    for (int i = 0; i < 1000; i++) {
      assertEquals(scoreRank.top(1), 1000);
    }
  }
}
