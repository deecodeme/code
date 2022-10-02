package com.poc.code;

public class Range<T extends Comparable<T>> {
    T start;
    T end;

    public Range(T start, T end) {
        this.start = start;
        this.end = end;
    }

    public boolean isInclusive(T val) {
        return val.compareTo(start) >= 0 && val.compareTo(end) <= 0;
    }

    public T getStart() {
        return start;
    }

    public T getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Range<?>)) {
            return false;
        }
        Range<?> other = (Range<?>) obj;
        return other.start.equals(this.start) && other.end.equals(this.end);
    }
}
