package GSM.Java_Project;

public class Minesweeper_Setting {
    private int width;
    private int height;
    private int Mine_Cnt = 10;

    public Minesweeper_Setting(int width, int height, int mine_Cnt) {
        this.width = width;
        this.height = height;
        this.Mine_Cnt = mine_Cnt;
    }

    public Minesweeper_Setting(){
        this(8,8,10);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMine_Cnt() {
        return Mine_Cnt;
    }

    public void setMine_Cnt(int mine_Cnt) {
        Mine_Cnt = mine_Cnt;
    }


}
