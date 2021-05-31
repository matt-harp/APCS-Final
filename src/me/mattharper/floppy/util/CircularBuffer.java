package me.mattharper.floppy.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class CircularBuffer<T> implements Iterable<T> {
    private final T[] buffer;
    private int position = 0;

    public CircularBuffer(int size) {
        buffer = (T[]) new Object[size];
    }

    public T next() {
        return buffer[position++];
    }

    public void push(T element) {
        buffer[position++] = element;
        if(position > buffer.length-1) position = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(buffer).filter(Objects::nonNull).iterator();
    }
}
