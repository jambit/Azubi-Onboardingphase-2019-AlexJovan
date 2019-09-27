package com.jambit;

import com.jambit.game.TerminalRPG;

public class Main {

  public static void main(String[] args) {
    Core core = new Core(new TerminalRPG());
    for (String arg : args) {
      if (arg.equals("--debug")) {
        core.DEBUG = true;
      }
    }
    core.start();
  }
}
