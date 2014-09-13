import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Enemy {
	private int x;
	private int y;
	private int size = 30;
	private int fallingSpeed = 2;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		y += fallingSpeed;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, size, size);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.size, this.size);
	}
}
