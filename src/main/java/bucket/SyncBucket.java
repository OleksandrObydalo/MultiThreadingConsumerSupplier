package bucket;

import interfaces.Bucket;
import interfaces.Sink;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SyncBucket<T> implements Bucket<T> {
    private T item;
    private volatile boolean isFull;
    private final Object LOCK = new Object();

    @Override
    public T getItem() throws Exception {
        synchronized (LOCK) {
            while (!isFull) {
                LOCK.wait();
            }
            T res = item;
            item = null;
            isFull = false;
            LOCK.notifyAll();
            return res;
        }
    }

    @Override
    public void setItem(T item) throws Exception {
        synchronized (LOCK) {
            while (isFull) {
                LOCK.wait();
            }
            this.item = item;
            isFull = true;
            LOCK.notifyAll();
        }
    }

    public static class FruitsSinkConcurrentCollection implements Sink<String> {
        private Map<String, Integer> counter =
                new ConcurrentHashMap<>();

        @Override
        public void add(String item) {
            counter.merge(item, 1, Integer::sum);
        }

        @Override
        public Map<String, Integer> getCounter() {
            return new HashMap<>(counter);
        }
    }
}
