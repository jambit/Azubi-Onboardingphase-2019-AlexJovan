package com.jambit.game;

import com.jambit.Actor;
import com.jambit.Game;
import com.jambit.Level;
import com.jambit.Terminal;
import com.jambit.game.actors.BossAlma;
import com.jambit.game.actors.ReceptionTA;
import java.util.Scanner;

/** Terminal RPG game */
public class TerminalRPG implements Game {

  Player player = new Player();
  Scanner sc = new Scanner(System.in);
  Musik ms = new Musik();
  Level reception = new Level();

  @Override
  public void beforeRun() {
    reception.setName("reception");
    Level sumatra = new Level();
    Level elevatorroom = new Level();
    elevatorroom.setName("elevatorroom");
    sumatra.setName("sumatra");

    sumatra.addConnectedLevel(reception);
    reception.addConnectedLevel(sumatra);
    elevatorroom.addConnectedLevel(reception);

    elevatorroom.addActor(player);
    BossAlma alma = new BossAlma();
    sumatra.addActor(alma);

    ReceptionTA rTA = new ReceptionTA();
    rTA.setName("LIZZI");
    reception.addActor(rTA);

    Terminal.println(
        "You wake up on the floor, everything is dark \n"
            + "Your back hurts, you feel very nausea's because you hadn't eaten in days.\n"
            + "You feel the dryed blood on your clothes sticking to your skin.\n"
            + "You decide to stand up and check how bad your Injuries are.\n"
            + "In the moment you try to stand up you drop to the ground in an instant and you feel how the complete room you are in is moving up.\n"
            + "Two doors open in front of you letting Light comes through the little crack between them.\n"
            + "Your eyes close themselves very quickly and they feel like this is the first time Light touched them\n"
            + "While the Light is burning in your eyes you try to escape from the darkness you were in before\n"
            + "Now that you have left the dark room you can clearly see that you were trapped in an elevator without electricity\n"
            + "You dont even want to start wondering about how the elevator couldn't move but you have electricity in the new room you are in\n"
            + "You are too occupied with the fact that you have lost your memory and that your head is bleeding very badly\n"
            + "But there is hope you think while you see a woman walking to something like a reception but you cant reach her because a glass door is seperating you both and you dont know how to open it\n"
            + "Try to opening the door\n");
    tutorialMenu();
  }

  @Override
  public Actor mainPlayer() {
    return player;
  }

  public void miniMenu() {
    Terminal.println("[1] Pick up and use key-card ");
    Terminal.println("[2] Leave key-card search other way");
    String input = sc.next();
    switch (input) {
      case "1":
        ms.runMusic("Downloads/One_Piece_Epic_Battle_Theme_REMIX_.wav");
        Terminal.println(
            "Congratulations you have opened the locked door, now you step into a big room that looks like a reception");
        player.changeLevel(reception);
        break;
      case "2":
        Terminal.println(
            "You look around you and you see the key-card-scanner from before, 3 locked doors and 2 elevator entrances but nothing is useful for you");
        miniMenu();
        break;
      default:
        break;
    }
  }

  public void tutorialMenu() {
    Terminal.println("---MENU---");
    Terminal.println("current room: " + player.getCurrentLevel().getName());
    Terminal.println("HP: " + player.getHealthPoints());
    Terminal.println("[1] Change Room");
    Terminal.println("[2] Interact list");
    Terminal.println("[3] Inventory");
    Terminal.println("[4] Look around you");

    String input = sc.next();

    switch (input) {
      case "1":
        Terminal.println("You cannot change your room without a key-card ");
        tutorialMenu();
        break;
      case "2":
        Terminal.println("There is a key-card-scanner in front of you but you have no card");
        tutorialMenu();
        break;
      case "3":
        Terminal.println("You have nothing in you Inventory");
        tutorialMenu();

      case "4":
        Terminal.println("You see a key-card on the ground");
        miniMenu();

      default:
        break;
    }
  }
}
