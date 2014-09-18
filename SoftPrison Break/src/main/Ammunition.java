package main;

public class Ammunition {

	int clip;
	int reloadDelay = 0;
	boolean reloading = false;
	
	public Ammunition() {
		
		// the initial ammo is 50. It cannot get beyond this.
		clip = 50;
	}
	
	public void tick() {
		// while we are reloading, we cannot shoot.
		if (reloadDelay > 0) {
			reloadDelay--;
		}
		
		// when we have reloaded, we get the clip to its initial value
		if (reloadDelay <= 0 && reloading) {
			clip = 50;
			reloading = false;
		}
	}
	
	// in case we click R
	public void reload() {
		reloadDelay = 20;
		reloading = true;
	}

	// getters and setters for the clip
	public int getClip() {
		return clip;
	}

	public void setClip(int clip) {
		this.clip = clip;
	}
	
	
}
