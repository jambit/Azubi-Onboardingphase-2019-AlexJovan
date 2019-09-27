package com.jambit;

import com.jambit.game.TerminalRPG;

public class Main {

  public static void main(String[] args) {
    Core core = new Core(new TerminalRPG());
    if (args[0].equals("--debug")) {
      core.DEBUG = true;
    }
    core.start();
  }
}
