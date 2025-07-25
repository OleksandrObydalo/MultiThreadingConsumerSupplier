package bucket;

import interfaces.Bucket;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockBucket<T> implements Bucket<T> {
    private volatile T item;
    private final Lock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition empty = lock.newCondition();

    @Override
    public T getItem() throws Exception {
        try {
            lock.lock();
            while (item == null)
                full.await();
            T res = item;
            item = null;
            empty.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void setItem(T item) throws Exception {
        try {
            lock.lock();
            while (this.item != null)
                empty.await();
            this.item = item;
            full.signal();
        } finally {
            lock.unlock();
        }
    }
}
