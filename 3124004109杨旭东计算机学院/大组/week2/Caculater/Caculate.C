#include"QG.h"
#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

double Caculate(ListStack* formula) {
	ListStack* Result = NewStack();
	while (IsNotEmpty(formula)) {
		if (formula->top->IsChar == false) {
			Result = EnStack(Result);
			Result->top->IsChar = false;
			Result->top->Double = formula->top->Double;
			formula = DeStack(formula);
		}
		else {
			char Char = formula->top->Char;
			formula = DeStack(formula);
			double Double = Result->top->Double;
			Result = DeStack(Result);
			switch (Char) {
			case '+':
				Result->top->Double += Double;
				break;
			case '-':
				Result->top->Double -= Double;
				break;
			case '*':
				Result->top->Double *= Double;
				break;
			case '%':
				if (Double == 0) {
					printf("出现了除数为零的情况");
					exit(EXIT_FAILURE);
				}
				else {
					Result->top->Double /= Double;
				}
				break;
			}
		}
	}
	double total = Result->top->Double;
	Destory(Result);
	return total;
}