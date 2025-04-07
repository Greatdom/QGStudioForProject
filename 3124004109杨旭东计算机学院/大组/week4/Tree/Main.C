#include<stdio.h>
#include"QG.h"

int main() {
	Tree* tree = CreateTextTree();
	
	tree=TurnToHeap(tree, 'S');
	
    printf("ÖÐÐòµÝ¹é±éÀú\n");
	inorderI(tree->root);
	tree = DeStroyTree(tree);
	//BST_levelOrder(tree->root);
	/*printf("ÏÈÐòµÝ¹é±éÀú\n");
	BST_preorderI(tree->root);*/
	/*printf("ÖÐÐòµÝ¹é±éÀú\n");
	BST_inorderI(tree->root);
	printf("ºóÐòµÝ¹é±éÀú\n");
	BST_postorderI(tree->root); */
}