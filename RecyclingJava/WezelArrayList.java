package recycling;

import java.util.ArrayList;

public class WezelArrayList extends Wezel {
    private ArrayList<Wezel> polaczenia;

    public WezelArrayList() {
        this.polaczenia = new ArrayList<>();
    }

    @Override
    public void dodajPolaczenie(Wezel w) {
        this.polaczenia.add(w);
    }

    @Override
    public ArrayList<Wezel> getPolaczenia() {
        return polaczenia;
    }

    @Override
    public String podsumowanie() {
        return "WezelArrayList(size=" + this.polaczenia.size() + ")";
    }

    @Override
    public int size() {
        return polaczenia.size();
    }
}
