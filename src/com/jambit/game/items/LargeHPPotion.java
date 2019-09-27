package com.jambit.game.items;

import com.jambit.Actor;
import com.jambit.Terminal;

public class LargeHPPotion extends Item {
  private int healValue = 50;

  public LargeHPPotion() {
    setItemType(Item.itemType.hpPotion);
    setName("Large HP Potion");
  }

  @Override
  protected void absUse(Actor actor) {
    actor.addHealthPoints(healValue);
    Terminal.println("Use large HP Potion");
  }
}
