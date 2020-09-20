package com.monpro.generics;

import java.util.Arrays;

public class DynamicArray<E> {
    private static final int DEFAULT_CAPACITY = 1;
    private int size;
    private Object[] elements;

    public DynamicArray() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= minCapacity) {
            return;
        }
        int newCapacity = oldCapacity * 2;
        if(newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        elements = Arrays.copyOf(elements, newCapacity);
    }

    public void add(E e) {
        ensureCapacity(size + 1);
        elements[size++] = e;
    }

    public E get(int index) {
        return (E)elements[index];
    }

    public int size() {
        return size;
    }

    public E set(int index, E element) {
        E oldValue = (E)elements[index];
        elements[index] = element;
        return oldValue;
    }
    // this make sure DynamicArray<T> element could be added to DynamicArray<E>
    public <T extends E> void addAll(DynamicArray<T> arr) {
        for(int i = 0; i < arr.size; i++) {
            add(arr.get(i));
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < size; i++) {
            builder.append(elements[i]).append(" ");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        DynamicArray<Number> numberDynamicArray = new DynamicArray<>();
        numberDynamicArray.add(1);
        numberDynamicArray.add(2);
        numberDynamicArray.add(3);

        DynamicArray<Integer> intArray = new DynamicArray<>();
        intArray.add(4);
        intArray.add(5);
        intArray.add(6);

        DynamicArray<Double> doubleArray = new DynamicArray<>();
        doubleArray.add(7.1);
        doubleArray.add(8.1);
        doubleArray.add(9.1);

        numberDynamicArray.addAll(intArray);
        numberDynamicArray.addAll(doubleArray);

        System.out.println(numberDynamicArray);
    }
}


