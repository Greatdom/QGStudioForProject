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

ListStack* NewStack();//初始化栈
ListStack* EnStack(ListStack* liststack);//栈顶增加一个栈节点
ListStack* DeStack(ListStack* liststack);//栈底减少一个栈节点
ListStack* TurnStack(ListStack* liststack);//将栈底设置为栈顶
bool IsNotEmpty(ListStack* liststack);//判断栈是否为空
int Length(ListStack* liststack);//判断栈的长度
ListStack* Destory(ListStack* liststack);//销毁栈
void Print(ListStack* liststack);//遍历输出栈从顶至底

ListStack* GetFormula(ListStack* liststack);//得到中缀表达式
double GetDouble(double OriNum, bool IsDouble, bool IsNagetive, int DecimalPoint);//得到一个Double数字
bool IsLegal(ListStack* liststack);//判断中缀表达式是否合法
ListStack* SwitchMidToHind(ListStack* Mid,ListStack* Hind);//将中缀表达式转换为后缀表达式
bool SwitchMidToHindForOperaterIsOk(ListStack* Operators,char Now);//比较两个运算符中Now是否更优先
double Caculate(ListStack* formula);//计算后缀

#endif