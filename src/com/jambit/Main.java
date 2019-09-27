package com.jambit;

import com.jambit.game.TerminalRPG;

public class Main {

  public static void main(String[] args) {
    Core core = new Core(new TerminalRPG());
    Core.DEBUG = true;
    core.start();
  }
}
