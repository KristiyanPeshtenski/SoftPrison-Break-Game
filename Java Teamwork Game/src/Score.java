import java.io.Serializable;


public class Score implements Serializable {
	
	private int score;
	private String name;
	
	
	
	public Score (int score){
		this.score = GamePanel.score;
	}
	
	public int getScore(){
		return score;
	}
	
	public String name() {
		return name;
	}
}
