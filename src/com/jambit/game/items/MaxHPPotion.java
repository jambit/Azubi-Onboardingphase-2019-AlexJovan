package com.jambit.game.items;

import com.jambit.Actor;

public class MaxHPPotion extends Item {
  @Override
  protected void absUse(Actor actor) {}

  // int healValue = maxHealth                     Important to have in my opinion for testing and
  // mby cheating and mby playing afterwards

  //    public MaxHPPotion() {
  //        setItemType(itemType.hpPotion);
  //        setName("Max HP Potion");
  //    }
  //
  //    @Override
  //    public void use(Actor actor) {
  //        super.use(actor);
  //        actor.addHealthPoints(healValue);
  //        Terminal.println("Use max HP Potion");
  //    }
}
