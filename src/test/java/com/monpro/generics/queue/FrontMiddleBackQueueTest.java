package com.monpro.generics.queue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrontMiddleBackQueueTest {

    @Test
    void FrontMiddleBackQueueOperationTest() {
        FrontMiddleBackQueue queue = new FrontMiddleBackQueue();
        queue.pushFront(1);
        queue.pushBack(2);
        queue.pushBack(3);
        queue.pushMiddle(4);

        assertEquals(queue.popMiddle(), 4);
    }
}
