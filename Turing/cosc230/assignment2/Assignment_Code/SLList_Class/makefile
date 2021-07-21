reverse_list_test: reverse_list_test.o SLList.o
	g++ reverse_list_test.o SLList.o -o reverse_list_test

reverse_list_test.o: reverse_list_test.cc SLList.h
	g++ -g reverse_list_test.cc -c -o reverse_list_test.o

SLList.o: SLList.h SLList.cc
	g++ -g SLList.cc -c -o SLList.o

clean: 
	rm SLList.o reverse_list_test.o reverse_list_test
