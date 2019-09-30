package com.jambit.game.actors;

import com.jambit.game.NPC;

public class Liz extends NPC {
  @Override
  protected void beginPlay() {
    setHealthPoints(100);
    setName("LIZ");
    addInteract(interacts.Talk);
  }

  @Override
  protected void onTick() {}
}
