package snack_game;

import javax.swing.*;

public class Main extends JFrame {
    public final static int WINDOW_WIDTH=550;
    public final static int WINDOW_HEIGHT=550;

    public Main(){
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        GameScene gameScene = new GameScene(0, 0, this.getContentPane().getWidth(), this.getContentPane().getHeight());
        this.add(gameScene);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

    }

    public static void main(String[] args) {
        Main snake = new Main();
    }
}
