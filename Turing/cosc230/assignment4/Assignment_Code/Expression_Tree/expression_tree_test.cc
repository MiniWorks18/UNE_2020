#include "Expression_Tree.h"
#include <iostream>
using namespace std;

int main()
{
	// Store parenthesized expression
	char input[] = {'2', '+', '(', '3', '*', '(', '2', '+', '2', ')', ')', '+', '5'};
	int size = sizeof(input)/sizeof(char);

	Expression_Tree a;
	a.build_expression_tree(input, size);

	cout << "Original input: ";
	for (int i = 0; i != sizeof(input)/sizeof(char); ++i) {
		cout << input[i];
	}
	cout << endl;

	cout << "Input in infix notation: 2+3*2+2+5. ";
	cout << "Answer: ";
	a.inorder();
	cout << endl;

	cout << "Input in prefix notation: +2+*3+225. ";
	cout << "Answer: ";
	a.preorder();
	cout << endl;

	cout << "Input in postfix notation: 2322+*5++. ";
	cout << "Answer: ";
	a.postorder();
	cout << endl;

	return 0;
}
