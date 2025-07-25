package interfaces;

public interface ISupplier<T> extends Runnable {
    static <T> ISupplier<T> of(
            Source<T> source, Bucket<T> bucket
    ) {
        return new Supplier<>(source, bucket);
    }
}

class Supplier<T> implements ISupplier<T> {
    private Bucket<T> bucket;
    private Source<T> source;

    public Supplier(Source<T> source, Bucket<T> bucket) {
        this.bucket = bucket;
        this.source = source;
    }

    @Override
    public void run() {
        try {
            for (T item : source) {
                bucket.setItem(item);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
