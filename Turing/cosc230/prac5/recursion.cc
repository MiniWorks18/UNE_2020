#include<iostream>
using namespace std;

int sum(int n) {
  if (n > 0) {return n+sum(n-1);} else {return 1;}
};

int some_func(int n) {
  if (n > 100) {return n-10;} else {return some_func(some_func(n+11));}
};

int fibo(int n) {
  int a = 1;
  int b = 1;
  if (n > 2) {
    return fibo(n-1)+fibo(n-2);
  } else if (n == 2){
    return b;
  } else {
    return a;
  }
};

int fibo_loop(int n) {
  int a, b, c;
  a = 1, b = 1, c = 1;
  if (n > 2){
    for (; n > 2; --n) {
      c = a + b;
      a = b;
      b = c;
    }
    return c;
  }
  else if (n == 2)
    return b;
  else
    return a;
}

int fibo_tail(int n, int a = 1, int b = 1) {
  if (n > 2) {
    return fibo_tail(n-1, b, a+b);
  } else if (n == 2)
    return b;
    else
    return a;
}

void recursive_print_decimal_to_binary(int dec) {
  if (dec != 0) {
    recursive_print_decimal_to_binary(dec/2);
    cout << dec%2;
  };
}

int mfib(int n) {
  static int a[1000];
  a[0] = 1, a[1] = 1;

  if (!a[n-1])
    a[n-1] = mfib(n-1)+mfib(n-2);

  return a[n-1];
}

int main() {

  cout << sum(10) << endl;
  cout << some_func(10) << endl;
  cout << fibo(10) << endl;
  cout << fibo_loop(10) << endl;
  cout << fibo_tail(10) << endl;
  recursive_print_decimal_to_binary(2047);
  cout << endl;
  cout << mfib(10) << endl;

  return 0;
};
