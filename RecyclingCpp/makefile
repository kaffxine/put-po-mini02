CXX=g++
CXXFLAGS=-g -Wall

outfile: main.o wezel.o
	$(CXX) $(CXXFLAGS) -o outfile main.o wezel.o

main.o: main.cc wezel.hh
	$(CXX) $(CXXFLAGS) -c main.cc -o main.o

wezel.o: wezel.cc wezel.hh
	$(CXX) $(CXXFLAGS) -c wezel.cc -o wezel.o
