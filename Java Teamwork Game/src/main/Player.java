package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Player {
	static int x;
	static int y;
	int velX = 0;
	int velY = 0;
	
	public int lives;
	int speed = 10;
	Image characterImage;
	
	static ArrayList<Ammo> ammo;
	
	public Player() {
		x = 15;
		y = 250;
		
		lives = 5;
		ammo = new ArrayList<>();
		loadCharacterImage();
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		for (int index = 0; index < ammo.size(); index++) {
			ammo.get(index).tick();
			checkMissileCollision(ammo.get(index));
		}
		
		checkOutOfBounds();
	}
	
	private void checkMissileCollision(Ammo missile) {
		for (int index = 0; index < GamePanel.enemies.size(); index++) {
			if (GamePanel.enemies.get(index).getBounds().intersects(missile.getBounds())) {
				GamePanel.enemies.remove(GamePanel.enemies.get(index));
				ammo.remove(missile);
				GamePanel.score += 10;
			}
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

	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.drawImage(characterImage, x, y, null);
		
		for (int i = 0; i < ammo.size(); i++) {
			ammo.get(i).paint(g);
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
		} else if (key == KeyEvent.VK_SPACE) {
			if (GamePanel.choice != 1) {
				ammo.add(new Ammo(x + characterImage.getWidth(null), y
					+ characterImage.getHeight(null) / 2));
			} else {
				ammo.add(new Ammo(x + characterImage.getWidth(null), y
						+ characterImage.getHeight(null) / 5 - 5));
			}
			
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
			ii = new ImageIcon("res/Deqn.png");
		} else {
			ii = new ImageIcon("res/angel.png");
		}
		
	    characterImage = ii.getImage();
	}
	
	
}

