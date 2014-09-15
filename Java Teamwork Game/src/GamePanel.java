import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	static int x = 10;
	
	static Random randGenerator;
	static Player player;
	public static ArrayList<Enemy> enemies;
	
	public GamePanel() {
		
		// for future character selection
		/*Object[] options = {"Nakov", "Deyan", "Angel"};
		
		int choice = JOptionPane.showOptionDialog(null,
				"Choose your character", "Character selection", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (choice == 0) {
			System.out.println("Nakov is selected");
		} else if (choice == 1) {
			System.out.println("Deyan is selected");
		} else {
			System.out.println("Angel is selected");
		}*/
		
		player = new Player();
		enemies = new ArrayList<Enemy>();
		//loadImage();
		KeyListener input = new InputHandler();
		addKeyListener(input);
		
		randGenerator = new Random();
		setSize(GameFrame.WIDTH, GameFrame.HEIGHT);
		setFocusable(true);
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
		
		if (x > GameFrame.WIDTH) {
			x = 10;
		}
	}

	public void generateEnemies() {
		Enemy tempEnemy;
		
		do {
			tempEnemy = new Enemy(GameFrame.WIDTH + randGenerator.nextInt(80), 
					randGenerator.nextInt(GameFrame.HEIGHT - Enemy.getSize() * 2));
			
		} while (avoidIntersection(tempEnemy));
		
		enemies.add(tempEnemy);
		
		checkEnemyOutOfBounds();
		
	}

	private void checkEnemyOutOfBounds() {
		for (int index = 0; index < enemies.size(); index++) {
			if (enemies.get(index).getX() < 0) {
				JOptionPane.showMessageDialog(this, "Your score",
						"Game Over", JOptionPane.YES_NO_OPTION);
				System.exit(0);
			}
		}
		
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
