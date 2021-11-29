package GSM.Java_Project;

import javafx.concurrent.Worker;

import javax.swing.*;

class Mine_level_chose extends Minesweeper_Setting{
    int level_chose_num;
    Minesweeper_Setting mineSet;
    static int[][] mine;

    public Mine_level_chose() {
        this(1);
    }

    public Mine_level_chose(int level_chose_num) {
        this.level_chose_num = level_chose_num;
        start();
    }

    public void start() {
        chose_mine();
        setMine();
        check_Mine();
    }

    public void chose_mine() {//크기생성
        switch (level_chose_num) {
            case 1:
                mineSet = new Minesweeper_Setting();
                mine = new int[getWidth()][getHeight()];
                break;
            case 2:
                mineSet = new Minesweeper_Setting(16,16,40);
                mine = new int[getWidth()][getHeight()];
                break;
            case 3:
                mineSet = new Minesweeper_Setting(30,16,99);
                mine = new int[getWidth()][getHeight()];
                break;
            case 4:
                throw new IllegalStateException("We are not success with code sorry");
            default:
                throw new IllegalStateException("Unexpected value: " + level_chose_num);
        }
    }

    public void setMine(){
        int number = mineSet.getMine_Cnt();
        int x = 0, y = 0;

        //Mine random location
        System.out.println("number is : " + number);
        do{
            System.out.println(x+", "+y);
            x = (int)(Math.random()* mineSet.getWidth());
            y = (int)(Math.random()* mineSet.getHeight());

            if(mine[x][y] == 0){
                mine[x][y] = 9;
                number--;
            }
        }while (number > 0);
    }

    protected void check_Mine() { //put num of adjacent mine at map tile
        for (int i = 0; i < mineSet.getWidth(); i++) {
            for (int j = 0; j < mineSet.getHeight(); j++) {
                if (mine[i][j] == 9)  //mine pass
                    continue;

                int mine_adjacent_cnt = 0;

                //iterate tile at 3x3
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        //number of the adjacent mine cnt
                        if (!((i == 0 && x == -1) ||
                                (i == mineSet.getWidth() - 1 && x == 1) ||
                                (j == 0 && y == -1) ||
                                (j == mineSet.getHeight() - 1 && y == 1))
                            && mine[i + x][j + y] == 9) mine_adjacent_cnt++;
                    }
                }
                mine[i][j] = mine_adjacent_cnt;
            }
        }
    }

    public int getMineCount(int i, int j) {
        return mine[i][j];
    }
}

public class Minesweeper_level_SettingTest {
    private Minesweeper_Setting mineSet;

    public static void main(String[] args) {
        Mine_level_chose game = new Mine_level_chose();

        for (int i = 0; i < (game).mineSet.getWidth(); i++) {
            for (int j = 0; j < (game).mineSet.getHeight(); j++) {
                System.out.printf("%d ", game.mine[i][j]);
            }
            System.out.println();
        }
    }
}
