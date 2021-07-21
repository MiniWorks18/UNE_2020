#include "Circular_DLList.h"
#include <iostream>

using namespace std;

int main()
{

	Circular_DLList a;

	a.add_to_tail(4), a.add_to_tail(3), a.add_to_tail(2); a.add_to_tail(1);

	cout << "List is: " << endl;
	a.print_list();

	cout << "Return head: " << a.return_head() << endl;

	cout << "Return one before tail: " << a.return_one_before_tail() << endl;

	int b = 0;
	if (a.is_in_list(b)) {
		cout << b << " is in list" << endl;
	}
	else {
		cout << b << " is not in list" << endl;
	}

	b = 1;
	if (a.is_in_list(b)) {
		cout << b << " is in list" << endl;
	}
	else {
		cout << b << " is not in list" << endl;
	}

	cout << "Deleting from tail: " << endl;
	cout << a.delete_from_tail() << endl,
	cout << a.delete_from_tail() << endl;
	cout << a.delete_from_tail() << endl;
	cout << a.delete_from_tail() << endl;

	a.add_to_tail(4), a.add_to_tail(3), a.add_to_tail(2); a.add_to_tail(1);
	cout << "Run Valgrind to test destructor" << endl;

	 return 0;
}
