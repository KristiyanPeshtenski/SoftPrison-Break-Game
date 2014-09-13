import java.awt.Color;
import java.awt.Graphics;


public class Player {
	static int x;
	static int y;
	static int size;
	
	public Player() {
		x = 40;
		y = 40;
		size = 30;
	}
	
	public void tick() {
		x += 1;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, size, size);
	}
}
