package recycling;

import java.util.ArrayList;
import java.util.Scanner;

public class Wezel {
    private ArrayList<Wezel> polaczenie;

    public Wezel() {
        // ArrayList jest potrzebny, aby dynamicznie
        // zmieniać ilość połączeń w czasie działania programu,
        // bez zbędnej alokacji pamięci, ani sztywnych limitów
        // co do ilości połączeń.
        //
        // Moglibyśmy użyć również HashMap<Wezel, bool>, aby
        // zredukować czas sprawdzania czy węzeł w1 jest w
        // liście połączeń węzła w2 z O(n) do O(1), kosztem
        // większego zużycia pamięci (i dłuższego czasu dostępu
        // dla bardzo małej ilości węzłów). Mamy do dyspozycji
        // również TreeMap<Wezel, bool> wykorzystującą drzewa
        // czerwono-czarne, z czasem sprawdzania O(log(n)). Nie
        // sądzę jednak, żeby była to dobra alternatywa, ponieważ
        // główną zaletą drzewnej mapy jest jej własność, która
        // gwarantuje utrzymanie jej elementów w posortowanej
        // kolejności, co czasem umożliwia znaczące przyspieszenie
        // algorytmów, a w tej sytuacji jest to bezużyteczne, a
        // zarówno czas dostępu jak i zużycie pamięci jest
        // zapewne mniej korzystne niż w przypadku haszowej mapy.
        this.polaczenie = new ArrayList<>();
    }

    public void dodajPolaczenie(Wezel w) {
        this.polaczenie.add(w);
    }

    @Deprecated
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizowanie_obiektu");
        super.finalize();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // Do około 300'000 iteracji, JVM nie zwalnia pamięci
        // w trakcie działania programu, więc nie widzimy
        // żadnych komunikatów "Finalizowanie_obiektu". Gdy
        // przekroczymy tę liczbę, JVM nagle zdaje sobie sprawę,
        // jaki się zrobił syf w pamięci, i zaczyna czyścić, ale
        // nadal tylko około 1/3 obiektów zostaje zwalniana
        // podczas działania programu.
        //
        // Po zaimplementowaniu ArrayList i tworzeniu dwóch
        // wzajemnie połączanych węzłów w pętli, potrzebujemy
        // jedynie około 50'000 iteracji, aby obudzić GC.
        for (int i = 0; i <= 50000; i++) {
            Wezel w1 = new Wezel();
            Wezel w2 = new Wezel();
            w1.dodajPolaczenie(w2);
            w2.dodajPolaczenie(w1);
        }
        s.nextLine();
        s.close();
    }
}
