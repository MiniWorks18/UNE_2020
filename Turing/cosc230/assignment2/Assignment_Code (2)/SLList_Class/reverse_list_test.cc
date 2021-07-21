#include "SLList.h"
#include <iostream>

int main()
{

	SLList a;

	a.add_to_head(4), a.add_to_head(3), a.add_to_head(2); a.add_to_head(1);

	std::cout << "Original list is: " << std::endl;
	a.print_list();

	a.reverse_list();

	std::cout << "Reversed list is: " << std::endl;
	a.print_list();

	return 0;
}
