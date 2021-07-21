main: main.o SLList.o
	g++ main.o SLList.o -o main
main.o: main.cc SLList.h
	g++ -g main.cc -c -o main.o
SLList.o: SLList.h SLList.cc
	g++ -g SLList.cc -c -o SLList.o
clean: 
	rm SLList.o main.o main
