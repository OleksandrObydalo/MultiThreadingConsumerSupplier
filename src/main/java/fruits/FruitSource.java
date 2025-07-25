package fruits;

import interfaces.Source;

import java.util.ArrayList;
import java.util.List;

public class FruitSource
        extends ArrayList<String>
        implements Source<String>
{
    {
        addAll(List.of("Apple", "Pear", "Orange"));
    }
}
