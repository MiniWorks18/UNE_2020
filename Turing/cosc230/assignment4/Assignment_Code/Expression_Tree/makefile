expression_tree_test: expression_tree_test.o Expression_Tree.o
	g++ expression_tree_test.o Expression_Tree.o -o expression_tree_test

expression_tree_test.o: expression_tree_test.cc Expression_Tree.h
	g++ -g expression_tree_test.cc -c -o expression_tree_test.o

Expression_Tree.o: Expression_Tree.h Expression_Tree.cc
	g++ -g Expression_Tree.cc -c -o Expression_Tree.o

clean: rm expression_tree_test.o expression_tree_test Expression_Tree.o
