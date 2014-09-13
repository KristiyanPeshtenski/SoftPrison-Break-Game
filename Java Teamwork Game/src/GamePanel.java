import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	static int x = 10;
	
	static Random randGenerator;
	static Player player;
	static Enemy enemy;
	public static ArrayList<Enemy> enemies;
	
	public GamePanel() {
		player = new Player();
		enemies = new ArrayList<Enemy>();
		enemy = new Enemy(40, 40);
		
		randGenerator = new Random();
		setSize(GameFrame.WIDTH, GameFrame.HEIGHT);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		for (Enemy enemy : enemies) {
			enemy.paint(g);
		}
		player.paint(g);
		
	}
	
	public void tick() {
		x += 5;
		
		if (randGenerator.nextInt(100) < 10) {
			generateEnemies();
		}
		
		
		player.tick();
		
		for (Enemy enemy : enemies) {
			enemy.tick();
		}
		
		//loadImage();
		if (x > GameFrame.WIDTH) {
			x = 10;
		}
	}

	public void generateEnemies() {
		Enemy tempEnemy;
		
		do {
			tempEnemy = new Enemy(10 + randGenerator.nextInt(GameFrame.WIDTH - 60), 
					-110 + randGenerator.nextInt(100));
			
		} while (avoidIntersection(tempEnemy));
		
		enemies.add(tempEnemy);
		
	}

	private boolean avoidIntersection(Enemy tempEnemy) {
		for (Enemy enemy : enemies) {
			if (enemy.getBounds().intersects(tempEnemy.getBounds())) {
				return true;
			}
		}
		return false;
	}
	
	/*private static void loadImage() {

        ImageIcon ii = new ImageIcon("res/joker.png");
        joker = ii.getImage();
    }*/
}
