main: main.o Bank_Account.o
	g++ main.o Bank_Account.o -o main

main.o: main.cc Bank_Account.h
	g++ -c -g main.cc -o main.o

Bank_Account.o: Bank_Account.cc Bank_Account.h
	g++ -c -g Bank_Account.cc -o Bank_Account.o

clean:
	rm Bank_Account.o main.o main
