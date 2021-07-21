#include "Expression_Tree.h"
#include <iostream>
#include <bits/stdc++.h>
#include <stack>
using namespace std;

//template<class T>
void Expression_Tree::clear(ETNode*) {

}

stack<ETNode> sta;
stack<ETNode> storage;

// Takes top 3 items off stack, constructs subtree and places on top of stack
void package() {
  storage.push(sta.top());
  ETNode *right = &storage.top();
  sta.pop();
  storage.push(sta.top());
  ETNode *opt = &storage.top();
  sta.pop();
  storage.push(sta.top());
  ETNode *left = &storage.top();
  sta.pop();

  opt->left = left;
  opt->right = right;
  sta.push(*opt);
}

void Expression_Tree::build_expression_tree(char c[], int) {
  for (int i = 0; i < strlen(c)-1; i++) { // Loop over every char
    if (c[i] == '(') continue; // Skip left parenthesis
    if (c[i] == ')') {package(); continue;}
    else {sta.push(ETNode(c[i]));} // Add to stack
  }

  // Package up stack until one remaining node
  while (sta.size() > 1) {package();}
  // Assign root to remaining node
  root = &sta.top();
}

void Expression_Tree::visit(ETNode *p) {
  cout << p->value;
}

void Expression_Tree::inorder(ETNode *p) {
  if (p != 0) {
     inorder(p->left);
     visit(p);
     inorder(p->right);
  }
}

void Expression_Tree::preorder(ETNode *p) {
  if (p != 0) {
    visit(p);
      preorder(p->left);
      preorder(p->right);
  }
}

void Expression_Tree::postorder(ETNode *p) {
  if (p != 0) {
    postorder(p->left);
      postorder(p->right);
      visit(p);
  }
}
