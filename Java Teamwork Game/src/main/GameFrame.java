package main;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class GameFrame extends JFrame{

	static final int WIDTH = 498;
	static final int HEIGHT = 684;
	static GamePanel game;
	
	public static void main(String[] args) {
		new GameFrame();
		
	}
	
	public GameFrame() {
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("SoftPrison Break");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		game = new GamePanel();
		this.add(game);
		
		this.setVisible(true);
		
		gameLoop();
	}

	private void gameLoop() {
		while (true) {
			game.tick();
			repaint();
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
	}

}
