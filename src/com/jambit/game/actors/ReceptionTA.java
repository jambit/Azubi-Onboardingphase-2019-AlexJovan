package com.jambit.game.actors;

import com.jambit.Level;
import com.jambit.game.NPC;
import com.jambit.game.Player;
import com.jambit.game.Quest;

public class ReceptionTA extends NPC {
  Quest q = new Quest(Quest.questTypes.BossFight);

  @Override
  protected void beginPlay() {
    q.addObjective(new Quest.BossFight(Level.findActorInLevel("sumatra", "Alma [BOSS]")));
    addInteract(interacts.Talk);
  }

  @Override
  protected void onTick() {}

  @Override
  protected void talkInteract(Player player) {
    System.out.println(
        "Elizabeth Maierbach: Hello"
            + getName()
            + "\n"
            + "Wait what happend to you?\n"
            + "Why dont you heal yourself first before i explain your mission here");

    player.addQuest(q);

    endInteract(player);
  }
}
