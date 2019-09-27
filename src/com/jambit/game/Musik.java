package com.jambit.game;

import java.io.File;
import javax.sound.sampled.*;

/** A Thread to start music */
public class Musik extends Thread {
  private Clip clip;

  @Override
  public void run() {}

  public void runMusic(String path) {
    try {
      AudioInputStream audioInputStream =
          AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      clip.start();
    } catch (Exception ex) {
      // System.out.println("Error with playing sound.");
    }
  }

  public void stopMusic() {
    try {
      clip.stop();
    } catch (Exception e) {

    }
  }

  /** Close Thread and stop music */
  public void close() {
    stopMusic();
    this.interrupt();
  }
}
