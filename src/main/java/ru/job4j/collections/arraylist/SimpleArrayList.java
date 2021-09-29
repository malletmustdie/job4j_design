package ru.job4j.collections.arraylist;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements List<T> {

    private static final Object[] EMPTY_CONTAINER = {};

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        add(value, container, size);
    }

    private void add(T value, Object[] array, int s) {
        if (s == array.length) {
            array = grow();
        }
        array[s] = value;
        size = s + 1;
    }

    private T[] grow() {
        int oldCapacity = container.length;
        if (oldCapacity > 0 || container != EMPTY_CONTAINER) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        return container;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T[] oldArr = container;
        T oldValue = container[index];
        remove(oldArr, index);
        return oldValue;
    }

    private void remove(T[] array, int index) {
        modCount++;
        int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(array, index + 1, array, index, newSize - index);
        }
        size = newSize;
        array[size] = null;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int cursor = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }

}