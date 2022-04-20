package snack_game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameScene extends JPanel {
    private int width;
    private int height;
    private Snake playerSnack;
    private SnakeFood playerFood;

    public GameScene(int x, int y, int width, int height){
        this.width = width;
        this.height = height;
        this.setBounds(x, y, width, height);
        this.setBackground(Color.black);
        playerSnack = new Snake(width, height, 6, Color.RED, Color.GREEN);
        Random random = new Random();
        SnakeFood test = null;
        do {
            test = new SnakeFood(random.nextInt(width), random.nextInt(height), Color.ORANGE);
        }while (playerSnack.CollisionWithFood(test));
        playerFood = test;
        this.gameLoop();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.paintScore(g);
        g.setColor(Color.black);
        this.playerSnack.paint(g);
        this.playerFood.paint(g);

    }

    public void gameLoop(){
        new Thread( ()-> {
            SnakeListener playerMovementSnack = new SnakeListener(this.playerSnack);
            this.addKeyListener(playerMovementSnack);
            this.setFocusable(true);
            this.requestFocus();
            while (true){
                try {
                    this.playerSnack.checkFood(this.playerFood, this.width, this.height);
                    this.playerSnack.move();
                    Thread.sleep(70);
                    repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void paintScore(Graphics g){
        g.drawChars(new char[]{'H', 'I'} , 0, 2, 100, 20);
    }
}




