package com.monpro.generics.map;

import com.monpro.generics.array.DynamicArray;
import com.monpro.generics.array.DynamicArrayIterator;

import java.util.*;

public class DynamicMap<K, V> extends AbstractMap<K, V> {

    private DynamicArray<Entry<K, V>> array;
    private Set<Entry<K, V>> entrySet = null;

    public DynamicMap() {
        array = new DynamicArray<>();
    }

    public DynamicMap(Map<? extends K, ? extends V> map) {
        this();
        putAll(map);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        if(entrySet == null) {
            entrySet = new EntrySet();
        }
        return entrySet;
    }

    @Override
    public V put(K key, V value) {
        for(int i = 0; i < array.size(); i++) {
            Map.Entry<K, V> entry = array.get(i);
            if((key == null && entry.getKey() == null) || (key != null && key.equals(entry.getKey()))) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        Map.Entry<K, V> entry = new AbstractMap.SimpleEntry<K, V>(key, value);
        array.add(entry);
        return null;
    }

    class EntrySet extends AbstractSet<Entry<K, V>> {
        public Iterator<Entry<K, V>> iterator() {
            return new DynamicArrayIterator<Entry<K, V>>(array);
        }

        public int size() {
            return array.size();
        }
    }

    public static void main(String[] args) {
        DynamicMap<String, Integer> dynamicMap = new DynamicMap<>();
        dynamicMap.put("abc", 12);
        dynamicMap.put("abc", 14);
        dynamicMap.put("def", 14);

        for(Map.Entry<String, Integer> entry: dynamicMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
