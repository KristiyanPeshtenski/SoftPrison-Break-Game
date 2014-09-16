package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Enemy {
	private int x;
	private int y;
	private static int size = 30;
	static double enemySpeed = 6;
	static Image enemyImage;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		
		enemyImage();
	}
	
	public void tick() {
		x -= (int)enemySpeed;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawImage(enemyImage, x, y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, enemyImage.getWidth(null), enemyImage.getHeight(null));
	}
	
	public static int getSize() {
		return size;
	}
	
	public int getX() {
		return x;
	}
	
	private void enemyImage(){
		
		ImageIcon ii = new ImageIcon("res/enemy.png");
		enemyImage = ii.getImage();
		
	}
}
