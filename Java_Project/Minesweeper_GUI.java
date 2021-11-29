package GSM.Java_Project;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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

//                String temp;
//                temp = Integer.toString(i*10 + j);
                /*

                MineButton extend JButton
                count
                설정파일 내에다가 getTile(x, y) (x,y) 에 있는 타일의 값 (0~8 이면 근처 지뢰개수, 9면 지뢰)
                얘를 count 에 대입
                button[i][j] = new MineButton(getTile(x, y));

                 */
                //button[i][j] = new MineButton(getTile(x, y));

                //check resource by click in the button
                AtomicReference<JButton> value = new AtomicReference<>(button[i][j]);   //lambda.. umm..
                AtomicInteger iValue = new AtomicInteger(i);
                AtomicInteger jValue = new AtomicInteger(j);

                button[i][j] = new JButton();
                Mine_level_chose game = new Mine_level_chose();
                int finalI = i;
                int finalJ = j;
                button[i][j].addActionListener(e -> { //lambda execute by click button
                        showMineCount(iValue.get(), jValue.get(), game.getMineCount(finalI, finalJ));
                        //button.getCount() button.count
                        //if (count == 9) Boom(i, j);
                        //else 버튼 텍스트 전환

                        /*
                        showNear(x, y)
                        fun showNear(int x, int y) -> showNear(x-1~x+1, y-1~y+1//맵가장자리면 안하고, (x, y)가 지뢰일떄도 안하고) 숫자보여주기(x, y)
                         */
                });
                add(button[i][j]);
            }
        }
    }

    //choose level JMenu_bar event
    public int leve_chose(){
        file_diff_easy.addActionListener(e -> {
            mineSet = new Minesweeper_Setting();
            mine = new int[getWidth()][getHeight()];
        });
        file_diff_normal.addActionListener(e -> {Mine_level_chose(2)});
        file_diff_hard.addActionListener(e -> {Mine_level_chose(3)});
        file_diff_Custom.addActionListener(e -> {Mine_level_chose(4)});
    }

    //show Mine_Counter
    public void showMineCount(int i, int j, int mineCount) {
        button[i][j].setText(mineCount + "");
    }
}
