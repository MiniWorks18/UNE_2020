class ETNode {
public:
		ETNode() {left = right = 0;}
		ETNode(char c) {value = c, left = right = 0;}
		char value;
		ETNode *left, *right;
};

// A very simple expression tree class that converts a parenthesized input
// consisting of addition and multiplication operations, into an expression tree.
// Traversal of this expression tree generates various Polish notations.

class Expression_Tree {
public:
	Expression_Tree() { root = 0; };
	//Expression_Tree(ETNode *r = 0) {root = r;}
	~Expression_Tree() { clear(root); }
	void build_expression_tree(char[], int);
	//void insert(char);
	void set_root(ETNode *n) {root = n;}
	void inorder() { inorder(root); }
	void preorder() { preorder(root); }
	void postorder() {postorder(root); }
private:
	ETNode* root;
	void visit(ETNode*);
	void inorder(ETNode*);
	void preorder(ETNode*);
	void postorder(ETNode*);
	void clear(ETNode*);
};
