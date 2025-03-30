#include<stdio.h>
#include<stdlib.h>
#include"QG.h"

int main() {
	
	int DataNum = GetDataNum();	
	
	CreateRandomData(DataNum);
	List* list=NewList();
	GetInitialData(list);
	//生成一组随机数据并封装到List里

	//插入排序
	//list = InsertSort(list);

	//快速排序
	//list = QuickSort(list);

	//计数排序
	//list = CountSort(list);

	list = OutputData(list);
}