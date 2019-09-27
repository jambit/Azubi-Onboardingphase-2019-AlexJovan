package com.jambit;

/** Interface for A Basic game */
public interface Game {
  /** Gets called before the game starts */
  void beforeRun();

  /**
   * Returns the playable character to the game core
   *
   * @return returns mainPlayer to the Game Core
   */
  Actor mainPlayer();
}
