#include <memory>
#include "wezel.hh"

Wezel::Wezel() {}

Wezel::Wezel(std::string name) {
    this->name = name;
}

std::string Wezel::display(unsigned depth) {
    if (std::shared_ptr<Wezel> ptr = link.lock()) {
        if (depth > 0)
            return "Wezel(\"" + ptr->name + "\", " + ptr->display(depth - 1) + ")";
        return "Wezel(\"" + name + "\")";
    }
    return "NOT_FOUND";
}

void Wezel::add_link(std::shared_ptr<Wezel> &other) {
    this->link = other;
}

Wezel::~Wezel() {
    std::cout << "Destrukcja_obiektu" << std::endl;
}
