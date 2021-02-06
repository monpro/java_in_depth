package com.monpro.generics.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovingAverageInArrayTest {

    @Test
    void MovingAverageInArrayBehaviorTest() {
        MovingAverageInArray averageInArray = new MovingAverageInArray(3);
        assertEquals(averageInArray.next(2), 2.0, 0.0001);
        assertEquals(averageInArray.next(3), 2.5, 0.0001);
        assertEquals(averageInArray.next(-1), 1.3333, 0.0001);
        assertEquals(averageInArray.next(10), 4.0, 0.0001);
    }
}
