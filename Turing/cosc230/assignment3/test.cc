#include "assignment3_functions.cc"
#include<stack>
#include<iostream>

using namespace std;

int main() {
  stack<int> S, S1, S2;
  S.push(1), S.push(2), S.push(3);
  cout << "The top element of S is: " << S.top() << endl;
  reverse_stack(S, S1, S2);
  cout << "The top element of S is now: " << S.top() << endl;


  cout << "Output for sum(4): " << sum(4) << endl;
  cout << "Output for sum(3): " << sum(3) << endl;
  cout << "Output for sum(2): " << sum(2) << endl;
  cout << "Output for sum(1): " << sum(1) << endl;

  iterative_call();
  recursive_call();

  return 0;
}
