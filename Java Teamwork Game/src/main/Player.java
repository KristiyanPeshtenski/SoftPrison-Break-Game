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
	static int x;
	static int y;
	int velX = 0;
	int velY = 0;
	int shootDelay = 0;
	Random randGenerator;
	
	public int lives;
	int speed = 12;
	Image characterImage;
	
	static ArrayList<Bullet> ammo;
	public static ArrayList<Bonus> bonuses;
	
	public Player() {
		x = 15;
		y = 250;
		
		lives = 5;
		randGenerator = new Random();
		ammo = new ArrayList<>();
		bonuses = new ArrayList<>();
		loadCharacterImage();
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if (shootDelay > 0) {
			shootDelay -= 1;
		}
		
		for (int index = 0; index < ammo.size(); index++) {
			checkBulletOutOfBounds(index);
		}
		
		for (int index = 0; index < ammo.size(); index++) {
			checkBulletCollision(ammo.get(index));
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
		
		for (int i = 0; i < ammo.size(); i++) {
			ammo.get(i).paint(g);
		}
		
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

	private void checkBulletOutOfBounds(int index) {
		ammo.get(index).tick();
		if (ammo.get(index).getX() > GameFrame.WIDTH) {
			ammo.remove(index);
		}
	}
	
	private void checkBulletCollision(Bullet bullet) {
		for (int index = 0; index < GamePanel.enemies.size(); index++) {
			if (GamePanel.enemies.get(index).getBounds().intersects(bullet.getBounds())) {
				GamePanel.enemies.remove(GamePanel.enemies.get(index));
				
				bonusChance(bullet.getX(), bullet.getY());
				ammo.remove(bullet);
				GamePanel.score += 10;
			}
		}
		
	}

	private void bonusChance(int x, int y) {
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
		} else if (key == KeyEvent.VK_SPACE && shootDelay == 0) {
			if (GamePanel.choice != 1) {
				ammo.add(new Bullet(x + characterImage.getWidth(null), y
					+ characterImage.getHeight(null) / 2));
			} else {
				ammo.add(new Bullet(x + characterImage.getWidth(null), y
						+ characterImage.getHeight(null) / 5 - 5));
			}
			
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
			ii = new ImageIcon("res/nakov.png");
		} else if (GamePanel.choice == 1) {
			ii = new ImageIcon("res/deyan.png");
		} else {
			ii = new ImageIcon("res/angel.png");
		}
		
	    characterImage = ii.getImage();
	}
	
	public Rectangle getBounds(){
		return new Rectangle (this.x,this.y,
				characterImage.getWidth(null), characterImage.getHeight(null));
	}
	
}

