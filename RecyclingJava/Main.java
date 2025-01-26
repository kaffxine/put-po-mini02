package recycling;

import java.util.Scanner;

public class Main {
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
        for (int i = 0; i <= 100; i++) {
            WezelArrayList w1 = new WezelArrayList();
            WezelArrayList w2 = new WezelArrayList();
            w1.dodajPolaczenie(w2);
            w2.dodajPolaczenie(w1);
        }
        
        WezelTreeMap w1 = new WezelTreeMap();
        for (int i = 0; i < 12; i++) {
            Wezel w;
            switch (i % 3) {
                case 0:
                    w = new WezelArrayList();
                    break;
                case 1:
                    w = new WezelHashMap();
                    break;
                default:
                    w = new WezelTreeMap();
                    break;
            }
            for (int j = (12 - i) * 5; j > 0; j--) {
                WezelArrayList wz = new WezelArrayList();
                w.dodajPolaczenie(wz);
            }
            w1.dodajPolaczenie(w);
        }
        w1.wyswietlPolaczenia();

        s.nextLine();
        s.close();

    }
}
