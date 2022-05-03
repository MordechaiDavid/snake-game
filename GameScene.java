package snack_game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static snack_game.GameRectangle.GENERAL_COMPONENT_SIZE;

public class GameScene extends JPanel {
    private final int screenWidth;
    private final int screenHeight;
    private final Snake snackPlayer;
    private SnakeFood foodPlayer;
    private boolean isRun=true;


    public GameScene(int x, int y, int screenWidth, int screenHeight){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setBounds(x, y, screenWidth, screenHeight);
        this.setBackground(Color.DARK_GRAY);
        snackPlayer = new Snake(this.screenWidth, this.screenHeight , 6, Color.RED, Color.GREEN);
        Random random = new Random();
        SnakeFood test = null;
        do {
            test = new SnakeFood(random.nextInt(screenWidth- GENERAL_COMPONENT_SIZE), random.nextInt(screenHeight- GENERAL_COMPONENT_SIZE), Color.ORANGE);
        }while (snackPlayer.collisionWithFood(test));
        this.foodPlayer = test;
        this.gameLoop();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.snackPlayer.paint(g);
        this.foodPlayer.paint(g);
        this.drawScore(g);
        this.gameOver(g);

    }

    public void drawScore(Graphics g){
        g.setColor(Color.red);
        Font font = new Font("Kristen ITC", Font.ITALIC, 30);
        g.setFont(font);
        FontMetrics fontMetrics = getFontMetrics(g.getFont());
        int scoreSize = fontMetrics.stringWidth("Score");
        g.drawString("Score:"+this.snackPlayer.getScore(), (this.screenWidth - scoreSize)/2, 30);
    }


    public void gameOver(Graphics g){
        // Ensure that snack position is not on the start point of the game that all (x,y) snake pieces coordinates are 0 by default and touch each other.
        if (!(GameRectangle.isInSamePosition(this.snackPlayer.getSnakeArr(),this.snackPlayer.getSnakeUnits(), 0, 0))){
                if(snackPlayer.isTouchItself()) {
                this.isRun = false;
                // paint game over message
                g.setColor(Color.RED);
                Font font = new Font("Kristen ITC", Font.BOLD, 65);
                g.setFont(font);
                FontMetrics fontMetrics = getFontMetrics(g.getFont());
                int gameOverSize = fontMetrics.stringWidth("Game Over");
                g.drawString("Game Over", (screenWidth - gameOverSize)/2, screenHeight / 2);
                // paint final score
                g.setColor(Color.orange);
                g.setFont(new Font("kristen ITC", Font.ITALIC, 30));
                fontMetrics = getFontMetrics(g.getFont());
                int finalScoreSize = fontMetrics.stringWidth("Your score: "+ this.snackPlayer.getScore());
                g.drawString("Your Score: "+this.snackPlayer.getScore(),(this.screenWidth - finalScoreSize)/2 , this.screenHeight /2 +40);

          }
        }
     }

    public void gameLoop(){
        new Thread( ()-> {
            SnakeListener playerMovementSnack = new SnakeListener(this.snackPlayer);
            this.addKeyListener(playerMovementSnack);
            this.setFocusable(true);
            this.requestFocus();
            while (true){
                try {
                    if (isRun) {
                        this.snackPlayer.checkFood(this.foodPlayer, this.snackPlayer, this.screenWidth, this.screenHeight);
                        this.snackPlayer.move();
                        this.snackPlayer.reachBorder(screenWidth, screenHeight);
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




