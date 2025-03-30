#include"QG.h"
#include<stdio.h>
#include<stdbool.h>
#include<stdlib.h>

List* NewList(){
	List* list = (List*)malloc(sizeof(List));
	if (list == NULL) {
		printf("初始化链表失败");
		exit(EXIT_FAILURE);
	}
	else {
		list->head = NULL;
		list->mid = NULL;
		list->hind = NULL;
		list->Length = 0;
		return list;
	}
}
bool IsNotEmpty(List* list) {
	if (list == NULL) {
		printf("这是未初始化栈");
		exit(EXIT_FAILURE);
	}
	else {
		if (list->Length == 0)
			return false;
		else
			return true;
	}
}
List* EnList(List* list) {
	ListNode* node = (ListNode*)malloc(sizeof(ListNode));
	if (node == NULL) {
		printf("初始化栈节点失败");
		exit(EXIT_FAILURE);
	}
	else {
		node->front = NULL;
		node->next = NULL;
		if (IsNotEmpty(list)) {
			if (list->head->Count != 0 || list->hind->Count != list->Length - 1) {
				printf("这不是主链表");
				free(node);
				return list;
			}
			node->Count = list->hind->Count + 1;
			list->hind->next = node;
			node->front = list->hind;
		}
		else {
			node->Count = 0;
			list->head = node;
			list->mid = node;
		}
		
		list->Length++;
		list->hind = node;
		if (list->mid->Count != ((list->Length) / 2)) {
			list->mid = list->mid->next;
		}
		return list;
	}
}
List* DeList(List* list) {
	if (IsNotEmpty(list)) {
		if (list->head->Count != 0 || list->hind->Count != list->Length - 1) {
			printf("这不是主链表");
			return list;
		}
		if (list->Length <= 1) {
			free(list->hind);
			list->hind = NULL;
			list->head = NULL;
			list->mid = NULL;
		}
		else {
			list->hind = list->hind->front;
			free(list->hind->next);
			list->hind->next = NULL;
			
		}
		list->Length--;
		/*if (list->mid!=NULL&& (list->mid->Count != ((list->Length) / 2))) {
			list->mid = list->mid->front;
		}*/
		if (list->Length == 0);
		else if (list->Length <= 4) {
			list->mid = list->head;
			while (list->mid->Count != ((list->Length-1) / 2))
				list->mid = list->mid->next;
		}
		else {
			if (list->mid->Count != ((list->Length-1) / 2))
				list->mid = list->mid->front;
		}
		return list;
	}
	else {
		free(list);
		return NULL;
	}
}
List* DeStory(List* list) {
	if (list->head->Count==0&&list->hind->Count==list->Length-1) {
		while (list != NULL) {
			list = DeList(list);
		}
		return list;
	}
	else {
		/*list->head = NULL;
		list->hind = NULL;
		list->mid = NULL;
		list->Length = 0;*/
		free(list);
		return NULL;
	}
	
}
void Print(List* list) {
	int Count = 0;//改善输出格式
	if (list == NULL||list->Length == 0) {
		printf("\nEmptyList\n");
		return;
	}
	ListNode* point = list->head;
	printf("\n");
	while (point->next!=list->hind->next) {
		printf("No.%d: %8.3f | ", point->Count, point->Double);
		point = point->next;
		if (Count % 4 == 0)
			printf("\n");
		Count++;
	}
	printf("No.%d: %8.3f | ", point->Count, point->Double);
	printf("\nNo.mid:%d ; No.hind:%d ; No.Length:%d\n", list->mid->Count, list->hind->Count, list->Length);
}
void TurnList(ListNode* node1, ListNode* node2) {
	double Temp;
	Temp = node1->Double;
	node1->Double = node2->Double;
	node2->Double = Temp;
}
ListNode* GetListNodeByList(List* list, int num) {
	ListNode* point = NULL;
	if (list == NULL||list->Length==0) {
		printf("这是空链表");
		return NULL;
	}
	else if (num > list->hind->Count) {
		printf("试图索取过大的下标的节点");
		return NULL;
	}
	else if (num < list->head->Count) {
		printf("试图索取过小下标的节点");
		return NULL;
	}
	if (num == list->head->Count) {
		return list->head;
	}
	else if (num == list->hind->Count) {
		return list->hind;
	}
	else if (num == list->mid->Count) {
		return list->mid;
	}
	else if (num < list->mid->Count) {
		if (num < (list->mid->Count) / 2) {
			point = list->head->next;
			while (point->Count != num) {
				point = point->next;
			}
			return point;
		}
		else {
			point = list->mid->front;
			while (point->Count != num) {
				point = point->front;
			}
			return point;
		}
	}
	else if (num > list->mid->Count) {
		if (num > (list->mid->Count + list->hind->Count) / 2) {
			point = list->hind->front;
			while (point->Count != num) {
				point = point->front;
			}
			return point;
		}
		else {
			point = list->mid->next;
			while (point->Count != num) {
				point = point->next;
			}
			return point;
		}

	}
}
ListNode* GetListNodeByNode(ListNode* node, int num) {
	if (node->Count == num) {
		return node;
	}
	else if (node->Count > num) {
		while (node->Count != num) {
			if (node->front == NULL) {
				printf("试图索取过小的下标的节点");
				return NULL;
			}
			node = node->front;
		}
		return node;
	}
	else {
		while (node->Count != num) {
			if (node->next == NULL) {
				printf("试图索取过大的下标的节点");
				return NULL;
			}
			node = node->next;
		}
		return node;
	}
}
List* NewBranchList(List* list, int head, int hind) {
	if (head<list->head->Count || hind>list->hind->Count||head>hind) {
		printf("不合法的分支");
		return NULL;
	}
	List* BranchList = NewList();
	BranchList->Length = hind - head+1;
	BranchList->hind = GetListNodeByList(list, hind);
	BranchList->head = GetListNodeByList(list, head);
	BranchList->mid = GetListNodeByList(list, head+(BranchList->Length-1)/2);
	return BranchList;
}