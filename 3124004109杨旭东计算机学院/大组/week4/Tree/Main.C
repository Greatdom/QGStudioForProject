#include<stdio.h>
#include"QG.h"

int main() {
	Tree* tree = CreateTextTree();
	
	tree=TurnToHeap(tree, 'S');
	
    printf("����ݹ����\n");
	inorderI(tree->root);
	tree = DeStroyTree(tree);
	//BST_levelOrder(tree->root);
	/*printf("����ݹ����\n");
	BST_preorderI(tree->root);*/
	/*printf("����ݹ����\n");
	BST_inorderI(tree->root);
	printf("����ݹ����\n");
	BST_postorderI(tree->root); */
}