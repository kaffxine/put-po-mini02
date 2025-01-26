package recycling;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Objects;

// Abstrakcyjna klasa Wezel, z której będą dziedziczyć implementacje
// dla ArrayList, HashMap i TreeMap. Zdefiniowanie tej bazowej klasy
// pozwala nam "łączyć" ze sobą wiele typów węzłów, na przykład
// WezelArrayList będzie mógł mieć w swoich połączeniach
// WezelTreeMap, albo WezelHashMap. Konkretne implementacje
// można znaleźć w osobnych plikach.
public abstract class Wezel implements Comparable<Wezel> {
    private static final ThreadLocalRandom RNG = ThreadLocalRandom.current();
    protected int hash;

    protected Wezel() {
        this.hash = java.util.Objects.hash(RNG.nextInt(), System.nanoTime());
    }

    public abstract void dodajPolaczenie(Wezel w);
    public abstract ArrayList<Wezel> getPolaczenia();
    public abstract String podsumowanie();
    public abstract int size();

    @Override
    public int compareTo(Wezel other) {
        int sizeComparison = Integer.compare(this.size(), other.size());
        if (sizeComparison == 0) {
            return hashComparison = Integer.compare(this.hash, other.hash);
        }
        return sizeComparison;
    }

    @Deprecated
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizowanie_obiektu");
        super.finalize();
    }

    public void wyswietlPolaczenia() {
        String output = this.podsumowanie() + ":";
        for (Wezel w : this.getPolaczenia()) {
            output += "\n  [" + w.podsumowanie() + "]";
        }
        System.out.println(output);
    }

}
