package com.jambit.game.items;

import com.jambit.Actor;
import com.jambit.Terminal;

public class MiddleHPPotion extends Item {
  private int healValue = 30;

  public MiddleHPPotion() {
    setItemType(itemType.hpPotion);
    setName("Middle HP Potion");
  }

  @Override
  protected void absUse(Actor actor) {
    actor.addHealthPoints(healValue);
    Terminal.println("Use middle HP Potion");
  }
}
