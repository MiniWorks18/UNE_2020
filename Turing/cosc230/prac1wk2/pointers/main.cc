// main.cc
#include <iostream>
using namespace std;

int main() {

int x = 2;
int* p = &x;
cout << x << endl;

*p = 1;
cout << x << endl;
}
