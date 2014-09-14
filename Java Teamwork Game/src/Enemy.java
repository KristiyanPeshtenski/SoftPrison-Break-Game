import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Enemy {
	private int x;
	private int y;
	private static int size = 30;
	private int fallingSpeed = 2;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		x -= fallingSpeed;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, size, size);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, size, size);
	}
	
	public static int getSize() {
		return size;
	}
	
	public int getX() {
		return x;
	}
}
