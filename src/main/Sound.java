package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Importowanie plik�w .wav do programu oraz ich obs�uga
 *
 */
public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL[30];
	
	/**
	 * odczytanie plik�w dzwi�kowych z �cie�ki
	 */
	public Sound() {
		soundURL[0]= getClass().getResource("/music/main.wav");
		soundURL[1]= getClass().getResource("/music/get.wav");
	}
	
	/**
	 * otwieranie pliku dzwi�kowego
	 */
	public void setFile(int i) {
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e) {
			
		}
	}
	
	/**
	 * odtwarzanie muzyki
	 */
	public void play() {
		clip.start();
	}
	/**
	 * zap�tlenie muzyki
	 */
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	/**
	 * zatrzymanie muzyki
	 */
	public void stop() {
		clip.stop();
	}

}
