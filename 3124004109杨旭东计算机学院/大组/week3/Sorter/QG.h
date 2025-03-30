#pragma once
#ifndef _QG
#define _QG
#include<stdbool.h>

typedef struct ListNode {
	double Double;//�洢������
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

List* NewList();
bool IsNotEmpty(List* list);
List* EnList(List* list);//�����������ɾʱ�ǵý�ͷ��β����У��
List* DeList(List* list);
List* DeStory(List* list);
void TurnList(ListNode* node1, ListNode* node2);//��������������ݶԵ�
ListNode* GetListNodeByList(List* list, int num);//ͨ��������õ��ض����±�Ľڵ�
ListNode* GetListNodeByNode(ListNode* node, int num);//ͨ������ڵ�õ��ض����±�Ľڵ�
List* NewBranchList(List* list, int head, int hind);//�õ���֧�ڵ�
void Print(List* list);

int GetDataNum();//���벢����һ������������һ�����������
void CreateRandomData(int num);//����������������ݵ��ļ�input.txt�������������ɶ�������
List* GetInitialData(List* list);//���ļ�input.txt�õ�δ��������ݣ�����Ҫ�������ʼ��һ������
List* OutputData(List* list);//������ݵ�output.txt����Ҫ��������

List* InsertSort(List* list);//��������
List* MergeSort(List* list);//�鲢����
List* QuickSort(List* list);//��������
List* CountSort(List* list);//��������
List* RadixCountSort(List* list);//������������

#endif
