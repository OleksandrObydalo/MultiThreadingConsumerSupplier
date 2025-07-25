import interfaces.Sink;

import java.util.HashMap;
import java.util.Map;

public class FruitsSinkNoSync implements Sink<String> {
    private Map<String, Integer> counter = new HashMap<>();

    @Override
    public void add(String item) {
        counter.merge(item, 1, Integer::sum);
    }

    @Override
    public Map<String, Integer> getCounter() {
        return new HashMap<>(counter);
    }
}
