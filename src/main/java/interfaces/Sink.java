package interfaces;

import java.util.Map;

public interface Sink<T> {
    void add(T item);
    Map<T, Integer> getCounter();
}
