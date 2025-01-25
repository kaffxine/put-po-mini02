#ifndef WEZEL_H
#define WEZEL_H

#include <iostream>
#include <memory>

class Wezel {
public:
    std::weak_ptr<Wezel> prev;
    std::shared_ptr<Wezel> next;
    Wezel();
    void dodaj_polaczenie(Wezel w);
    ~Wezel();
};

#endif
