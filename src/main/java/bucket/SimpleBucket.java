package bucket;

import interfaces.Bucket;

public class SimpleBucket<T> implements Bucket<T> {
    private T item;

    @Override
    public T getItem() {
        T res = item;
        item = null;
        return res;
    }

    @Override
    public void setItem(T item) {
        this.item = item;
    }
}
