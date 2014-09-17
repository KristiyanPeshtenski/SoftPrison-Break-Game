package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	static Random randGenerator;
	static Player player;
	
	public static Statistics statistics;
	
	static Image background;
	
	public static ArrayList<Enemy> enemies;
	public static ArrayList<Bullet> bullets;
	public static int choice;
	
	public GamePanel() {
		statistics = new Statistics();
		loadBackground();
		characterSelection();
		
		player = new Player();
		enemies = new ArrayList<>();
		bullets = new ArrayList<>();
		
		KeyListener input = new InputHandler();
		addKeyListener(input);
		
		randGenerator = new Random();
		setSize(GameFrame.WIDTH, GameFrame.HEIGHT);
		setFocusable(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(background, 0, 0, null);
		
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).paint(g);
		}
		
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
		
		player.paint(g);
		statistics.paint(g);
		
	}
	
	public void tick() {
		
		Enemy.enemySpeed += 0.001;
		if (randGenerator.nextInt(100) < 10) {
			generateEnemies();
		}
		
		statistics.tick();
		player.tick();
		
		for (int index = 0; index < bullets.size(); index++) {
			checkBulletOutOfBounds(index);
		}
		
		for (int index = 0; index < bullets.size(); index++) {
			checkBulletCollision(bullets.get(index));
		}
		
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).tick();
		}
	}
	
	private void checkBulletOutOfBounds(int index) {
		bullets.get(index).tick();
		if (bullets.get(index).getX() + 10 > GameFrame.WIDTH) {
			bullets.remove(index);
		}
	}
	
	private void checkBulletCollision(Bullet bullet) {
		for (int index = 0; index < GamePanel.enemies.size(); index++) {
			if (GamePanel.enemies.get(index).getBounds().intersects(bullet.getBounds())) {
				GamePanel.enemies.remove(GamePanel.enemies.get(index));
				
				player.bonusChance(bullet.getX(), bullet.getY());
				bullets.remove(bullet);
				statistics.score += 10;
			}
		}
		
	}

	public void generateEnemies() {
		Enemy tempEnemy;
		
		do {
			tempEnemy = new Enemy(GameFrame.WIDTH + randGenerator.nextInt(300), 
					190 + randGenerator.nextInt(GameFrame.HEIGHT - 170
							- player.characterImage.getHeight(null)));
			
		} while (avoidEnemyIntersection(tempEnemy));
		
		enemies.add(tempEnemy);
		
		checkEnemyOutOfBounds();
		
	}

	private void checkEnemyOutOfBounds() {
		for (int index = 0; index < enemies.size(); index++) {
			if (enemies.get(index).getX() < 0) {
				if (player.lives == 1) {
					gameOver();
				
				} else {
					enemies.remove(index);
					player.lives--;
				}
				
			}
		}
		
	}
	
	private boolean avoidEnemyIntersection(Enemy tempEnemy) {
		for (Enemy enemy : enemies) {
			if (enemy.getBounds().intersects(tempEnemy.getBounds())) {
				return true;
			}
		}
		return false;
	}
	
	private void characterSelection() {
		Object[] options = {"Nakov", "Deyan", "Angel"};
		
		choice = JOptionPane.showOptionDialog(null,
				"Choose your character", "Character selection", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
	}

	private void gameOver() {
		JOptionPane.showMessageDialog(this, "Your score is " + statistics.score,
			"Game Over", JOptionPane.YES_NO_OPTION);
		statistics.overwriteHighscore();
		System.exit(0);
	}
	
	private void loadBackground() {
		ImageIcon ii = new ImageIcon("res/Images/background.png");
		background = ii.getImage();
		
	}
}
