#include <iostream>
#include "BST.h"

using namespace std;

int main() {

  // BST<int> t;
  //
  // t.insert(1);
  // t.insert(5);
  // t.insert(3);
  // t.insert(4);
  // t.insert(6);
  // t.insert(2);
  // t.insert(7);
  // t.recursive_insert(8);
  // //BSTNode<int>* d = 2;
  //
  // cout << *t.recursive_search(8) << endl;

  Balanced_BST<int> a;

  a.insert(2);
  a.insert(5);
  a.insert(7);
  a.insert(9);

  a.balance();



  return 0;
}
