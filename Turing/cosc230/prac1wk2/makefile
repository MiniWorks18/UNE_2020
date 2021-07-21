main: main.o Rational.o
	g++ main.o Rational.o -o main

main.o: main.cc Rational.h
	g++ -c -g main.cc -o main.o

Rational.o: Rational.cc Rational.h
	g++ -c -g Rational.cc -o Rational.o

clean:
	rm Rational.o main.o main
