circular_dllist_class_test: circular_dllist_class_test.o Circular_DLList.o
	g++ circular_dllist_class_test.o Circular_DLList.o -o circular_dllist_class_test

circular_dllist_class_test.o: circular_dllist_class_test.cc Circular_DLList.h
	g++ -g circular_dllist_class_test.cc -c -o circular_dllist_class_test.o

Circular_DLList.o: Circular_DLList.h Circular_DLList.cc
	g++ -g Circular_DLList.cc -c -o Circular_DLList.o

clean: 
	rm Circular_DLList.o circular_dllist_class_test.o circular_dllist_class_test
