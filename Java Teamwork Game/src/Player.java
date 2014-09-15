import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Player {
	static int x;
	static int y;
	static int size;
	int velX = 0;
	int velY = 0;
	int speed = 10;
	
	static ArrayList<Missile> ammo;
	
	public Player() {
		x = 15;
		y = 250;
		size = 20;	
		ammo = new ArrayList<>();
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
	
	private void checkMissileCollision(Missile missile) {
		for (int index = 0; index < GamePanel.enemies.size(); index++) {
			if (GamePanel.enemies.get(index).getBounds().intersects(missile.getBounds())) {
				GamePanel.enemies.remove(GamePanel.enemies.get(index));
				ammo.remove(missile);
			}
		}
		
	}

	private void checkOutOfBounds() {
		if (x <= 0) {			
			x = 0;			
		}
		if (y <= 0) {			
			y = 0; 
		}
		if (x >= GameFrame.WIDTH - size - 15) {
			x = GameFrame.WIDTH - size - 15;
		}
		if (y >= GameFrame.HEIGHT - size - 30) {
			y = GameFrame.HEIGHT - size - 30;
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, size, size);
		
		for (Missile missile : ammo) {
			missile.paint(g);
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
			ammo.add(new Missile(x + size, y + size / 3));
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
	
	
}

