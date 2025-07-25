package bucket;

import interfaces.Bucket;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueBucket<T> implements Bucket<T> {
    private BlockingQueue<T> queue =
            new ArrayBlockingQueue<>(1);

    @Override
    public T getItem() throws Exception {
        return queue.take();
    }

    @Override
    public void setItem(T item) throws Exception {
        queue.put(item);
    }
}
