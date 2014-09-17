package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;


public class Player {
	int x;
	int y;
	int velX = 0;
	int velY = 0;
	int shootDelay = 0;
	Random randGenerator;
	
	public int lives;
	int speed = 14;
	Image characterImage;
	
	Ammunition ammo;
	public static ArrayList<Bonus> bonuses;
	
	public Player() {
		x = 15;
		y = 250;
		
		lives = 5;
		randGenerator = new Random();
		
		ammo = new Ammunition();
		bonuses = new ArrayList<>();
		loadCharacterImage();
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		ammo.tick();
		
		if (shootDelay > 0) {
			shootDelay -= 1;
		}
		
		for (int i = 0; i < bonuses.size(); i++) {
			bonuses.get(i).tick();
		}
		
		checkBonusCollection();
		checkOutOfBounds();
	}

	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.drawImage(characterImage, x, y, null);
		
		for (int i = 0; i < bonuses.size(); i++) {
			bonuses.get(i).paint(g);
		}
	}
	
	private void checkBonusCollection() {
		for (int index = 0; index < bonuses.size(); index++) {
			if (this.getBounds().intersects(bonuses.get(index).getBounds())) {
				bonuses.get(index).getBonus();
				bonuses.remove(index);
			}
		}
	}

	public void bonusChance(int x, int y) {
		int chance = randGenerator.nextInt(100);
		if (chance <= 5) {
			bonuses.add(new Bonus(x, y));
		}
		
	}

	private void checkOutOfBounds() {
		if (x <= 0) {			
			x = 0;			
		}
		if (y <= 170) {			
			y = 170; 
		}
		if (x >= GameFrame.WIDTH - characterImage.getWidth(null)) {
			x = GameFrame.WIDTH - characterImage.getWidth(null);
		}
		if (y >= GameFrame.HEIGHT - characterImage.getHeight(null)) {
			y = GameFrame.HEIGHT - characterImage.getHeight(null);
		}
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W ) {
			velY = -speed;
		} else if (key == KeyEvent.VK_S) {
			velY = speed;
		} else if (key == KeyEvent.VK_A) {
			velX = -speed;
		} else if(key == KeyEvent.VK_D){
			velX = speed;
		} else if (key == KeyEvent.VK_R) {
			if (ammo.reloadDelay <= 0) {
				Sound.RELOAD.play();
				ammo.reload();
			}
			
		} else if (key == KeyEvent.VK_SPACE && shootDelay == 0
				&& ammo.getClip() > 0 && !ammo.reloading) {
			Sound.SHOOT.play();
			
			if (GamePanel.choice != 1) {
				GamePanel.bullets.add(new Bullet(x + characterImage.getWidth(null), y
					+ characterImage.getHeight(null) / 2));
			} else {
				GamePanel.bullets.add(new Bullet(x + characterImage.getWidth(null), y
						+ characterImage.getHeight(null) / 5 - 5));
			}
			
			ammo.setClip(ammo.getClip() - 1);
			shootDelay = 4;
			
		}
	}
	
	public void keyReleased(KeyEvent e){
			
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W) {
			velY = 0;
		}
		else if (key == KeyEvent.VK_S){
			velY = 0;
		}
		else if (key == KeyEvent.VK_A){
			velX = 0;
		}
		else if(key == KeyEvent.VK_D){
			velX = 0;
		}
		
   }
		
	private void loadCharacterImage() {
		ImageIcon ii;
		
		if (GamePanel.choice == 0) {
			ii = new ImageIcon("res/Images/nakov.png");
		} else if (GamePanel.choice == 1) {
			ii = new ImageIcon("res/Images/deyan.png");
		} else {
			ii = new ImageIcon("res/Images/angel.png");
		}
		
	    characterImage = ii.getImage();
	}
	
	public Rectangle getBounds(){
		return new Rectangle (this.x,this.y,
				characterImage.getWidth(null), characterImage.getHeight(null));
	}
	
}

