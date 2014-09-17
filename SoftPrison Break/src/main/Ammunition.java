package main;

public class Ammunition {

	int clip;
	int reloadDelay = 0;
	boolean reloading = false;
	
	public Ammunition() {
		
		clip = 50;
	}
	
	public void tick() {
		if (reloadDelay > 0) {
			reloadDelay--;
		}
		
		if (reloadDelay <= 0 && reloading) {
			clip = 50;
			reloading = false;
		}
	}
	
	public void reload() {
		reloadDelay = 20;
		reloading = true;
	}

	public int getClip() {
		return clip;
	}

	public void setClip(int clip) {
		this.clip = clip;
	}
	
	
}
