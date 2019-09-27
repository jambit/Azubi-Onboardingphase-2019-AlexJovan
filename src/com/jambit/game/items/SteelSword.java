package com.jambit.game.items;

import com.jambit.Actor;
import com.jambit.Terminal;

public class SteelSword extends Item {
  public SteelSword() {
    durability = 3;
    setItemType(itemType.sword);
    setName("Steel-Sword");
  }

  @Override
  protected void absUse(Actor actor) {
    setDamage(50);
    Terminal.println("Use Steel-Sword");
  }
}
