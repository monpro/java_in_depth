package com.monpro.generics;

import java.util.Arrays;

public class DynamicArray<E> {
    private static final int DEFAULT_CAPACITY = 1;
    private int size = 0;
    private int cursor = 0;
    private int lastCursor = -1;
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
//    public <T extends E> void addAll(DynamicArray<T> arr) {
    public void addAll(DynamicArray<? extends E> arr) {
        for(int i = 0; i < arr.size; i++) {
            add(arr.get(i));
        }
    }

//    public static <T> int indexOf(DynamicArray<T> arr, Object element) {
    public static int indexOf(DynamicArray<? > arr, Object element) {
        for(int i = 0; i < arr.size; i++) {
            if(arr.get(i).equals(element)) {
                return i;
            }
        }
        return -1;
    }

//    public void swap(DynamicArray<? > arr, int i, int j) {
//        Object temp = arr.get(i);
//        arr.set(i, arr.get(j));
//        arr.set(j, temp);
//    }

    public static void swap(DynamicArray<? > arr, int i, int j) {
        swapInternal(arr, i, j);
    }

    private static <T> void swapInternal(DynamicArray<T> arr, int i, int j) {
        T temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

//    public static <D, S extends D> void copy(DynamicArray<D> dest, DynamicArray<S> src) {
    public static <D> void copy(DynamicArray<D> dest, DynamicArray<? extends D> src) {
        for(int i = 0; i < src.size; i++) {
            dest.add(src.get(i));
        }
    }

    public void copyTo(DynamicArray< ? super E> dest) {
        for(int i = 0; i < size; i++) {
            dest.add(get(i));
        }
    }

    public E remove(int index) {
        E oldValue = get(index);
        int numMoved = size - index - 1;
        if(numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return oldValue;
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

        DynamicArray.swap(numberDynamicArray, 1, 6);
        System.out.println(numberDynamicArray);

        intArray.copyTo(numberDynamicArray);

        // it doesn't have super extends here
        //intArray.copyTo(doubleArray);

    }
}


