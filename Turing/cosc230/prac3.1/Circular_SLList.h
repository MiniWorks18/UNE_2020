class SLLNode {
  public:
      SLLNode(): info() { next = 0; }
      SLLNode(int a, SLLNode *ptr = 0) { info = a; next = ptr; }
      int info;
      SLLNode *next;
};

class Circular_SLList {
  public:
    Circular_SLList() { tail = 0; }
    ∼Circular_SLList();
    bool is_empty() { return tail == 0; }
    void add_to_tail(int);
    int delete_from_tail();
    bool is_in_list(int);
    void print_list();
  private:
    SLLNode *tail;
};
