#include <string>
#include <iostream>

class Bank_Account {
public:
    // Default constructor
    Bank_Account(): account_number(), balance() { }

    // Constructor
    Bank_Account(std::string a, int b, double c = 0.0)
    {
        customer_name = a;
        account_number = b;
        balance = c;
    }

    // Set functions
    void set_balance(double a) { balance = a;}
    
    // Get functions
    std::string get_customer_name() const { return customer_name; }
    int get_account_number() const { return account_number; }
    double get_balance() const { return balance; }

private: 
    // Variables
    std::string customer_name;
    int account_number;
    double balance;
};




int main() 
{
    // Object instantiation
    Bank_Account customer1;
    Bank_Account customer2("David", 123, 100.0);

    // Ouput values for customer1
    std::cout << "Name: " << customer1.get_customer_name() << std::endl;
    std::cout << "Account number: " << customer1.get_account_number() << std::endl;
    std::cout << "Balance: " << customer1.get_balance() << std::endl;

    // Outpute values for customer2
    std::cout << "Name: " << customer2.get_customer_name() << std::endl;
    std::cout << "Account number: " << customer2.get_account_number() << std::endl;
    std::cout << "Balance: " << customer2.get_balance() << std::endl;

    // Test set
    customer2.set_balance(200.0);
    std::cout << "Balance is now :" << customer2.get_balance() << std::endl;

return 0;
}
