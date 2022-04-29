package snack_game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import static snack_game.GameRectangle.GENERAL_SIZE;

public class Snake {
    private GameRectangle[] snakeArr;
    private int snakeUnits;
    private Color headColor;
    private Color bodyColor;
    private int direction= KeyEvent.VK_RIGHT;
    private int score;

    public Snake(int screenWidth, int screenHeight, int snakeUnits, Color headColor, Color bodyColor) {
        snakeArr = new GameRectangle[(screenHeight*screenHeight)/ GENERAL_SIZE];
        this.snakeUnits= snakeUnits;
        for (int i = 0; i < this.snakeUnits ; i++) {
            this.snakeArr[i] = new GameRectangle(0, 0);
        }
        this.headColor = headColor;
        this.bodyColor = bodyColor;
        this.score=0;
    }

    public void paint(Graphics g){
        for (int i = 0; i < snakeUnits; i++) {
            if(i==0){
                g.setColor(headColor);
                snakeArr[i].paint(g);
            }
            else {
                g.setColor(bodyColor);
                snakeArr[i].paint(g);
            }
        }
    }

    public void move(){
        for (int i = snakeUnits-1; i >0; i--) {
            snakeArr[i].x = snakeArr[i-1].x;
            snakeArr[i].y = snakeArr[i-1].y;
        }
        switch (direction){
            case KeyEvent.VK_RIGHT:
                this.snakeArr[0].x+= GENERAL_SIZE;
                break;

            case KeyEvent.VK_LEFT:
                this.snakeArr[0].x-= GENERAL_SIZE;
                break;

            case KeyEvent.VK_UP:
                this.snakeArr[0].y-= GENERAL_SIZE;
                break;

            case KeyEvent.VK_DOWN:
                this.snakeArr[0].y+= GENERAL_SIZE;
                break;
        }
    }

    public boolean collisionWithFood(GameRectangle other){
        for (int i = 0; i < snakeUnits; i++) {
            if(snakeArr[i].isCollision(other))
                return true;
        }
        return false;
    }

    public void reachBorder(int screenWidth, int screenHeight){
        if (snakeArr[0].x > screenWidth )
            snakeArr[0].x = 0;

        if (snakeArr[0].x < 0 )
            snakeArr[0].x= screenWidth;

        if (snakeArr[0].y < 0 )
            snakeArr[0].y= screenHeight;

        if (snakeArr[0].y > screenHeight )
            snakeArr[0].y= 0;
    }

    public boolean isTouchItself(){
        for (int i = 1; i < snakeUnits; i++) {
            if (snakeArr[0].isCollision(snakeArr[i]))
                return true;
        }
        return false;
    }


    public void checkFood(SnakeFood playerFood, int screenWidth, int screenHeight){
        Random random = new Random();
        if (this.snakeArr[0].isCollision(playerFood)) {
            // generate another food.
            playerFood.x = random.nextInt(screenWidth- GENERAL_SIZE);
            playerFood.y = random.nextInt(screenHeight- GENERAL_SIZE);
            // increase the snack body.
            this.snakeArr[snakeUnits]= new GameRectangle(0,0);
            this.snakeUnits++;
            // increase the score
            this.score++;
        }
    }


    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public GameRectangle[] getSnakeArr() {
        return snakeArr;
    }

    public int getSnakeUnits() {
        return snakeUnits;
    }

    public int getScore() {
        return score;
    }
}
