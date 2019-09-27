package com.jambit.game.items;

import com.jambit.Actor;
import com.jambit.Terminal;

public class PaperShield extends Item {

  public PaperShield() {
    // Always watch out that you take away the modifier from damage otherwise you will just get the
    // reduced damage
    setItemType(itemType.shield);
    setName("Paper-Shield");
  }

  @Override
  protected void absUse(Actor actor) {
    actor.setDmgReduction(0.2);
    Terminal.println("Use Paper-Shield");
  }
}
