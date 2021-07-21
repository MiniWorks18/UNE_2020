#include "BST.h" 
using namespace std;

int main()
{
	BST<int> a;
	cout << "Empty tree is balanced? (Yes)" << endl;
	if (a.is_balanced() == true)
		cout << "Answer: Yes." << endl;
	else
		cout << "Answer: No." << endl;
	
	a.insert(4);
	cout << "Single node is balanced? (Yes)" << endl;
	if (a.is_balanced() == true)
		cout << "Answer: Yes." << endl;
	else
		cout << "Answer: No." << endl;

	a.insert(3);
	cout << "Linked list of two nodes is balanced? (Yes)" << endl;
	if (a.is_balanced() == true)
		cout << "Answer: Yes." << endl;
	else
		cout << "Answer: No." << endl;

	a.insert(2);
	cout << "Linked list of three nodes is balanced? (No)" << endl;
	if (a.is_balanced() == true)
		cout << "Answer: Yes." << endl;
	else
		cout << "Answer: No." << endl;

	a.insert(5); a.find_and_delete_by_merging(2);
	cout << "Full binary tree of three nodes is balanced? (Yes)" << endl;
	if (a.is_balanced() == true)
		cout << "Answer: Yes." << endl;
	else
		cout << "Answer: No." << endl;

	a.insert(6); 
	cout << "Adding one node to right subtree of previous tree is balanced? (Yes)" << endl;
	if (a.is_balanced() == true)
		cout << "Answer: Yes." << endl;
	else
		cout << "Answer: No." << endl;

	a.insert(7); 
	cout << "Adding one node to right subtree of previous tree is balanced? (No)" << endl;
	if (a.is_balanced() == true)
		cout << "Answer: Yes." << endl;
	else
		cout << "Answer: No." << endl;
	
	return 0;
}
