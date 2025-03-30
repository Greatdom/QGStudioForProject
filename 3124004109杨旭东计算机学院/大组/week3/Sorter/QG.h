#pragma once
#ifndef _QG
#define _QG
#include<stdbool.h>

typedef struct ListNode {
	double Double;//存储的数据
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

List* NewList();
bool IsNotEmpty(List* list);
List* EnList(List* list);//对链表进行增删时记得将头中尾进行校对
List* DeList(List* list);
List* DeStory(List* list);
void TurnList(ListNode* node1, ListNode* node2);//将两个链表的数据对调
ListNode* GetListNodeByList(List* list, int num);//通过链表本身得到特定的下标的节点
ListNode* GetListNodeByNode(ListNode* node, int num);//通过链表节点得到特定的下标的节点
List* NewBranchList(List* list, int head, int hind);//得到分支节点
void Print(List* list);

int GetDataNum();//输入并返回一个数用来生成一定量的随机数
void CreateRandomData(int num);//生成随机待排序数据到文件input.txt，参数代表生成多少数据
List* GetInitialData(List* list);//从文件input.txt得到未处理的数据，其中要在里面初始化一个链表
List* OutputData(List* list);//输出数据到output.txt但不要销毁链表

List* InsertSort(List* list);//插入排序
List* MergeSort(List* list);//归并排序
List* QuickSort(List* list);//快速排序
List* CountSort(List* list);//计数排序
List* RadixCountSort(List* list);//基数计数排序

#endif
