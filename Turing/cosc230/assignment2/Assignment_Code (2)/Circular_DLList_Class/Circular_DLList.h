#ifndef Circular_SL_LIST
#define Circular_SL_LIST

class DLLNode {
public:
	DLLNode(): info() { prev = 0, next = 0; }
	DLLNode(int a, DLLNode *p = 0, DLLNode *q = 0) { info = a; prev = p; next = q;}
	int info;
	DLLNode *prev, *next;
};

class Circular_DLList {
public:
	Circular_DLList() { tail = 0; }
	~Circular_DLList();
	bool is_empty() { return tail == 0; }
	void add_to_tail(int);
	int delete_from_tail();
	bool is_in_list(int);
	void print_list();
	int return_head()
	{
		DLLNode* head = tail->next;
		return head->info;
	}
	int return_one_before_tail()
	{
		DLLNode* one_before_tail = tail->prev;
		return one_before_tail->info;
	}
private:
	DLLNode *tail;
};

#endif 
