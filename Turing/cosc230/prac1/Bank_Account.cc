// Bank_Account.cc implementation
#include <string>
#include "Bank_Account.h"

Bank_Account::Bank_Account(std::string a, int b, double c = 0.0)
{
    customer_name = a;
    account_number = b;
    balance = c;
}
void Bank_Account::set_balance(double a)
{
    balance = a;
}
std::string Bank_Account::get_customer_name() const
{
    return customer_name;
}
int Bank_Account::get_account_number() const
{
    return account_number;
}
double Bank_Account::get_balance() const
{
    return balance;
}
