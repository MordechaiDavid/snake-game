package snack_game;

import javax.swing.*;

public class Main extends JFrame {
    public final static int WINDOW_WIDTH=500;
    public final static int WINDOW_HEIGHT=500;

    public Main(){
        this.setVisible(true);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        GameScene gameScene = new GameScene(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.add(gameScene);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

    }

    public static void main(String[] args) {
        Main snack = new Main();
    }
}
