package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Ammo {

	private int x;
	private int y;
	private int size = 10;
	private int speed = 11;
	
	public Ammo(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void tick(){
		x += speed;
	}
	
	public void paint (Graphics g){
		g.setColor(Color.red);
		g.fillRect(x, y, size, size);
	} 
	
	public Rectangle getBounds(){
		return new Rectangle (this.x,this.y,this.size,this.size);
	}
	
	
}
