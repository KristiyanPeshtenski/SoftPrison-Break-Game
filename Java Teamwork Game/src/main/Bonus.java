package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Bonus {
	int x;
	int y;
	int bonusDuration;
	int bonusType;
	Image bonusImage;
	
	Random randGenerator;
	
	public Bonus(int x, int y) {
		randGenerator = new Random();
		this.x = x;
		this.y = y;
		
		bonusType = randGenerator.nextInt(101);
		bonusDuration = 90;
		loadBonusImage();
	}
	
	public void paint(Graphics g) {
		g.drawImage(bonusImage, x, y, null);
	}
	
	public void tick() {
		bonusDuration--;
		
		if (bonusDuration < 0) {
			Player.bonuses.remove(this);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle (this.x,this.y, bonusImage.getWidth(null), bonusImage.getHeight(null));
	}

	public void getBonus() {
		if (bonusType <= 10) {
			GamePanel.player.lives++;
		} else if (bonusType <= 80) {
			GamePanel.score += 100;
		} else {
			Enemy.enemySpeed -= 1;
		}
	}
	
	public void loadBonusImage() {
		ImageIcon ii;
		
		if (bonusType <= 10) {
			ii = new ImageIcon("res/heart.png");
		} else if (bonusType <= 80) {
			ii = new ImageIcon("res/money.png");
		} else {
			ii = new ImageIcon("res/slow.png");
		}
		
		bonusImage = ii.getImage();
	}
}
