package com.jambit.game.items;

import com.jambit.Actor;
import com.jambit.Terminal;

public class PaperSword extends Item {

  public PaperSword() {
    setDamage(30);
    setItemType(Item.itemType.sword);
    setName("Paper-Sword");
    setDescription("adds 30 damage to your base damage");
  }

  @Override
  protected void absUse(Actor actor) {
    Terminal.println("Use Paper-Sword");
  }
}
