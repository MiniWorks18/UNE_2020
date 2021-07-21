#include <iostream>
#include <stack>
#include <queue>
#include <string>

using namespace std;


// class Palindrome {
// public:
//   Palindrome();
// private:
//
// }

int main()
{
  const std::string input = "a man, a plan, a canal; panama";

  queue<char> q;
  stack<char> sta;
  bool palindrome = true;

  for (int i = 0; i < input.length(); i++) {
    if (input.at(i) > 96 && input.at(i) < 123) { // Filters non alphabeticals
    q.push(input.at(i));
    sta.push(input.at(i));
    }
  }

  while (!q.empty()) {
    if (q.front() != sta.top()){
      palindrome = false;
    }
    q.pop();
    sta.pop();
  }

  cout << palindrome << endl;

  return 0;
}
