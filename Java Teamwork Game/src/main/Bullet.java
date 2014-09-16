package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Bullet {

	private int x;
	private int y;
	private int size = 10;
	private int speed = 11;
	static Image ammoImage;
	
	public Bullet(int x, int y){
		this.x = x;
		this.y = y;
		
		ammoImage();
	}
	
	public void tick(){
		x += speed;
	}
	
	public void paint (Graphics g){
		g.setColor(Color.red);
		g.drawImage(ammoImage, x, y, null);
	} 
	
	public Rectangle getBounds(){
		return new Rectangle (this.x,this.y, ammoImage.getWidth(null), ammoImage.getHeight(null));
	}
	
	private void ammoImage(){
		
		ImageIcon ii = new ImageIcon("res/bullet.png");
		ammoImage = ii.getImage();
		
	}
	
	
}
