package com.jambit.game;

import com.jambit.Actor;
import com.jambit.Terminal;
import java.util.ArrayList;

public abstract class NPC extends Actor {
  protected interacts activeInteract = null;
  protected Player interactPlayer = null;

  protected ArrayList<interacts> availableInteracts = new ArrayList<interacts>();

  public enum interacts {
    Talk,
    Fight
  }

  public ArrayList<interacts> getAvailableInteracts() {
    return availableInteracts;
  }

  private void activeInteractMenu(Player player) {
    for (int i = 0; i < availableInteracts.size(); i++) {
      Terminal.println("[" + (i + 1) + "]" + availableInteracts.get(i).name());
    }
    int input = Terminal.scanner.nextInt();
    activeInteract = availableInteracts.get(input - 1);
    player.setInInteractObject(this);
    if (activeInteract == interacts.Fight) {
      Terminal.musik.runMusic("Music\\Battle_Long.wav");
      Terminal.writeMessage("You Started a fight with " + getName() + "\n");
    }
  }

  public void interact(Player player) {
    if (activeInteract == null) {
      interactPlayer = player;
      activeInteractMenu(player);
    } else {
      switch (activeInteract) {
        case Talk:
          talkInteract(player);
          break;
        case Fight:
          fightInteract(player);
          break;
      }
    }
  }

  protected void talkInteract(Player player) {
    endInteract(player);
  }

  protected void fightInteract(Player player) {
    player.fightMenu(this);
  }

  protected void endInteract(Player player) {
    player.setInInteractObject(null);
    activeInteract = null;
    Terminal.musik.stopMusic();
  }

  @Override
  protected void onDeath() {
    super.onDeath();
    endInteract(interactPlayer);
    interactPlayer = null;
  }
}
