#pragma once
#ifndef _QG
#define _QG
#include<stdbool.h>

typedef struct StackNode {
	double Double;
	char Char;
	bool IsChar;
	struct StackNode* next;
	struct Stack* front;
}StackNode;

typedef struct ListStack {
	StackNode* top;
	int count;
}ListStack;

ListStack* NewStack();//��ʼ��ջ
ListStack* EnStack(ListStack* liststack);//ջ������һ��ջ�ڵ�
ListStack* DeStack(ListStack* liststack);//ջ�׼���һ��ջ�ڵ�
ListStack* TurnStack(ListStack* liststack);//��ջ������Ϊջ��
bool IsNotEmpty(ListStack* liststack);//�ж�ջ�Ƿ�Ϊ��
int Length(ListStack* liststack);//�ж�ջ�ĳ���
ListStack* Destory(ListStack* liststack);//����ջ
void Print(ListStack* liststack);//�������ջ�Ӷ�����

ListStack* GetFormula(ListStack* liststack);//�õ���׺���ʽ
double GetDouble(double OriNum, bool IsDouble, bool IsNagetive, int DecimalPoint);//�õ�һ��Double����
bool IsLegal(ListStack* liststack);//�ж���׺���ʽ�Ƿ�Ϸ�
ListStack* SwitchMidToHind(ListStack* Mid,ListStack* Hind);//����׺���ʽת��Ϊ��׺���ʽ
bool SwitchMidToHindForOperaterIsOk(ListStack* Operators,char Now);//�Ƚ������������Now�Ƿ������
double Caculate(ListStack* formula);//�����׺

#endif