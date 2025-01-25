#include <iostream>
#include <memory>
#include "wezel.hh"

int main() {
    // W środku pętli, zamieściłem komentarze dla trzech różnych
    // sposobów na tworzenie obiektów klasy Wezel. Kod był testowany
    // dla każdego z tych sposobów z osobna, mimo że aktualnie
    // wszystkie trzy są odkomentowane (dla czytelności). Liczona
    // była liczba wypisanych linijek "Destrukcja_obiektu" i czas
    // wykonania tych operacji.
    //
    const unsigned n_iter = 0xffffffff;
    for (unsigned i = 0; i < 1; i++) { // wcześniej: i < n_iter

        // wywołanie 1
        std::unique_ptr<Wezel>(new Wezel());
        // liczba wypisanych linijek: 0xffffffff
        // czas wykonania: 26 minut, 19 sekund
        
        // wywołanie 2
        std::shared_ptr<Wezel>(new Wezel());
        // liczba wypisanych linijek: 0xffffffff
        // czas wykonania: 26 minut, 42 sekundy

        // wywołanie 3
        std::shared_ptr<Wezel> w = std::make_shared<Wezel>();
        // liczba wypisanych linijek: 0xffffffff
        // czas wykonania: 31 minut, 22 sekundy
    }
    // W przeciwieństwie do Javy, C++ nie ułatwia sobie zadania
    // i rzetelnie alokuje wszystkie obiekty, jakie mu alokować
    // każemy.
    // Jeśli chodzi o czas wykonywania, ta minimalna różnica
    // między pierwszym a drugim wywołaniem jest zapewne spowodowana
    // tym, że dla shared_ptr musimy zainicjalizować też licznik
    // referencji (jeśli dobrze rozumiem że to taki Rc<T> z Rusta).
    // Wywołanie trzecie zaś zajęło znacznie dłużej, co było dla
    // mnie większą zagadką. Porównując output strace dla obu
    // przypadków, wygląda on prawie tak samo, z jedyną różnicą taką,
    // że niektóre wywołania mmap dla wywołania 3 alokują o wiele
    // więcej pamięci. Możliwe, że to dlatego, że w wywołaniu 2
    // nie przechowujemy outputu w zmiennej, więc nie trzeba alokować
    // dla tej zmiennej żadnej pamięci.

    for (unsigned i = 0; i < 12; i++) {
        std::shared_ptr<Wezel> w1 = std::make_shared<Wezel>();
        std::shared_ptr<Wezel> w2 = std::make_shared<Wezel>();
        // w1->next = w2;
        // w2->next = w1;
    }
    // Dla tego przypadku, klasa Wezel ma publiczny atrybut
    // std::shared_ptr<Wezel> next. Gdy odkomentujemy dwie
    // dolne linijki w pętli, pamięć nie zostanie zwolniona
    // poprawnie, gdyż std::shared_ptr zwalnia się dopiero
    // wtedy, gdy jego licznik referencji osiągnie zero. Podczas
    // gdy w1 trzyma referencję do w2 i vice versa, żaden z nich
    // nie osiągnie stanu koniecznego do bycia zwolnionym. Żeby
    // poradzić sobie z tym cyklem referencji, możemy użyć
    // std::weak_ptr (nie spodziewałem się, że C++ będzie mieć tyle
    // wspólnego z Rustem). Działa on podobnie do std::shared_ptr,
    // z wyjątkiem tego, że nie zwiększa on licznika referencji
    // obiektu, na który wskazuje (jeśli jest to std::shared_ptr).
    //
    // Możemy zmodyfikować naszą klasę Wezel tak, aby oprócz pola
    // std::shared_ptr<Wezel> next, miała również
    // std::weak_ptr<Wezel> prev.
    //
    for (unsigned i = 0; i < 12; i++) {
        std::shared_ptr<Wezel> w1 = std::make_shared<Wezel>();
        std::shared_ptr<Wezel> w2 = std::make_shared<Wezel>();
        w1->prev = w2;
        w2->next = w1;
    }
    // Zwróćmy uwagę na to, że w pliku main zmieniliśmy jedynie
    // 'next' na 'prev' i wszystko działa. To dzięki temu, że
    // Konstruktor std::weak_ptr(std::shared_ptr) jest
    // automatycznie wywoływany dla przypisania obiektu typu
    // std::shared_ptr do obiektu typu std::weak_ptr, o ile
    // taki konstruktor oczywiście istnieje.
    //
    // W tym przypadku, najpierw jest zwalniany w2, ponieważ
    // jego licznik referencji wynosi 0 (std::weak_ptr go
    // nie zwiększa). Skoro w2 jest już zwolnione, to nie
    // ma też już żadnych referencji do w1, więc jego licznik
    // referencji również wynosi zero. Wszystko działa jak
    // w zegarku.

    return 0;
}

