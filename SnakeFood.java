package snack_game;
import java.awt.*;

public class SnakeFood extends GameRectangle {
    private final Color color;

    public SnakeFood(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }
    @Override
    public void paint(Graphics g){
        g.setColor(this.color);
        g.fillOval(this.x, this.y, GENERAL_COMPONENT_SIZE, GENERAL_COMPONENT_SIZE);

    }

}
