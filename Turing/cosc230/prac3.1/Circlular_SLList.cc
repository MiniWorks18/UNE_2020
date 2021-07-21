#include "Circular_SLList.h"
#include <iostream>

void Circular_SLList::add_to_tail(int a)
{
  if (is_empty()) {
    tail = new SLLNode(a);
    tail->next = tail;
  }
  else {
    tail->next = new SLLNode(a, tail->next);
  }
}
