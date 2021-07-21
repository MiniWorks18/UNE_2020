#include<stack>
#include<queue>
#include<iostream>
using namespace std;

// Tips stack S upside down into S1, then right side up into S2, then upside
// down back into S.
void reverse_stack(stack<int>& S, stack<int>& S1, stack<int>& S2) {
  while (!S.empty()) {
    S1.push(S.top());
    S.pop();
  }
  while (!S1.empty()) {
    S2.push(S1.top());
    S1.pop();
  }
  while (!S2.empty()) {
    S.push(S2.top());
    S2.pop();
  }
}

// Recursive function iterates over each integer from n to 1 summing the
// values in a specific speries of fractions 1/1 +1/2 -1/3 +1/4 etc.
double sum(int n) {
  int add = n;
  if (n % 2 != 0 && n != 1) add = -n; // If not even, make negative number
  if (n-1 == 0) return 1.0/add; else return 1.0/add + sum(n-1);
}

void iterative_call() {
  int call = 1;
  string statement = "This was written by call number ";
  for (; call != 5; ++call) {
    cout << statement << call << endl;
    statement = " " + statement; // adds leading white space
  }
  for (--call; call != 0; --call)
    cout << statement.erase(0,1) << call << endl; // removes leading white space
}

// Recursively adds to a queue then prints when finished
void recursive_call(int call = 1) {
  static string statement = "This was written by call number ";
  static queue<string> lines;
  static bool forward = true;

  if (call == 0) { // Print all elements of our queue
    while (!lines.empty()) {
      cout << lines.front() << endl;
      lines.pop();
    }
  } else if (call == 5) { // 5 is our turn around point
    forward = false;
    statement = statement.erase(0,1);
    recursive_call(call-1);
  } else {
    // Either adds or removes to call and statement whitespace. And adds to queue
    lines.push(statement+to_string(call));
    if (forward) {
      ++call;
      statement = " "+statement;
    } else {
      --call;
      statement = statement.erase(0,1);
    }
    recursive_call(call);
  }
}
