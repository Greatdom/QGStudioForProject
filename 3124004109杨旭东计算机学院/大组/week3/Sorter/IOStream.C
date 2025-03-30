#include"QG.h"
#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include<time.h>

void CreateRandomData(int num) {
	FILE* fp;
	if ((fp = fopen("input.txt", "w")) == NULL) {
		printf("创建待排序数据错误");
		exit(EXIT_FAILURE);
	}
	srand((unsigned int)time(NULL));
	double RandNum;
	for (int i = 0; i < num; i++) {
		RandNum = (double)rand();
		//if (i % 4 == 0)RandNum *= -1;
		fprintf(fp, "%.3f ", RandNum);
		if (i % 10 == 9)fprintf(fp, "\n");
	}
	fclose(fp);
}
List* GetInitialData(List* list) {
	FILE* fp;
	if ((fp = fopen("input.txt", "r")) == NULL) {
		printf("创建待排序数据错误");
		exit(EXIT_FAILURE);
	}
	double RandNum; int count = 1;
	while (fscanf(fp, "%lf", &RandNum) == 1) {
		list = EnList(list);
		list->hind->Double = RandNum;
	}
	fclose(fp);
	return list;
}
int GetDataNum() {
	int DataNum=0;//储存输入的数字
	char Char;
	char Prev = 'A';
	bool RETURN_KEY = true;//判断是否完成正确的输入
	printf("请输入合适的数字(2~999999)");
	while (RETURN_KEY) {
		if (RETURN_KEY) {
			printf("请重新输入合适的数字\n");
		}
		int NumCount = 0;//统计输入的位数
		
        RETURN_KEY = false;
        bool SPACE_KEY_FRONT = false;
		while ((Char = getchar())!=EOF&&Char!='\n') {
			
			//判断输入空格的合法性
			if (Char >= '0' && Char <= '9') {
				DataNum = DataNum * 10 + (Char - '0');
				if (Prev == ' '&& SPACE_KEY_FRONT) {
					printf("非合法数字\n");
					RETURN_KEY = true;
					break;
				}
				NumCount++;
				if (NumCount >= 7) {
					printf("理论是可以安排无限的数据，但为了爱护我电脑的CPU，所以给出了限制Doge\n");
					RETURN_KEY = true;
					break;
				}
			}
			else if (Char == ' ') {
				if (Prev != 'A') {
					if (Prev >= '0' && Prev <= '9')SPACE_KEY_FRONT = true;
				}
				
			}
			else{
				printf("非合法数字\n");
				RETURN_KEY = true;
				break;
			}
			Prev = Char;
		}
		if (DataNum <= 1&&RETURN_KEY==false) {
			printf("小于2就排序不了了\n");
			RETURN_KEY = true;
			Char = -1;
		}
		if ((int)Char == 26)Char = -1;
		if (RETURN_KEY == true&&(int)Char != -1) {
			while (getchar() != '\n')continue;
			DataNum = 0;
		}
		if (RETURN_KEY == true) {
			DataNum = 0;
			Char = 'A';
			Prev = 'A';
		}
	}
	return DataNum;
}
List* OutputData(List* list) {
	FILE* fp;
	if ((fp = fopen("output.txt", "w")) == NULL) {
		printf("创建待排序数据错误");
		exit(EXIT_FAILURE);
	}
	ListNode* point = list->head;
	while (point->next != list->hind->next) {
		fprintf(fp, "%.3f ", point->Double);
		if (point->Count % 10 == 9)fprintf(fp, "\n");
		point = point->next;
	}
	fprintf(fp, "%.3f ", point->Double);
	fclose(fp);
	return list;
}