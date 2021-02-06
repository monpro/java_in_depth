package com.monpro.generics.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneBookTest {

    @Test
    void PhoneBookBehaviorTest() {
        PhoneBook phoneBook = new PhoneBook(10);
        assertEquals(phoneBook.get(), 0);
        assertEquals(phoneBook.check(0), false);
        assertEquals(phoneBook.get(), 1);
        phoneBook.release(0);
        assertEquals(phoneBook.get(), 0);
        assertEquals(phoneBook.get(), 2);
    }
}
