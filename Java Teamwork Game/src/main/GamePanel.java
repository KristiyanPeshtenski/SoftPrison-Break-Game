package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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
	public static int choice;
	
	public GamePanel() {
		
		characterSelection();
		
		player = new Player();
		enemies = new ArrayList<Enemy>();
		
		KeyListener input = new InputHandler();
		addKeyListener(input);
		
		score = 0;
		readHighScore();
		loadBackground();
		
		randGenerator = new Random();
		setSize(GameFrame.WIDTH, GameFrame.HEIGHT);
		setFocusable(true);
	}

	private void loadBackground() {
		ImageIcon ii = new ImageIcon("res/background.png");
		background = ii.getImage();
		
	}

	private void characterSelection() {
		Object[] options = {"Nakov", "Deyan", "Angel"};
		
		choice = JOptionPane.showOptionDialog(null,
				"Choose your character", "Character selection", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(background, 0, 0, null);
		
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).paint(g);
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
		
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).tick();
		}
		
		if (highscore < score) {
			highscore = score;
		}
	}

	public void generateEnemies() {
		Enemy tempEnemy;
		
		do {
			tempEnemy = new Enemy(GameFrame.WIDTH + randGenerator.nextInt(50), 
					190 + randGenerator.nextInt(GameFrame.HEIGHT - 170
							- player.characterImage.getHeight(null)));
			
		} while (avoidIntersection(tempEnemy));
		
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
	
	public void drawStatistics(Graphics g){
		
		g.setColor(Color.RED);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
		g.drawString("Score" + " " + score, 10, 40);
		g.drawString("Highscore: " + highscore, GameFrame.WIDTH - 250, 40);
		g.drawString("Lives: " + player.lives, 10, 150);
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
}
