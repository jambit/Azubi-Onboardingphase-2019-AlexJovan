package com.jambit;

/** Main Game Core */
public class Core {
  private static boolean running = false;
  short gameCount = 0;
  private Game game;
  public static boolean DEBUG = false;

  /**
   * Constructor
   *
   * @param game constructs a game based on Game Class
   */
  public Core(Game game) {
    this.game = game;
    gameCount++;
  }

  /** Start Game */
  public void start() {
    Terminal.music.start();
    if (!(gameCount > 1)) {
      game.beforeRun();
      running = true;
      mainLoop();
    } else {
      Terminal.errorMessage("Only one Game can be run!");
    }
  }

  /** main game Loop */
  private void mainLoop() {
    while (running) {
      Level.Tick();
    }
    Terminal.music.close();
  }

  /** Ends game main loop */
  public static void stopGame() {
    running = false;
    Terminal.debugMessage("Stopping Game ....");
    System.exit(0);
  }
}
