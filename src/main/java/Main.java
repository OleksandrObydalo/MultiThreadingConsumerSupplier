import interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Bucket<String> bucket = new LockBucket<>();
        Sink<String> sink = new FruitsSinkSync(); // Assuming FruitsSink is one of the synchronized Sinks
        List<Thread> suppliers = new ArrayList<>();
        final int nSuppliers = 5;
        for (int i = 0; i < nSuppliers; i++) {
            suppliers.add(new Thread(
                    ISupplier.of(new FruitSource(), bucket),
                    "Supplier#%d".formatted((i + 1))
            ));
        }
        List<Thread> consumers = new ArrayList<>();
        final int nConsumers = 3;
        for (int i = 0; i < nConsumers; i++) {
            consumers.add(new Thread(
                    IConsumer.of(bucket, 5, sink),
                    "Consumer#%d".formatted((i + 1))
            ));
        }
        suppliers.forEach(Thread::start);
        consumers.forEach(Thread::start);
        try {
            for (var t : suppliers) t.join();
            for (var t : consumers) t.join();
        } catch (InterruptedException e) { /* */ }
        System.out.println(sink.getCounter());
    }
}
