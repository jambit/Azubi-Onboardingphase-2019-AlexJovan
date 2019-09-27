package com.jambit.game.items;

import com.jambit.Actor;
import com.jambit.Terminal;

public class WoodenSword extends Item {

  public WoodenSword() {
    durability = 2;
    setItemType(itemType.sword);
    setName("Wooden-Sword");
  }

  @Override
  protected void absUse(Actor actor) {
    setDamage(40);
    Terminal.println("Use Wooden-Sword");
  }
}
