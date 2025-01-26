package recycling;

import java.util.ArrayList;
import java.util.TreeMap;

public class WezelTreeMap extends Wezel {
    private TreeMap<Wezel, Boolean> polaczenia;

    public WezelTreeMap() {
        this.polaczenia = new TreeMap<>();
    }

    @Override
    public void dodajPolaczenie(Wezel w) {
        this.polaczenia.put(w, true);
    }
    
    @Override
    public ArrayList<Wezel> getPolaczenia() {
        return new ArrayList<>(polaczenia.keySet());
    }

    @Override
    public String podsumowanie() {
        return "WezelTreeMap(size=" + this.polaczenia.size() + ")";
    }

    @Override
    public int size() {
        return polaczenia.size();
    }

}
