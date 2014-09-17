package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class InputHandler implements KeyListener {

	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub

		GamePanel.player.keyPressed(key);
		
	}

	@Override
	public void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub

		GamePanel.player.keyReleased(key);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
