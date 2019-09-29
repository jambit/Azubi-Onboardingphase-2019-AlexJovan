package com.jambit.game.actors;

import com.jambit.Terminal;
import com.jambit.game.NPC;
import com.jambit.game.Player;
import java.util.ArrayList;
import java.util.Random;

public class BossAlma extends NPC {
  @Override
  protected void talkInteract(Player player) {
    addInteract(interacts.Fight);
    Terminal.coloredMessage("Now you can Fight with " + getName(), Terminal.ANSI_YELLOW);
    endInteract(player);
  }

  @Override
  protected void fightInteract(Player player) {
    super.fightInteract(player);
    if (getHealthPoints() <= 0) {
      return;
    }
    Terminal.writeMessage(getName() + " started an Attack.........\n\n");
    Random rand = new Random();
    switch (rand.nextInt((6 - 1) + 1) + 1) {
      case 1:
      case 2:
        Terminal.println(getName() + "s attack failed; \n");
        break;
      case 3:
      case 4:
        player.takeDamage(5, this);
        Terminal.writeMessage(getName() + " did 5 damage\n");
        break;
      case 5:
      case 6:
        player.takeDamage(8, this);
        Terminal.writeMessage(getName() + " did 8 damage\n");
        break;
      default:
        break;
    }
  }

  @Override
  protected void beginPlay() {
    setName("Alma [BOSS]");
    addInteract(interacts.Talk);
    setHealthPoints(100);
  }

  @Override
  protected void onTick() {}

  @Override
  protected void onDeath() {
    super.onDeath();
    Terminal.coloredMessage(getName() + "has been defeated.....", Terminal.ANSI_RED);
    setActiveInteract(new ArrayList<interacts>());
  }
}
