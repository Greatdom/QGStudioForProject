#include"QG.h"
#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

ListStack* SwitchMidToHind(ListStack* Mid, ListStack* Hind) {
	ListStack* Operators = NewStack();
	//数字，直接输出
	while (IsNotEmpty(Mid)) {
		if (Mid->top->IsChar == false) {
			Hind = EnStack(Hind);
			Hind->top->IsChar = false;
			Hind->top->Double = Mid->top->Double;
			Mid = DeStack(Mid);
		}
		else {
			//左括号，直接入栈
			if (Mid->top->Char == '(') {
				Operators = EnStack(Operators);
				Operators->top->IsChar = true;
				Operators->top->Char = '(';
				Mid = DeStack(Mid);
			}else if (Mid->top->Char == ')') {//右括号，将栈顶的运算符出栈，出栈再出栈，直到左括号。括号不作输出
				Mid = DeStack(Mid);
				while (Operators->top->Char != '(') {
					Hind = EnStack(Hind);
					Hind->top->IsChar = true;
					Hind->top->Char = Operators->top->Char;
					Operators = DeStack(Operators);
				}
				if (Operators->top->Char == '(') {
					Operators = DeStack(Operators);
				}

			}
			else {
            //运算符处理
			while (!SwitchMidToHindForOperaterIsOk(Operators, Mid->top->Char)) {
				Hind = EnStack(Hind);
				Hind->top->IsChar = true;
				Hind->top->Char = Operators->top->Char;
				Operators = DeStack(Operators);
			}
			Operators = EnStack(Operators);
			Operators->top->IsChar = true;
			Operators->top->Char = Mid->top->Char;
			Mid = DeStack(Mid);
			}
			

		}
	}
	//结束时把栈剩余数据出栈到空为止
	while (IsNotEmpty(Operators)) {
		Hind = EnStack(Hind);
		Hind->top->IsChar = true;
		Hind->top->Char = Operators->top->Char;
		Operators = DeStack(Operators);
	}
	Destory(Operators);
	return Hind;
}
bool SwitchMidToHindForOperaterIsOk(ListStack* Operators, char Now) {
	if (IsNotEmpty(Operators)) {
		char Prev = Operators->top->Char;
		if (Prev == '(')return true;
		if (Now == '+' || Now == '-')return false;
		if (Now == '*' || Now == '%') {
			if (Prev == '+' || Prev == '-')return true;
			else return false;
		}
	}
	else {
		return true;
	}
}