package com.jambit.game.items;

import com.jambit.Actor;
import com.jambit.Terminal;

public class WoodenShield extends Item {

  public WoodenShield() {
    durability = 2;
    setItemType(itemType.shield);
    setName("Wooden-Shield");
  }

  @Override
  protected void absUse(Actor actor) {
    actor.setDmgReduction(0.4);
    Terminal.println("Use Wooden-Shield");
  }
}
