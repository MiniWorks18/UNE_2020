// Bank_Account.h interface
#include <string>

class Bank_Account {
public:
    Bank_Account(): account_number(), balance() { }
    Bank_Account(std::string, int, double);
    void set_balance(double);
    std::string get_customer_name() const;
    int get_account_number() const;
    double get_balance() const;

private:
    std::string customer_name;
    int account_number;
    double balance;
};
