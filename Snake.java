package snack_game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Snake {
    private GameRectangle[] SnakeArr;
    private int snakeUnits;
    private Color headColor;
    private Color bodyColor;
    private int direction= KeyEvent.VK_RIGHT;

    public Snake(int screenWidth, int screenHeight, int snakeUnits, Color headColor, Color bodyColor) {
        SnakeArr = new GameRectangle[(screenHeight*screenHeight)/ GameRectangle.GENERAL_SIZE];
        this.snakeUnits= snakeUnits;
        for (int i = 0; i < this.snakeUnits ; i++) {
            this.SnakeArr[i] = new GameRectangle(0, 0);
        }
        this.headColor = headColor;
        this.bodyColor = bodyColor;
    }

    public void paint(Graphics g){
        for (int i = 0; i < snakeUnits; i++) {
            if(i==0){
                g.setColor(headColor);
                SnakeArr[i].paint(g);
            }
            else {
                g.setColor(bodyColor);
                SnakeArr[i].paint(g);
            }
        }
    }

    public void move(){
        for (int i = snakeUnits-1; i >0; i--) {
            SnakeArr[i].x = SnakeArr[i-1].x;
            SnakeArr[i].y = SnakeArr[i-1].y;
        }
        switch (direction){
            case KeyEvent.VK_RIGHT:
                this.SnakeArr[0].x+= GameRectangle.GENERAL_SIZE;
                break;

            case KeyEvent.VK_LEFT:
                this.SnakeArr[0].x-= GameRectangle.GENERAL_SIZE;
                break;

            case KeyEvent.VK_UP:
                this.SnakeArr[0].y-= GameRectangle.GENERAL_SIZE;
                break;

            case KeyEvent.VK_DOWN:
                this.SnakeArr[0].y+= GameRectangle.GENERAL_SIZE;
                break;
        }
    }

    public boolean CollisionWithFood(GameRectangle other){
        for (int i = 0; i < snakeUnits; i++) {
            if(SnakeArr[i].isCollision(other))
                return true;
        }
        return false;
    }


    public void checkFood(SnakeFood playerFood, int screenWidth, int screenHeight){
        Random random = new Random();
        if (this.SnakeArr[0].isCollision(playerFood)) {
            playerFood.x = random.nextInt((int)(screenWidth/ GameRectangle.GENERAL_SIZE))* GameRectangle.GENERAL_SIZE;
            playerFood.y = random.nextInt((int)(screenHeight/ GameRectangle.GENERAL_SIZE))* GameRectangle.GENERAL_SIZE;
            this.SnakeArr[snakeUnits]= new GameRectangle(0,0);
            this.snakeUnits++;
        }
    }

    public void gameOver(){

    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
