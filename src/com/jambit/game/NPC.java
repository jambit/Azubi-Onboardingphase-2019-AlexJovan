package com.jambit.game;

import com.jambit.Actor;
import com.jambit.Terminal;
import java.util.ArrayList;

public abstract class NPC extends Actor {
  protected interacts activeInteract = null;
  protected Player interactPlayer = null;
  protected boolean defeated = false;

  private ArrayList<interacts> availableInteracts = new ArrayList<interacts>();

  public void addInteract(interacts interact) {
    boolean addInteract = true;
    for (interacts i : availableInteracts) {
      if (i.equals(interact)) {
        addInteract = false;
      }
    }
    if (addInteract) {
      availableInteracts.add(interact);
    }
  }

  public void setActiveInteract(ArrayList<interacts> x) {
    availableInteracts = x;
  }

  public enum interacts {
    Talk,
    Fight
  }

  public ArrayList<interacts> getAvailableInteracts() {
    return availableInteracts;
  }

  private void activeInteractMenu(Player player) {
    Terminal.println("[0] Exit");
    for (int i = 0; i < availableInteracts.size(); i++) {
      Terminal.println("[" + (i + 1) + "]" + availableInteracts.get(i).name());
    }
    int input = Terminal.scanner.nextInt();
    if (input == 0) {
      endInteract(player);
      return;
    }
    activeInteract = availableInteracts.get(input - 1);
    player.setInInteractObject(this);
    if (activeInteract == interacts.Fight) {
      Terminal.music.runMusic("Music/Battle_Long.wav");
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

  public void endInteract(Player player) {
    Terminal.music.stopMusic();
    player.setInInteractObject(null);
    activeInteract = null;
  }

  @Override
  protected void onDeath() {
    super.onDeath();
    endInteract(interactPlayer);
    interactPlayer = null;
  }
}
