package com.monpro.generics;

import java.util.Iterator;

public class DynamicArrayList <E> implements Iterable<E>{

    DynamicArray<E> array;

    public DynamicArrayList(DynamicArray<E> array) {
        this.array = array;
    }

    @Override
    public Iterator<E> iterator() {
        return new DynamicArrayIterator<>(array);
    }

    public static void main(String[] args) {
        DynamicArray<Double> doubleArray = new DynamicArray<>();
        doubleArray.add(7.1);
        doubleArray.add(8.1);
        doubleArray.add(9.1);

        DynamicArrayList<Double> list = new DynamicArrayList<>(doubleArray);
        for(double num: list) {
            System.out.println(num);
        }
    }
}
