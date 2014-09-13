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
	
	public static void tick() {
		x += 1;
	}
	
	public static void paint(Graphics g) {
		g.fillRect(x, y, size, size);
	}
}
