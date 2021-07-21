#include "Circular_DLList.h"
#include <iostream>

using namespace std;

Circular_DLList::~Circular_DLList() {
  DLLNode *head = tail->prev;
  while(tail != head) {
    tail = tail->next;
    delete tail->prev;
  }
  delete head;
}

void Circular_DLList::add_to_tail(int a) {
  if (tail != 0){ // If tail has a value.
    DLLNode *temp = new DLLNode(a, tail, tail->next);
    if (tail->prev == tail) { // If only one element exists,
      tail->prev = temp; // reroute tail->prev to new element.
    }
    tail->next = temp;
    tail = temp;
    tail->next->prev = tail;
  } else { // First element case
    tail = new DLLNode(a, tail, tail);
    tail->next = tail; // Tail does not have address,
    tail->prev = tail; // have to fix manually.
  }

}

int Circular_DLList::delete_from_tail() {
  DLLNode *p = tail; // Temp pointer to keep track of the element until deleted.
  int a = p->info; // Catching info so we can delete before return.
  if (tail->next != tail) { // Don't do this if list is length 1.
    tail = tail->prev;
    tail->next = p->next;
    tail->next->prev = tail;
    delete p;
  }
  return a;
}

bool Circular_DLList::is_in_list(int a) {
  if (tail->info == a) return true; // Checking first element, our loop doesn't.
  DLLNode *p = tail->next; // Pointer passes through each element except the fisrt.
  while (p != tail) {
    if (p->info == a) return true;
    else p = p->next; // Proceed to the next element.
  }
  return false; // Not found.
}

void Circular_DLList::print_list() {
  cout << tail->info << endl;
  DLLNode *p = tail->prev;
  while (p != tail) {
    cout << p->info << endl;
    p = p->prev;
  }
}
