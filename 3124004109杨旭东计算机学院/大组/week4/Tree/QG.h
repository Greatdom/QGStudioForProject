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
	char TreeStatus;//�������࣬��N����δ���壬��B�������������������������H�����
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
Tree* DeTree(Tree* tree, int value);//BST��ɾ��ֵ
TreeNode* BST_SelectTree(Tree* tree, int value);//BST����ѯֵ
Tree* DeStroyTree(Tree* tree);//�ݻ���

void preorderI(TreeNode* node);//�ݹ��������
void preorderR(TreeNode* node);//�ǵݹ��������
void inorderI(TreeNode* node);//�ݹ��������
void inorderR(TreeNode* node);//�ǵݹ��������
void postorderI(TreeNode* node);//�ݹ�������
void postorderR(TreeNode* node);//�ǵݹ�������
void levelOrder(TreeNode* node);//�㼶����

void HeapUp(TreeNode* node,char kind);//�����ϵ����㷨,char��ʾ�������ֶѣ�B���ϴ�ѣ�S����С��
void HeapDown(TreeNode* node);//�����µ����㷨
Tree* TurnToHeap(Tree* tree,char kind);//ת���ɶ�

Tree* CreateTextTree();//���ɲ�������


#endif