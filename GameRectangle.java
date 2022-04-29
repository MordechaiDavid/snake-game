package snack_game;
import java.awt.*;

public class GameRectangle {

    protected int x;
    protected int y;
    public final static int GENERAL_SIZE=23;


    public GameRectangle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics graphics){
        graphics.fillRect(this.x, this.y, GENERAL_SIZE, GENERAL_SIZE);
    }

    public boolean isCollision( GameRectangle other){
        boolean collision = false;
        Rectangle thisRactangle = new Rectangle(this.x, this.y, GENERAL_SIZE, GENERAL_SIZE);
        Rectangle otherRactangle = new Rectangle(other.x, other.y, GENERAL_SIZE, GENERAL_SIZE);
        if( thisRactangle.intersects(otherRactangle))
            collision=true;

        return collision;
    }

    public static boolean isInSamePosition(GameRectangle[] arr, int length, int x, int y){
        boolean ans=true;
        for (int i = 0; i < length; i++) {
            if (arr[i].x!=x  && arr[i].y!=y){
                ans=false;
            }
        }
        return ans;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }



}
