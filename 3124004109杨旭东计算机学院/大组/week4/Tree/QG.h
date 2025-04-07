#pragma once
#ifndef _QG
#define _QG
#include<stdbool.h>


typedef struct ListNode {
	struct TreeNode* Point;//储存的树节点
	int Count;//这个节点的位置，以零为开头
	struct ListNode* front;
	struct ListNode* next;
}ListNode;

typedef struct List {
	int Length;//链表长度
	ListNode* head;//链表头
	ListNode* mid;//链表中心
	ListNode* hind;//链表尾
}List;

typedef struct TreeNode {
	int value;
	struct TreeNode* left;
	struct TreeNode* right;
	struct TreeNode* parent;
}TreeNode;

typedef struct Tree {
	TreeNode* root;
	char TreeStatus;//树的种类，‘N’则未定义，‘B’则二叉排序树及其衍生，‘H’则堆
	int Length;
}Tree;


List* NewList();
bool IsNotEmptyList(List* list);
List* EnList(List* list);//对链表进行增删时记得将头中尾进行校对
List* DeList(List* list);
List* DeStory(List* list);
ListNode* GetListNodeByList(List* list, int num);//通过链表本身得到特定的下标的节点
ListNode* GetListNodeByNode(ListNode* node, int num);//通过链表节点得到特定的下标的节点
List* NewBranchList(List* list, int head, int hind);//得到分支节点
void Print(List* list);


Tree* NewTree();//生成一个空树
bool IsNotEmptyTree(Tree* tree); 
Tree* EnTree_BST(Tree* tree,int value);//BST树插入值
Tree* DeTree(Tree* tree, int value);//BST树删除值
TreeNode* BST_SelectTree(Tree* tree, int value);//BST树查询值
Tree* DeStroyTree(Tree* tree);//摧毁树

void preorderI(TreeNode* node);//递归先序遍历
void preorderR(TreeNode* node);//非递归先序遍历
void inorderI(TreeNode* node);//递归中序遍历
void inorderR(TreeNode* node);//非递归中序遍历
void postorderI(TreeNode* node);//递归后序遍历
void postorderR(TreeNode* node);//非递归后序遍历
void levelOrder(TreeNode* node);//层级遍历

void HeapUp(TreeNode* node,char kind);//堆向上调整算法,char表示生成哪种堆，B则上大堆，S则上小堆
void HeapDown(TreeNode* node);//堆向下调整算法
Tree* TurnToHeap(Tree* tree,char kind);//转换成堆

Tree* CreateTextTree();//生成测试用例


#endif