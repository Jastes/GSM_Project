#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>//bool
#include <Windows.h>


//캐릭터,몬스터,골드의 위치를 저장할 구조체
typedef struct _CHARACTER{
	int pos_x;
	int pos_y;
}CHARACTER;

//게임시작시 각 캐릭터의 위치를 랜덤으로 발생
void RandomPos(CHARACTER* target){
	target->pos_x = 0 + rand() % 10;
	target->pos_y = 0 + rand() % 10;
}

//캐릭터를 무작위로 움직임
void RandomMove(CHARACTER* target){
	int direction = 0 + rand() % 4;
	switch (direction)
	{
	case 0:
		target->pos_x++;
		break;
	case 1:
		target->pos_x--;
		break;
	case 2:
		target->pos_y++;
		break;
	case 3:
		target->pos_y--;
		break;
	default:
		break;
	}
}

//캐릭터가 필드를 벋어나는지 확인해서 벋어나면 정지
void CheckBorder(CHARACTER* target){
	if (target->pos_x > 9)
	{
		target->pos_x--;
	}
	else if (target->pos_x < 0)
	{
		target->pos_x++;
	}
	else if (target->pos_y > 9)
	{
		target->pos_y--;
	}
	else if (target->pos_y < 0)
	{
		target->pos_y++;
	}
}

//매개변수로 구조체를 받아서 케릭터의 위치 설정
void SetPosition(CHARACTER sharp, CHARACTER mon1, CHARACTER mon2, CHARACTER gold, char map[][10]){
	for (int i = 0; i < 10; i++)
	{
		for (int j = 0; j < 10; j++)
		{
			map[i][j] = '.';
		}
	}
	map[mon1.pos_y][mon1.pos_x] = 'M';
	map[mon2.pos_y][mon2.pos_x] = 'M';
	map[gold.pos_y][gold.pos_x] = 'G';
	map[sharp.pos_y][sharp.pos_x] = '#';
}

//매개변수로 받은 두개의 구조체가 같은지 비교 같으면 true, 다르면 false를 리턴
bool CheckPos(CHARACTER target1, CHARACTER target2){
	if (target1.pos_x == target2.pos_x && target1.pos_y == target2.pos_y)
		return true;

	return false;
}

//커서를 움직임 
void gotoxy(int x, int y){
	COORD Pos;
	Pos.X = x;
	Pos.Y = y;
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), Pos);
}

//화면출력
void PrintScreen(char map[][10], int score){
	system("cls");
	gotoxy(30, 5);
	printf("현재점수 : %d\n", score);
	for (int i = 0; i < 10; i++)
	{
		gotoxy(30, 6 + i);
		for (int j = 0; j < 10; j++)
		{

			printf("%c", map[i][j]);
		}
	}

}
int main()
{
	//캐릭터 변수 선언
	CHARACTER sharp = { .pos_x = 0, .pos_y = 0 };
	CHARACTER mon1 = { .pos_x = 0, .pos_y = 0 };
	CHARACTER mon2 = { .pos_x = 0, .pos_y = 0 };
	CHARACTER gold = { .pos_x = 0, .pos_y = 0 };
	char map[10][10] = { 0, };
	int score = 0;

	//캐릭터 변수 포인터 및 할당
	CHARACTER* sharpPTR = &sharp;
	CHARACTER* mon1PTR = &mon1;
	CHARACTER* mon2PTR = &mon2;
	CHARACTER* goldPTR = &gold;

	//케릭터, 몬스터, 골드의 위치를 랜덤으로 생성
	srand((unsigned int)time(NULL));

	RandomPos(sharpPTR);
	do
	{
		RandomPos(mon1PTR);
	} while (CheckPos(sharp, mon1));

	do
	{
		RandomPos(mon2PTR);
	} while (CheckPos(sharp, mon2) || CheckPos(mon1, mon2));

	do
	{
		RandomPos(goldPTR);
	} while (CheckPos(sharp, gold) || CheckPos(mon1, gold) || CheckPos(mon2, gold));


	//초기 위치 설정
	SetPosition(sharp, mon1, mon2, gold, map);
	PrintScreen(map, 0);

	char key = '0';

	do
	{

		//사용자 케릭터의 움직임 설정
		key = getch();
		if (key == 'a' || key == 'A')
		{
			sharp.pos_x -= 1;
		}
		else if (key == 'd' || key == 'D')
		{
			sharp.pos_x += 1;
		}
		else if (key == 'w' || key == 'W')
		{
			sharp.pos_y -= 1;
		}
		else if (key == 's' || key == 'S')
		{
			sharp.pos_y += 1;
		}
		else if (key == 'q' || key == 'Q')
		{
			return 0;
		}
		//몬스터를 무작위로 1칸씩 움직이도록 함
		RandomMove(mon1PTR);
		RandomMove(mon2PTR);

		//몬스터나 플레이어가 맵의 경계를 벋어나지 않도록 확인
		CheckBorder(sharpPTR);
		CheckBorder(mon1PTR);
		CheckBorder(mon2PTR);

		//위치지정
		SetPosition(sharp, mon1, mon2, gold, map);

		//화면출력

		PrintScreen(map, score);



		//몬스터와 부딛히거나 골드를 획득하면 종료
		if (CheckPos(sharp, mon1) || CheckPos(sharp, mon2))
		{
			gotoxy(16, 17);
			printf("몬스터와 부딛혔습니다. - 10점 계속 진행하려면 아무키나 누르세요.");
			score -= 10;
			RandomPos(sharpPTR);
			do
			{
				RandomPos(mon1PTR);
			} while (CheckPos(sharp, mon1));

			do
			{
				RandomPos(mon2PTR);
			} while (CheckPos(sharp, mon2) || CheckPos(mon1, mon2));

			do
			{
				RandomPos(goldPTR);
			} while (CheckPos(sharp, gold) || CheckPos(mon1, gold) || CheckPos(mon2, gold));


		}
		else if (CheckPos(sharp, gold))
		{
			gotoxy(16, 17);
			printf("골드를 찾았습니다. + 10점:)계속 진행하려면 아무키나 누르세요.");
			score += 10;
			RandomPos(sharpPTR);
			do
			{
				RandomPos(mon1PTR);
			} while (CheckPos(sharp, mon1));

			do
			{
				RandomPos(mon2PTR);
			} while (CheckPos(sharp, mon2) || CheckPos(mon1, mon2));

			do
			{
				RandomPos(goldPTR);
			} while (CheckPos(sharp, gold) || CheckPos(mon1, gold) || CheckPos(mon2, gold));

		}

	} while (1);
	return 0;
}