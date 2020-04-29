package LF2.custom.mp3;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class MP3Player implements Runnable {

	private InputStream path;
	private AdvancedPlayer ap = null;
	private Thread thread = null;

	public MP3Player(InputStream path) {
		this.path = path;
		thread = new Thread(this);
	}

	public void play() {
		thread.start();
	}

	public void close() {
		ap.close();
	}

	@Override
	public void run() {
		try {
			ap = new AdvancedPlayer(path);
			ap.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
		ap.close();
	}

}