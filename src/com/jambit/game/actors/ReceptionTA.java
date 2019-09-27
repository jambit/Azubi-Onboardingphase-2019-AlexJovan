package com.jambit.game.actors;

import com.jambit.game.NPC;
import com.jambit.game.Player;

public class ReceptionTA extends NPC {
  @Override
  protected void beginPlay() {}

  @Override
  protected void onTick() {}

  @Override
  protected void talkInteract(Player player) {
    System.out.println(
        "Elizabeth Maierbach: Hello"
            + getName()
            + "\n"
            + "wait what happend to you?\n"
            + "Why dont you heal yourself first before i explain your mission here");

    endInteract(player);
  }
}
