package com.jambit.game.items;

import com.jambit.Actor;
import com.jambit.Terminal;

public class SteelShield extends Item {
  public SteelShield() {
    durability = 3;
    setItemType(itemType.shield);
    setName("Steel-Shield");
  }

  @Override
  protected void absUse(Actor actor) {
    actor.setDmgReduction(0.6);
    Terminal.println("Use Steel-Shield");
  }
}
