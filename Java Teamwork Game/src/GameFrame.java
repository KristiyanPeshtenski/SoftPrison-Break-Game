import javax.swing.JFrame;


public class GameFrame extends JFrame{

	static final int WIDTH = 500;
	static final int HEIGHT = 500;
	
	public static void main(String[] args) {
		new GameFrame();
		
	}
	
	public GameFrame() {
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		GamePanel game = new GamePanel();
		
		this.add(game);
		this.setVisible(true);
		
		gameLoop();
	}

	private void gameLoop() {
		while (true) {
			repaint();
			GamePanel.tick();
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
