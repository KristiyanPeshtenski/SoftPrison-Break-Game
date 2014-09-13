import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class GamePanel extends JPanel {

	static int x = 10;
	static Player player;
	
	public GamePanel() {
		player = new Player();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		player.paint(g);
		
	}
	
	public static void tick() {
		x += 5;
		
		player.tick();
		//loadImage();
		if (x > GameFrame.WIDTH) {
			x = 10;
		}
	}
	
	/*private static void loadImage() {

        ImageIcon ii = new ImageIcon("res/joker.png");
        joker = ii.getImage();
    }*/
}
