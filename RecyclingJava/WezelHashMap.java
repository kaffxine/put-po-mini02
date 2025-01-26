package recycling;

import java.util.ArrayList;
import java.util.HashMap;

public class WezelHashMap extends Wezel {
    private HashMap<Wezel, Boolean> polaczenia;

    public WezelHashMap() {
        this.polaczenia = new HashMap<>();
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
        return "WezelHashMap(size=" + this.polaczenia.size() + ")";
    }

    @Override
    public int size() {
        return polaczenia.size();
    }
}
