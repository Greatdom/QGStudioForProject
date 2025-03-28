#include<stdio.h>
#include"QG.h"

int main() {



	//实例化一个栈，这个栈储存中缀表达式
	ListStack* MidFormula = NewStack();
	MidFormula = GetFormula(MidFormula);
	MidFormula = TurnStack(MidFormula);

	/*MidFormula = TurnStack(MidFormula);
	Print(MidFormula);*/


	//实例化一个栈，这个栈储存后缀表达式
	ListStack* HindFormula = NewStack();
	/*Print(MidFormula);*/
	HindFormula = SwitchMidToHind(MidFormula, HindFormula);
	
	HindFormula = TurnStack(HindFormula);
    /*Print(HindFormula);*/

	//这是一个计算后缀表达式的函数
	double Result = Caculate(HindFormula);

	//这是输出结果的函数
	printf("这个中缀表达式的结果为: %.3f", Result);

	return 0;

}