package com.jambit.game.items;

import com.jambit.Actor;
import com.jambit.Terminal;

public class SmallHPPotion extends Item {
  private final int healValue = 15;

  public SmallHPPotion() {
    setItemType(itemType.hpPotion);
    setName("Small HP Potion");
  }

  @Override
  protected void absUse(Actor actor) {
    actor.addHealthPoints(healValue);
    Terminal.println("Use small HP Potion");
  }
}
