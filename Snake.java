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

    public Snake(int screenWidth, int screenHeight, int snakeUnits, Color headColor, Color bodyColor) {
        snakeArr = new GameRectangle[(screenHeight*screenHeight)/ GENERAL_SIZE];
        this.snakeUnits= snakeUnits;
        for (int i = 0; i < this.snakeUnits ; i++) {
            this.snakeArr[i] = new GameRectangle(0, 0);
        }
        this.headColor = headColor;
        this.bodyColor = bodyColor;
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

    public boolean CollisionWithFood(GameRectangle other){
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


    public void checkFood(SnakeFood playerFood, int screenWidth, int screenHeight){
        Random random = new Random();
        if (this.snakeArr[0].isCollision(playerFood)) {
            playerFood.x = random.nextInt(screenWidth- GENERAL_SIZE);
            playerFood.y = random.nextInt(screenHeight- GENERAL_SIZE);
            this.snakeArr[snakeUnits]= new GameRectangle(0,0);
            this.snakeUnits++;
        }
    }

    public void gameOver(){

    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
