package snack_game;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeListener implements KeyListener {

    Snake playerSnack;

    public SnakeListener(Snake playerSnack) {
        this.playerSnack = playerSnack;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.playerSnack.setDirection(e.getKeyCode());
        }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
