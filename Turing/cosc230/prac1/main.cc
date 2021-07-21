// main.cc
#include <iostream>
#include "Bank_Account.h"
using namespace std;

int main()
{
    // Object instantiation
    Bank_Account customer1;
    Bank_Account customer2("Nunya", 123, 1000.0);

    // Output values for c1
    cout << "Name: " << customer1.get_customer_name() << endl;
    cout << "Account number: " << customer1.get_account_number() << endl;
    cout << "Balance: " << customer1.get_balance() << endl;

    // Output values for c2
    cout << "Name: " << customer2.get_customer_name() << endl;
    cout << "Account Number: " << customer2.get_account_number() << endl;
    cout << "Balance: " << customer2.get_balance() << endl;

    // Testing set function
    customer2.set_balance(200.0);
    cout << "Balance: " << customer2.get_balance() << endl;
    
    return 0;

}
