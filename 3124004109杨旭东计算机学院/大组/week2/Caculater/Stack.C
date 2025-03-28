#include"QG.h"
#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

ListStack* NewStack() {
	
	 ListStack *liststack = (ListStack*)malloc(sizeof(ListStack));
	if (liststack == NULL) {
		printf("初始化栈失败");
		exit(EXIT_FAILURE);
	}
	else {
		liststack->top = NULL;
		liststack->count = 0;
		return liststack;
	}
}

bool IsNotEmpty(ListStack* liststack) {
	if (liststack == NULL) {
		printf("这是空栈");
		exit(EXIT_FAILURE);
	}
	else {
		if (liststack->top == NULL)
			return false;
		else
			return true;
	}
}

ListStack* EnStack(ListStack* liststack) {
	StackNode* node = (StackNode*)malloc(sizeof(StackNode));
	if (node == NULL) {
		printf("初始化栈节点失败");
		exit(EXIT_FAILURE);
	}
	else {
		node->front = NULL;
		node->next = NULL;
		if (IsNotEmpty(liststack)) {
			liststack->top->next = node;
			node->front = liststack->top;
		}
		liststack->count++;
		liststack->top = node;
		return liststack;
	}
}

ListStack* DeStack(ListStack* liststack) {
	if (IsNotEmpty(liststack)) {
		if (liststack->count <= 1) {
			free(liststack->top);
			liststack->top = NULL;
		}
		else {
			liststack->top = liststack->top->front;
			free(liststack->top->next);
			liststack->top->next = NULL;
		}
		liststack->count--;
		return liststack;
	}
	else {
		free(liststack);
		return NULL;
	}
}

int Length(ListStack* liststack) {
	return liststack->count;
}

ListStack* Destory(ListStack* liststack) {
	while (liststack != NULL) {
		liststack = DeStack(liststack);
	}
}

ListStack* TurnStack(ListStack* liststack) {
	ListStack* temp = NewStack();
	while (liststack->count != 0) {
		temp = EnStack(temp);
		temp->top->IsChar = liststack->top->IsChar;
		if (temp->top->IsChar == true) {
			temp->top->Char = liststack->top->Char;
		}
		else {
			temp->top->Double = liststack->top->Double;
		}
		liststack = DeStack(liststack);
	}
	Destory(liststack);
	return temp;
}

void Print(ListStack* liststack) {
	StackNode* point;
	point = liststack->top;
	while (point->front != NULL) {
		if (point->IsChar == false) {
			printf("%.3f ", point->Double);
		}
		else
			printf("%c ", point->Char);
		point = point->front;
	}
	if (point->IsChar == false) {
		printf("%.3f ", point->Double);
	}
	else
		printf("%c ", point->Char);
	printf("\n");
}
