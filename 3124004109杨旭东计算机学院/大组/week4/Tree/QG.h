#pragma once
#ifndef _QG
#define _QG
#include<stdbool.h>


typedef struct ListNode {
	struct TreeNode* Point;//��������ڵ�
	int Count;//����ڵ��λ�ã�����Ϊ��ͷ
	struct ListNode* front;
	struct ListNode* next;
}ListNode;

typedef struct List {
	int Length;//������
	ListNode* head;//����ͷ
	ListNode* mid;//��������
	ListNode* hind;//����β
}List;

typedef struct TreeNode {
	int value;
	struct TreeNode* left;
	struct TreeNode* right;
	struct TreeNode* parent;
}TreeNode;

typedef struct Tree {
	TreeNode* root;
	int Length;
}Tree;


List* NewList();
bool IsNotEmptyList(List* list);
List* EnList(List* list);//�����������ɾʱ�ǵý�ͷ��β����У��
List* DeList(List* list);
List* DeStory(List* list);
ListNode* GetListNodeByList(List* list, int num);//ͨ��������õ��ض����±�Ľڵ�
ListNode* GetListNodeByNode(ListNode* node, int num);//ͨ������ڵ�õ��ض����±�Ľڵ�
List* NewBranchList(List* list, int head, int hind);//�õ���֧�ڵ�
void Print(List* list);


Tree* NewTree();//����һ������
bool IsNotEmptyTree(Tree* tree); 
Tree* EnTree_BST(Tree* tree,int value);//BST������ֵ
Tree* DeTree_BST(Tree* tree, int value);//BST��ɾ��ֵ
TreeNode* SelectTree_BST(Tree* tree, int value);//BST����ѯֵ
Tree* DeStroyTree(Tree* tree);//�ݻ���

void BST_preorderI(TreeNode* node);//�ݹ��������
void BST_preorderR(TreeNode* node);//�ǵݹ��������
void BST_inorderI(TreeNode* node);//�ݹ��������
void BST_inorderR(TreeNode* node);//�ǵݹ��������
void BST_postorderI(TreeNode* node);//�ݹ�������
void BST_postorderR(TreeNode* node);//�ǵݹ�������
void BST_levelOrder(TreeNode* node);//�㼶����

Tree* CreateTextTree();//���ɲ�������


#endif