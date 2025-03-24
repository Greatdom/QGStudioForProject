#include"QG.h"
#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

ListStack* GetFormula(ListStack* liststack) {
	char Prev = 'A';//前一个字符
	char Temp;//当前字符
	int TempInt = 0;//当前字符转换的数字形式
	double TempNumber = 0;//当前集成的数字
	bool IsDouble = false;//是否小数
	int DecimalPoint = 0;//小数位，默认0
	bool IsNegative = false;//是否负数
	bool RETURN_KEY = true;//判断是否完成中缀表达式输入
	while (RETURN_KEY && (Temp = getchar())) {
		if (Temp >= '0' && Temp <= '9') {
			//若前面为‘）’则非法
			if (Prev == ')') {
				printf("非法表达式：非法的符号位置");
				exit(EXIT_FAILURE);
			}
			else {
				if (IsDouble) {
					DecimalPoint++;
				}
				TempInt = Temp - '0';
				TempNumber = TempNumber * 10 + TempInt;
			}
		}
		else if (Temp == '.') {
			//若前面无数字则非法
			if (!(Prev >= '0' && Prev <= '9')) {
				printf("非法表达式:错误的小数点位置");
				exit(EXIT_FAILURE);
			}
			else {
				if (IsDouble == true) {
					printf("非法表达式:错误的小数点位置");
					exit(EXIT_FAILURE);
				}
				else {
					IsDouble = true;
				}
			}
		}
		else if (Temp == ' ')continue;
		else {

			bool IsCaled = false;

			if (Prev == '.') {
				printf("非法表达式:错误的小数点位置");
				exit(EXIT_FAILURE);
			}
			switch (Temp) {
			case '\n':
				//若前面是除了右括号外的非数字符号则非法
				if (Prev != ')' && !(Prev >= '0' && Prev <= '9')) {
					printf("非法表达式：非法的符号位置");
					exit(EXIT_FAILURE);
				}
				else {
					//处理可能的数字
					if (Prev != ')') {
						liststack = EnStack(liststack);
						liststack->top->Double = GetDouble(TempNumber, IsDouble, IsNegative, DecimalPoint);
						liststack->top->IsChar = false;
						IsCaled = true;
					}
					
				}
				RETURN_KEY = false; break;
			case '+':
			case '*':
			case '%':
			case '/':
				if (Temp == '/')Temp = '%';
				//若前面是除了右括号外的非数字符号则非法
				if (Prev!=')'&&!(Prev >= '0' && Prev <= '9')) {
					printf("非法表达式：非法的符号位置");
					exit(EXIT_FAILURE);
				}
				else {
					//处理数字和字符
					if (Prev != ')') {
						liststack = EnStack(liststack);
						liststack->top->Double = GetDouble(TempNumber, IsDouble, IsNegative, DecimalPoint);
						liststack->top->IsChar = false;
						IsCaled = true;
					}
					liststack = EnStack(liststack);
					liststack->top->Char = Temp;
					liststack->top->IsChar = true;
				}
				break;
			case '(':
				//若前面是数字或者右括号则非法
				if ((Prev >= '0' && Prev <= '9') || Prev == ')') {
					printf("非法表达式：非法的符号位置");
					exit(EXIT_FAILURE);
				}
				else {
					//处理字符
					liststack = EnStack(liststack);
					liststack->top->Char = Temp;
					liststack->top->IsChar = true;
				}
				break;
			case ')':
				//若前面是数字或者右括号则合法
				if ((Prev >= '0' && Prev <= '9') || Prev == ')') {
					//处理数字和字符
					if (Prev != ')') {
						liststack = EnStack(liststack);
						liststack->top->Double = GetDouble(TempNumber, IsDouble, IsNegative, DecimalPoint);
						liststack->top->IsChar = false;
						IsCaled = true;
					}
					liststack = EnStack(liststack);
					liststack->top->Char = Temp;
					liststack->top->IsChar = true;
				}
				else {
					printf("非法表达式：非法的符号位置");
					exit(EXIT_FAILURE);
				}
				break;
			case '-':
				if (Prev == 'A') {
					//处理数字和字符
					
						liststack = EnStack(liststack);
						liststack->top->Double = 0.0;
						liststack->top->IsChar = false;
						IsCaled = true;
					
					liststack = EnStack(liststack);
					liststack->top->Char = Temp;
					liststack->top->IsChar = true;
			}else
			{
				//若前面是除了右括号外的非数字符号判定是否可以作为负数
				if (Prev != ')' && !(Prev >= '0' && Prev <= '9')) {
					if (Prev == '-' || IsNegative == true) {
						printf("非法表达式：非法的符号位置");
						exit(EXIT_FAILURE);
					}
					else {
						IsNegative = true;
					}
				}
				else {
					//处理数字和字符
					if (Prev != ')') {
						liststack = EnStack(liststack);
						liststack->top->Double = GetDouble(TempNumber, IsDouble, IsNegative, DecimalPoint);
						liststack->top->IsChar = false;
						IsCaled = true;
					}
					liststack = EnStack(liststack);
					liststack->top->Char = Temp;
					liststack->top->IsChar = true;
				}
			}
				break;
			default:
				printf("非法表达式：无法识别的符号");
				exit(EXIT_FAILURE);
			}

			if(IsCaled){
				//初始化数字
				TempInt = 0;
				TempNumber = 0;
				IsDouble = false;
				DecimalPoint = 0;
				IsNegative = false;
				IsCaled = false;
			}

		}
		Prev = Temp;
	}
	if(!IsLegal(liststack))exit(EXIT_FAILURE);
	return liststack;
}

bool IsLegal(ListStack* liststack) {
	StackNode* point = liststack->top;

	int Bracket = 0;//左括号和右括号的差值

	while (point->front != NULL) {
		if (point->IsChar == true) {
			if (point->Char == '(')Bracket--;
			if (point->Char == ')')Bracket++;
		}
		point = point->front;
	}
	if (point->IsChar == true) {
		if (point->Char == '(')Bracket--;
		if (point->Char == ')')Bracket++;
	}
	if (Bracket != 0) {
		printf("非法表达式：不合法的括号");
		return false;
		}
	while (point->next != NULL) {
		if (point->IsChar == true && point->Char == '%') {
			if (point->next->IsChar == false && point->next->Double == 0.0) {
				printf("非法表达式：除法为零！");
				return false;
			}
		}
		point = point->next;
	}
	return true;
}

double GetDouble(double OriNum, bool IsDouble, bool IsNagetive, int DecimalPoint) {
	if (IsDouble) {
		OriNum = OriNum / (DecimalPoint * 10);
	}
	if (IsNagetive) {
		OriNum = OriNum * (-1);
	}
	return OriNum;
}