#include "SLList.h"

int main()
{

	SLList a;

	a.add_to_head(1), a.add_to_head(2), a.add_to_head(3);

	a.print_list();

	a.delete_node(1);

	a.insert_after(a.head, 4);
	a.insert_before(a.tail, 9);

	a.print_list();

	return 0;
}
