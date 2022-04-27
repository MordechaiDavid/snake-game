package snack_game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static snack_game.GameRectangle.GENERAL_SIZE;

public class GameScene extends JPanel {
    private int width;
    private int height;
    private Snake playerSnack;
    private SnakeFood playerFood;
    private boolean isRun=true;


    public GameScene(int x, int y, int width, int height){
        this.width = width;
        this.height = height;
        this.setBounds(x, y, width, height);
        this.setBackground(Color.black);
        playerSnack = new Snake(width, height, 6, Color.RED, Color.GREEN);
        Random random = new Random();
        SnakeFood test = null;
        do {
            test = new SnakeFood(random.nextInt(width-GENERAL_SIZE), random.nextInt(height-GENERAL_SIZE), Color.ORANGE);
        }while (playerSnack.collisionWithFood(test));
        playerFood = test;
        this.gameLoop();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.playerSnack.paint(g);
        this.playerFood.paint(g);
        this.gameOver(g);

    }


    public void gameOver(Graphics g){
        if(playerSnack.isTouchItself()){
            isRun=false;
            g.setColor(Color.RED);
            Font font= new Font("Kristen ITC", Font.BOLD, 59);
            g.setFont(font);
            g.drawString("Game Over", width/2-160, height/2);

        }
    }

    public void gameLoop(){
        new Thread( ()-> {
            SnakeListener playerMovementSnack = new SnakeListener(this.playerSnack);
            this.addKeyListener(playerMovementSnack);
            this.setFocusable(true);
            this.requestFocus();
            while (true){
                try {
                    if (isRun) {
                        this.playerSnack.checkFood(this.playerFood, this.width, this.height);
                        this.playerSnack.move();
                        this.playerSnack.reachBorder(width, height);
                    }
                    Thread.sleep(90);
                    repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



}




