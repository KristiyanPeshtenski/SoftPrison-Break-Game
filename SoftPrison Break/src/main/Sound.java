package main;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final AudioClip START_GAME = Applet.newAudioClip
			(Sound.class.getResource("/ThereIsARiot.wav"));
	public static final AudioClip SHOOT = Applet.newAudioClip
			(Sound.class.getResource("/shoot.wav"));
	public static final AudioClip RELOAD = Applet.newAudioClip
			(Sound.class.getResource("/gunReload.wav"));
	public static final AudioClip GAME_OVER = Applet.newAudioClip
			(Sound.class.getResource("/GameOver.wav"));
	public static final AudioClip ENEMY_KILLED = Applet.newAudioClip
			(Sound.class.getResource("/enemyKilled.wav"));
	public static final AudioClip ENEMY_ESCAPED = Applet.newAudioClip
			(Sound.class.getResource("/yay.wav"));
	public static final AudioClip PLAYER_HIT = Applet.newAudioClip
			(Sound.class.getResource("/playerHit.wav"));
	
}
