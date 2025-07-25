import interfaces.Sink;

import java.util.HashMap;
import java.util.Map;

public class FruitsSinkSync implements Sink<String> {
    private Map<String, Integer> counter = new HashMap<>();

    @Override
    public void add(String item) {
        synchronized (counter) {
            counter.merge(item, 1, Integer::sum);
        }
    }

    @Override
    public Map<String, Integer> getCounter() {
        synchronized (counter) {
            return new HashMap<>(counter);
        }
    }
}
