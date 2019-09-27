package com.jambit.game.items;

import com.jambit.Actor;
import com.jambit.Terminal;

public class PaperSword extends Item {

  public PaperSword() {

    setItemType(Item.itemType.sword);
    setName("Paper-Sword");
  }

  @Override
  protected void absUse(Actor actor) {
    setDamage(30);
    Terminal.println("Use Paper-Sword");
  }
}
