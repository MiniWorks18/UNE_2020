// main.cc
#include "Rational.h"
#include <iostream>
using namespace std;

int main()
{
    // Testing the rational number class.

    // Testing the constructors.
    Rational a(1,-3);
    Rational b(1,3);
    Rational c(5,10);
    // Testing the overloaded stream insertion operator.
    cout << a << endl;
    cout << b << endl;
    cout << c << endl;
    // Testing the overloaded + operator.
    Rational d = b + c;
    cout << d << endl;
    return 0;
}
