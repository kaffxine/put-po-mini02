#ifndef WEZEL_H
#define WEZEL_H

#include <iostream>
#include <memory>

class Wezel {
private:
    std::string name;
    std::weak_ptr<Wezel> link;
public:
    Wezel();
    Wezel(std::string name);
    std::string display(unsigned depth);
    void add_link(std::shared_ptr<Wezel> &other);
    ~Wezel();
};

#endif
