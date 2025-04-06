#include"QG.h"
#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

Tree* NewTree() {
	Tree* tree = (Tree*)malloc(sizeof(Tree));
	if (tree == NULL) {
		printf("初始化树失败");
		exit(EXIT_FAILURE);
	}
	else {
		tree->root = NULL;
		tree->Length = 0;
		return tree;
	}
}
bool IsNotEmptyTree(Tree* tree){
	if (tree == NULL) {
		printf("这是未初始化树");
		return false;
	}
	else {
		if (tree->Length == 0)
			return false;
		else
			return true;
	}
}

TreeNode* SelectTree_BST(Tree* tree, int value) {
	if (!IsNotEmptyTree(tree)) {
		return NULL;
	}
	else {
		TreeNode* point = tree->root;
		while (point!=NULL&&point->value!=value) {
			if (value < point->value)
				point = point->left;
			else if (value > point->value)
				point = point->right;
		}
		return point;
	}
}

Tree* EnTree_BST(Tree* tree,int value) {//没做完
	TreeNode* node = (TreeNode*)malloc(sizeof(TreeNode));
	if (node == NULL) {
		printf("初始化栈节点失败");
		exit(EXIT_FAILURE);
	}else {
		node->left = NULL;
		node->right = NULL;
		node->parent = NULL;
		node->value = value;
		if (IsNotEmptyTree(tree)) {
			TreeNode* point = tree->root;
			bool KEY = true;
			while (KEY) {
				if (value == point->value) {
					printf("试图插入已存在的节点");
					free(node);
					return tree;
				}
				else if (value < point->value) {
					if (point->left == NULL) {
						KEY = false;
						point->left = node;
					}
					else {
						point = point->left;
					}
				}
				else if (value > point->value) {
					if (point->right == NULL) {
						KEY = false;
						point->right = node;
					}
					else {
						point = point->right;
					}
				}
			}
			node->parent = point;
		}
		else {
			tree->root = node;
		}

		tree->Length++;
		return tree;
	}
}

Tree* DeTree_BST(Tree* tree, int value) {
	TreeNode* point = SelectTree_BST(tree, value);
	if (point == NULL) {
		printf("找不到待删除数据");
	}
	else {
		if (point == tree->root) {
			if (point->left == NULL && point->right == NULL) {
				free(point);
				tree->root = NULL;
			}
			else if (point->left != NULL && point->right != NULL) {//unfinished
				TreeNode* next = point->right;//待删除节点的后继节点
				while (next->left != NULL)next = next->left;
				point->value = next->value;
				point=next;//将后继节点赋值给待删除节点，使待删除节点变成不删除节点，进而后继节点删除

				if (point->left == NULL && point->right == NULL) {
					if (point->parent->left != NULL && point->parent->left->value == point->value)
						point->parent->left = NULL;
					else
						point->parent->right = NULL;
				}
				else {
					if (point->parent->left!=NULL&&point->parent->left->value == point->value)
					if (point->left != NULL) { point->parent->left = point->left; point->left->parent = point->parent; }
					else { point->parent->left = point->right; point->right->parent = point->parent; }
				else
					if (point->left != NULL) { point->parent->right = point->left; point->left->parent = point->parent; }
					else { point->parent->right = point->right; point->right->parent = point->parent; }
				}

				/*if (point->parent->left->value == point->value)
					if (point->left != NULL) { point->parent->left = point->left; point->left->parent = point->parent; }
					else { point->parent->left = point->right; point->right->parent = point->parent; }
				else
					if (point->left != NULL) { point->parent->right = point->left; point->left->parent = point->parent; }
					else { point->parent->right = point->right; point->right->parent = point->parent; }*/
				free(point);
			}
			else {
				if (point->left != NULL)
					tree->root = point->left;
				else
					tree->root = point->right;
				point->left = NULL;
				point->right = NULL;
				tree->root->parent = NULL;
				free(point);
			}
		}
		else {
			if (point->left == NULL && point->right == NULL) {
				if (point->parent->left!=NULL&&point->parent->left->value == point->value)
					point->parent->left = NULL;
				else
					point->parent->right = NULL;
				free(point);
			}
			else if (point->left != NULL && point->right != NULL) {//unfinished
				TreeNode* next = point->right;//待删除节点的后继节点
				while (next->left != NULL)next = next->left;
				point->value = next->value;
				point = next;//将后继节点赋值给待删除节点，使待删除节点变成不删除节点，进而后继节点删除
				if (point->left == NULL && point->right == NULL) {
					if (point->parent->left != NULL && point->parent->left->value == point->value)
						point->parent->left = NULL;
					else
						point->parent->right = NULL;
				}
				else {
					if (point->parent->left != NULL && point->parent->left->value == point->value)
						if (point->left != NULL) { point->parent->left = point->left; point->left->parent = point->parent; }
						else { point->parent->left = point->right; point->right->parent = point->parent; }
					else
						if (point->left != NULL) { point->parent->right = point->left; point->left->parent = point->parent; }
						else { point->parent->right = point->right; point->right->parent = point->parent; }
				}
				free(point);
			}
			else {
				if (point->parent->left!=NULL&&point->parent->left->value == point->value)
					if (point->left != NULL) { point->parent->left = point->left; point->left->parent = point->parent; }
					else { point->parent->left = point->right; point->right->parent = point->parent; }
				else
					if (point->left != NULL) { point->parent->right = point->left; point->left->parent = point->parent; }
					else { point->parent->right = point->right; point->right->parent = point->parent; }
				free(point);
			}
		}
		tree->Length--;
	}
	return tree;
}

