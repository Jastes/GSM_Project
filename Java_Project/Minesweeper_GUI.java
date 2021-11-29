package GSM.Java_Project;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static GSM.Java_Project.Mine_level_chose.mine;

public class Minesweeper_GUI extends JFrame {
//J_MenuBar setup
    JMenuBar J_Menu = new JMenuBar();
    JMenu file = new JMenu("file");
    JMenu sub_file_first = new JMenu("new Game!");
//J_menuBar level
    JMenu file_diff = new JMenu("level");
    JMenu file_diff_easy = new JMenu("Beginner");
    JMenu file_diff_normal = new JMenu("Intermediate");
    JMenu file_diff_hard = new JMenu("Advanced");
    JMenu file_diff_Custom = new JMenu("Customized");
//Creator
    JMenu file_Creator = new JMenu("Creator");
    JMenu file_help = new JMenu("Help?");
//Escape
    JMenu sub_file02_end = new JMenu("exit");
    private JButton button[][];

    Mine_level_chose game = new Mine_level_chose();

    public Minesweeper_GUI() throws HeadlessException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("!!Minesweeper!!");
        setSize(50*8, 50*8);
        setVisible(true);

        // JMenuBar setup
        setJMenuBar(J_Menu);
        J_Menu.add(file);
        file.add(sub_file_first);

        //level
        sub_file_first.add(file_diff);
        file_diff.add(file_diff_easy);
        file_diff.add(file_diff_normal);
        file_diff.add(file_diff_hard);
        file_diff.add(file_diff_Custom);

        //Creator
        file.add(file_help);
        file.add(file_Creator);

        //Escape
        file.add(sub_file02_end);

        // Layout default
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(8,8);
        setLayout(grid);
        button =  new JButton[8][8];

        //choose level JMenu_bar event



        for(int i = 0 ; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                AtomicReference<JButton> value = new AtomicReference<>(button[i][j]);   //lambda.. umm..
                AtomicInteger iValue = new AtomicInteger(i);
                AtomicInteger jValue = new AtomicInteger(j);

                button[i][j] = new JButton();

                button[i][j].addActionListener(e -> { //클릭 이벤트 부분
                    serch(iValue.get(), jValue.get());
                });




                add(button[i][j]);
            }
        }
    }



    public void serch(int i, int j){
        int count =0;//비교를 위한 변수
        if(game.getMineCount(i,j) == 9){//지뢰 체크
            showMineCount(i,j);
        }else {
            for (int ij = -1; ij < 2; ij++) {//주위 돌리는 문1
                for (int ji = -1; ji < 2; ji++) {//주위 돌리는 문1
                    try {//테두리 미출력 오류 해결
                        count = 0;
                        if (ji == 0 && ij == 0) {//제자리 출력
                            showMineCount(i + ij, j + ji, game.getMineCount(i, j));
                        } else {//제자리 이외문
                            for (int ij2 = -1; ij2 < 2; ij2++) {//숫자 클릭을 위한 문1
                                for (int ji2 = -1; ji2 < 2; ji2++) {//숫자 클릭을 위한 문2
                                    if (mine[i + ij2][j + ji2] == 10) {//숫자 비교를 비교하기 위한 문
                                        count++;
                                    }
                                }
                            }
                            if (game.getMineCount(i, j) == 0 || game.getMineCount(i, j) != 0 && game.getMineCount(i, j)
                                    == count) {//주변 출력
                                try {
                                    if (game.getMineCount(i + ij, j + ji) == 0) {//0 출력
                                        showMineCount(i + ij, j + ji, game.getMineCount(i + ij, j + ji));
                                    } else if (game.getMineCount(i + ij, j + ji) != 0) {//0 이외 출력
                                        if (game.getMineCount(i + ij, j + ji) != 9) {//9이외 출력
                                            showMineCount(i + ij, j + ji, game.getMineCount(i + ij, j + ji));
                                        }
                                    }
                                } catch (Exception e) {
                                }
                            }
                        }
                    }catch (Exception e){}
                }
            }
        }
    }
    public void showMineCount(int i, int j) {//지뢰위치 표시용
        button[i][j].setText("체크");
        mine[i][j] +=1;
    }
    public void showMineCount(int i, int j, int mineCount) {//기본 출력
        button[i][j].setText(mineCount + "");
        if(mineCount == 10){
            button[i][j].setText("체크");
        }
    }
}
