package com.monpro.effectivejava.classes;

import java.util.*;

public class ForwardingClass {
  /**
   * Prefer composition over inheritance
   *
   * <p>because subclass could misuse super class methods and when super class methods are changed,
   * the subclass must migrate to new methods
   *
   * <p>in this case, super.addAll() would called overridden add method to cause wrong count
   */
  class CountHashSet<E> extends HashSet<E> {
    private int count = 0;

    public CountHashSet() {}

    public CountHashSet(int cap, float factor) {
      super(cap, factor);
    }

    @Override
    public boolean add(E e) {
      count += 1;
      return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> collections) {
      count += collections.size();
      return super.addAll(collections);
    }

    public int getCount() {
      return count;
    }
  }

  /**
   * so we could use Decorator pattern to wrap the set as an private final instance then let other
   * class to extend this wrapper class
   */
  class DecoratorSet<E> implements Set<E> {
    private final Set<E> s;

    public DecoratorSet(Set<E> s) {
      this.s = s;
    }

    @Override
    public int size() {
      return s.size();
    }

    @Override
    public boolean isEmpty() {
      return s.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
      return s.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
      return s.iterator();
    }

    @Override
    public Object[] toArray() {
      return s.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
      return s.toArray(a);
    }

    @Override
    public boolean add(E e) {
      return s.add(e);
    }

    @Override
    public boolean remove(Object o) {
      return s.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
      return s.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
      return s.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
      return s.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
      return s.removeAll(c);
    }

    @Override
    public void clear() {
      s.clear();
    }

    @Override
    public Spliterator<E> spliterator() {
      return s.spliterator();
    }

    /**
     * we make sure all the methods we calling are using the instance method instead of misusing
     * overridden methods
     *
     * @param <E>
     */
    class DecoratorSetCountSet<E> extends DecoratorSet<E> {
      private int count = 0;

      public DecoratorSetCountSet(Set<E> s) {
        super(s);
      }

      @Override
      public boolean add(E e) {
        count += 1;
        return super.add(e);
      }

      @Override
      public boolean addAll(Collection<? extends E> c) {
        count += c.size();
        return super.addAll(c);
      }

      public int getCount() {
        return count;
      }
    }
  }
}
