import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Missiles {

	private int x;
	private int y;
	private int size = 10;
	private int speed = 1;
	
	public Missiles(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void tick(){
		x += 1;
	}
	
	public void paint (Graphics g){
		g.setColor(Color.red);
		g.fillRect(x, y, size, size);
	} 
	
	public Rectangle getBound(){
		return new Rectangle (this.x,this.y,this.size,this.size);
	}
	
	
}
