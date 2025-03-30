#include"QG.h"
#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

List* InsertSort(List* list) {
	ListNode* PointI = list->head;
	ListNode* PointJ = list->head;
	for (int i = list->head->Count; i <= list->hind->Count; i++) {
		PointI = GetListNodeByNode(PointI, i);
		PointJ = list->head;
		for (int j = list->head->Count; j < i; j++) {			
			PointJ = GetListNodeByNode(PointJ, j);
			if (PointI->Double < PointJ->Double) {
				int k;
				ListNode* PointK1 = PointI;
				ListNode* PointK2 = PointI;
				double Temp = PointI->Double;
				for (k = i; k > j; k--) {
					PointK1 = GetListNodeByNode(PointK1, k);
					PointK2 = GetListNodeByNode(PointK2, k - 1);
					PointK1->Double = PointK2->Double;
				}
				PointK2->Double = Temp;
				break;
			}
		}
	}
	return list;
}
List* MergeSort(List* list) {

}
List* QuickSort(List* list) {
	/*int i, j, mid, p;
	i = list->head->Count;
	j = list->hind->Count;
	mid = list->mid->Count;*/
	ListNode* PointI = list->head;
	ListNode* PointJ = list->hind;
	ListNode* PointM = list->mid;
	if (PointI == PointJ)return list;
	do {
		while (PointI->Double < PointM->Double)
			PointI = PointI->next;
		while (PointJ->Double > PointM->Double)
			PointJ = PointJ->front;
		if (PointI->Count <= PointJ->Count) {
			TurnList(PointI, PointJ);
			if (PointI->next != NULL)
				PointI = PointI->next;
			if (PointJ->front != NULL)
				PointJ = PointJ->front;
		}
	} while ((PointI->Count <= PointJ->Count));
	if (list->head->Count < PointJ->Count) {
		List* left = NewBranchList(list, list->head->Count, PointJ->Count);
		left = QuickSort(left);
		//left = DeStory(left);
		
	}
	if (PointI->Count < list->hind->Count) {
		List* right = NewBranchList(list, PointI->Count, list->hind->Count);
		right = QuickSort(right);
		//right = DeStory(right);
		
	}
	return list;
}
List* CountSort(List* list) {//只能给0~40000的整数数字进行排序
	int Data[40000] = { 0 };
	
	{
		ListNode* Point = list->head;
		int min = (int)Point->Double;
		int max = (int)Point->Double;
		if (min < 0 || max >= 39999) {
			printf("这组数据有不适合用来排序的数据");
			return list;
		}
		Data[(int)Point->Double]++;
		while (Point->next != list->hind->next) {
			Point = Point->next;
			if ((int)Point->Double < min)min = (int)Point->Double;
			if ((int)Point->Double > max)max = (int)Point->Double;
			if (min < 0 || max >= 39999) {
				printf("这组数据有不适合用来排序的数据");
				return list;
			}
			Data[(int)Point->Double]++;
		}
	}
	list = DeStory(list);
	list = NewList();
	for (int i = 0; i < 40000; i++) {
		while (Data[i] != 0) {
			Data[i]--;
			list = EnList(list);
			list->hind->Double = (double)i;
		}
	}
	
	return list;
}
List* RadixCountSort(List* list) {

}