Tree* CreateTextTree() {
	Tree* tree = NewTree();
	tree = EnTree_BST(tree, 6);
	tree = EnTree_BST(tree, 3);
	tree = EnTree_BST(tree, 5);
	tree = EnTree_BST(tree, 1);
	tree = EnTree_BST(tree, 2);
	tree = EnTree_BST(tree, 4);
	tree = EnTree_BST(tree, 8);
	tree = EnTree_BST(tree, 7);
	tree = EnTree_BST(tree, 9);
	/*printf("先序递归遍历\n");
	BST_preorderI(tree->root);
	printf("中序递归遍历\n");
	BST_inorderI(tree->root);
	printf("后序递归遍历\n");
	BST_postorderI(tree->root);*/
}

void BST_preorderI(TreeNode* node) {
	if (node == NULL) return;
	else {
		printf("Current:%6d;", node->value);
		node->left ? printf("Left:%6d;", node->left->value) : printf("Left:  NULL;");
		node->right ? printf("Right:%6d;", node->right->value) : printf("Right:  NULL;");
		node->parent ? printf("Parent:%6d;", node->parent->value) : printf("Parent:  NULL;");
		printf("\n");
		BST_preorderI(node->left);
		BST_preorderI(node->right);
	}
}
void BST_inorderI(TreeNode* node) {
	if (node == NULL) return;
	else {
		BST_inorderI(node->left);
		printf("Current:%6d;", node->value);
		node->left ? printf("Left:%6d;", node->left->value) : printf("Left:  NULL;");
		node->right ? printf("Right:%6d;", node->right->value) : printf("Right:  NULL;");
		node->parent ? printf("Parent:%6d;", node->parent->value) : printf("Parent:  NULL;");
		printf("\n");
		BST_inorderI(node->right);
	}
}
void BST_postorderI(TreeNode* node) {
	if (node == NULL) return;
	else {
		BST_postorderI(node->left);
		BST_postorderI(node->right);
		printf("Current:%6d;", node->value);
		node->left ? printf("Left:%6d;", node->left->value) : printf("Left:  NULL;");
		node->right ? printf("Right:%6d;", node->right->value) : printf("Right:  NULL;");
		node->parent ? printf("Parent:%6d;", node->parent->value) : printf("Parent:  NULL;");
		printf("\n");
	}
}
void BST_preorderR(TreeNode* node) {
	if (node == NULL)return;
	List* sort = NewList();
	List* save = NewList();
	sort = EnList(sort);
	sort->hind->Point = node;
	while (IsNotEmptyList(sort)) {
		save = EnList(save);
		save->hind->Point = sort->hind->Point;
		sort = DeList(sort);
		if (save->hind->Point->right != NULL) {
			sort = EnList(sort);
			sort->hind->Point = save->hind->Point->right;
		}
		if (save->hind->Point->left != NULL) {
			sort = EnList(sort);
			sort->hind->Point = save->hind->Point->left;
		}
	}
	printf("先序非递归遍历\n");
	Print(save);
	sort = DeStory(sort);
	save = DeStory(save);
}
void BST_inorderR(TreeNode* node) {
	if (node == NULL)return;
	List* sort = NewList();
	List* save = NewList();
	TreeNode* current = node;
	while (current != NULL || IsNotEmptyList(sort)) {
		while (current != NULL) {
			sort = EnList(sort);
			sort->hind->Point = current;
			current = current->left;
		}
		current = sort->hind->Point;
		sort = DeList(sort);
		save = EnList(save);
		save->hind->Point = current;
		current = current->right;
	}
	printf("中序非递归遍历\n");
	Print(save);
	sort = DeStory(sort);
	save = DeStory(save);

}
void BST_postorderR(TreeNode* node) {
	if (node == NULL)return;
	List* sort = NewList();
	List* temp = NewList();
	List* save = NewList();
	sort = EnList(sort);
	sort->hind->Point = node;
	while (IsNotEmptyList(sort)) {
		temp = EnList(temp);
		temp->hind->Point = sort->hind->Point;
		sort = DeList(sort);
        if (temp->hind->Point->left != NULL) {
			sort = EnList(sort);
			sort->hind->Point = temp->hind->Point->left;
		}
		if (temp->hind->Point->right != NULL) {
			sort = EnList(sort);
			sort->hind->Point = temp->hind->Point->right;
		}
		
	}

	while (IsNotEmptyList(temp)) {
		save = EnList(save);
		save->hind->Point = temp->hind->Point;
		temp = DeList(temp);
	}
	printf("后序非递归遍历\n");
	Print(save);
	sort = DeStory(sort);
	temp = DeStory(temp);
	save = DeStory(save);
}
void BST_levelOrder(TreeNode* node) {
	List* save = NewList();
	save = EnList(save);
	save->hind->Point = node;
	ListNode* point = save->head;
	while (point != NULL) {
		if (point->Point->left != NULL) {
			save = EnList(save);
			save->hind->Point = point->Point->left;
		}
		if (point->Point->right != NULL) {
			save = EnList(save);
			save->hind->Point = point->Point->right;
		}
		point = point->next;
	}
	printf("层级遍历");
	Print(save);
	save = DeStory(save);
}
Tree* DeStroyTree(Tree* tree) {
	List* sort = NewList();
	List* temp = NewList();
	sort = EnList(sort);
	sort->hind->Point = tree->root;
	while (IsNotEmptyList(sort)) {
		temp = EnList(temp);
		temp->hind->Point = sort->hind->Point;
		sort = DeList(sort);
		if (temp->hind->Point->left != NULL) {
			sort = EnList(sort);
			sort->hind->Point = temp->hind->Point->left;
		}
		if (temp->hind->Point->right != NULL) {
			sort = EnList(sort);
			sort->hind->Point = temp->hind->Point->right;
		}
	}
	int TempValue;
	while (IsNotEmptyList(temp)) {
		TempValue = temp->hind->Point->value;
		temp = DeList(temp);
		tree = DeTree_BST(tree, TempValue);
	}
	free(tree);
	sort = DeStory(sort);
	temp = DeStory(sort);
	return NULL;
}