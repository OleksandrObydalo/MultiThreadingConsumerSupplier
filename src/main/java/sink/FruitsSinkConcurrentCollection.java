package sink;

import interfaces.Sink;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FruitsSinkConcurrentCollection implements Sink<String> {
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
