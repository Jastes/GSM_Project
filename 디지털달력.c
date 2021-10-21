#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <time.h>


void PrintCal(int yearToday, int monthToday, int days, int dayToday, int NdayToday);	//달력인쇄함수 선언
int checkLeapYear(int year);	//윤년확인함수 선언, 윤년이면 1, 평년이면 0리턴

/*int main() {
	int days[2][12] = { {31,28,31,30,31,30,31,31,30,31,30,31}, {31,29,31,30,31,30,31,31,30,31,30,31} };	//평년일경우 days[0][], 윤년일경우 days[1][]

	//시간을 구조체에 저장하는 과정
	//기준일자는 1900년 1월 1일, 월은 0~11, 일은 1~31
	time_t curr;	//시간을 받을 변수
	struct tm* currTime;	//시간을 년월일시분초로 나누어 저장할 구조체
	time(&curr);	//시간변수 초기화
	currTime = localtime(&curr);	//구조체에 로컬시간 할당

	//오늘이 속한 날의 달력 출력시 이용
	int yearToday = (currTime->tm_year) + 1900;	//연
	int monthToday = (currTime->tm_mon) + 1;	//월
	int dayToday = currTime->tm_mday; //일
	int dayOfFirst = 6 - (((currTime->tm_mday) - (currTime->tm_wday) - 2) % 7);//매월의 1일의 요일확인하기
	int leapYearIndicator = checkLeapYear(yearToday);//윤년확인



	switch (leapYearIndicator)
	{
	case 0://평년이면 인쇄할 날짜의 수를 days[0]에서 넘김
		PrintCal(yearToday, monthToday, days[0][monthToday - 1], dayToday, dayOfFirst);
		break;
	case 1://윤년이면 인쇄할 날짜의 수를 days[1]에서 넘김
		PrintCal(yearToday, monthToday, days[1][monthToday - 1], dayToday, dayOfFirst);
		break;
	default:
		break;
	}

	return 0;
}

int checkLeapYear(int year)
{
	if ((year % 400) == 0)
	{
		return 1;
	}
	else if ((year % 100) == 0)
	{
		return 0;
	}
	else if ((year & 4) == 0)
	{
		return 1;
	}
	else
	{
		return 0;
	}
}


void PrintCal(int yearToday, int monthToday, int days, int dayToday, int dayOfFirst)
{
	char* str[7] = { "SUN", "MON","TUE","WED","THU","FRI","SAT" };

	do
	{
		//system("cls");

		printf("\t----------------------------------------------------\n");
		printf("  \t\t\t     %d년 %d월\n", yearToday, monthToday);
		printf("\t----------------------------------------------------\n");
		printf(" \tSUN \tMON \tTUE \tWED \tTHU \tFRI \tSAT \n");
		printf("\t----------------------------------------------------\n");

		for (int i = 0; i < dayOfFirst; i++)
			printf("\t");

		int counter = dayOfFirst + 1;
		int dayCounter = 1;

		while (counter < (days + dayOfFirst + 1))
		{
			if (dayCounter < 10 && dayCounter == dayToday)
			{
				printf("\t%d*", dayCounter++);
			}
			else if (dayCounter >= 10 && dayCounter == dayToday)
			{
				printf("\t%d*", dayCounter++);
			}
			else if (dayCounter < 10 && dayCounter != dayToday)
			{
				printf("\t %d", dayCounter++);
			}
			else
			{
				printf("\t%d", dayCounter++);
			}
			if ((counter % 7) == 0)
				printf("\n");
			counter++;
		}
		printf("\n");
		printf("\t----------------------------------------------------\n");
	} while (0);//무한루프로 바꾸려면 1을 넣으세요
}*/