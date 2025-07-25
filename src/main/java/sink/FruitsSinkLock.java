package sink;

import interfaces.Sink;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FruitsSinkLock implements Sink<String> {
    private Map<String, Integer> counter = new HashMap<>();
    private final ReadWriteLock lock =
            new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    @Override
    public void add(String item) {
        try {
            writeLock.lock();
            counter.merge(item, 1, Integer::sum);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Map<String, Integer> getCounter() {
        try {
            readLock.lock();
            return new HashMap<>(counter);
        } finally {
            readLock.unlock();
        }
    }
}
