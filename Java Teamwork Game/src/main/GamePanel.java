package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	static Random randGenerator;
	static Player player;
	
	public static int score;
	static int highscore;
	
	static Image background;
	
	public static ArrayList<Enemy> enemies;
	public static ArrayList<Bullet> bullets;
	public static int choice;
	
	public GamePanel() {
		readHighScore();
		loadBackground();
		characterSelection();
		
		player = new Player();
		enemies = new ArrayList<>();
		bullets = new ArrayList<>();
		
		KeyListener input = new InputHandler();
		addKeyListener(input);
		
		score = 0;
		
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
		drawStatistics(g);
		
	}
	
	public void tick() {
		
		Enemy.enemySpeed += 0.001;
		if (randGenerator.nextInt(100) < 10) {
			generateEnemies();
		}
		
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
		
		if (highscore < score) {
			highscore = score;
		}
		checkCollision();
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
				GamePanel.score += 10;
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
	
	private void checkCollision() {
		if (Player.isEnemyCollision == true) {
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

	}

	private void gameOver() {
		JOptionPane.showMessageDialog(this, "Your score is " + score,
				"Game Over", JOptionPane.YES_NO_OPTION);
		overwriteHighscore();
		System.exit(0);
	}

	private boolean avoidIntersection(Enemy tempEnemy) {
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
		JOptionPane.showMessageDialog(this, "Your score is " + score,
			"Game Over", JOptionPane.YES_NO_OPTION);
		overwriteHighscore();
		System.exit(0);
	}
	
	public void drawStatistics(Graphics g){
		
		g.setColor(Color.RED);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
		g.drawString("Score" + " " + score, 10, 40);
		g.drawString("Highscore: " + highscore, GameFrame.WIDTH - 250, 40);
		g.drawString("Lives: " + player.lives, 10, 140);
		g.drawString("Ammo: " + player.ammo.getClip(), GameFrame.WIDTH - 220 , 140);
	}
	
	private void readHighScore() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(
					"res/highscore.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String line = null;
		
		try {
			while ((line = reader.readLine()) != null) {
			    highscore = Integer.parseInt(line);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void overwriteHighscore() {
		PrintWriter out = null;
		try {
			out = new PrintWriter("res/highscore.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		out.println(highscore);
		out.close();
		
	}
	
	private void loadBackground() {
		ImageIcon ii = new ImageIcon("res/Images/background.png");
		background = ii.getImage();
		
	}
}
