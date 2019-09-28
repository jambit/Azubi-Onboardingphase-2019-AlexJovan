package com.jambit.game;

import com.jambit.Actor;
import com.jambit.Core;
import com.jambit.Terminal;
import com.jambit.game.items.Item;
import com.jambit.game.items.LargeHPPotion;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/** Player Class */
public class Player extends Actor {
  private Scanner scanner = new Scanner(System.in);

  private NPC inInteractObject = null;

  private Inventory inventory = new Inventory();
  private ArrayList<Quest> questList = new ArrayList<>();

  @Override
  public void beginPlay() {
    intractable = false;
    inventory.addItem(new LargeHPPotion());
    setMaxHP(100);
    setHealthPoints(100);
    if (Core.DEBUG) {
      setMaxHP(1000);
      setHealthPoints(1000);
    }
  }

  @Override
  public void onTick() {
    checkQuest();
    if (inInteractObject == null) playerMenu();
    else inInteractObject.interact(this);
    if (getMaxHP() < getHealthPoints()) setHealthPoints(getMaxHP());
  }

  @Override
  protected int onTakeAnyDamage(int damage) {
    return (int) ((double) damage * getDmgReduction());
  }

  @Override
  protected void onDeath() {
    super.onDeath();
    Terminal.println("You Died");
    Core.stopGame();
  }

  /** player interact menu */
  private void playerInteractMenu() {
    boolean validInput = false;
    do {
      Terminal.println("----INTERACT LIST----");
      Terminal.println("[0] Exit");
      for (int i = 0; i < getCurrentLevel().getActorArrayList().size(); i++) {
        try {
          if (getCurrentLevel().getActorArrayList().get(i) instanceof NPC
              && ((NPC) getCurrentLevel().getActorArrayList().get(i)).getAvailableInteracts().size()
                  > 0) {
            Terminal.println(
                "[" + (i + 1) + "] " + getCurrentLevel().getActorArrayList().get(i).getName());
          }
        } catch (NullPointerException ex) {
        }
      }
      int input;
      Scanner scanner = new Scanner(System.in);
      Terminal.coloredMessage("What do you want to interact with: ", Terminal.ANSI_GREEN);
      try {
        input = scanner.nextInt();
      } catch (InputMismatchException ex) {
        Terminal.errorMessage("Invalid Input");
        continue;
      }
      if (input == 0) {
        return;
      } else {
        try {
          ((NPC) getCurrentLevel().getActorArrayList().get(input - 1)).interact(this);
          validInput = true;
        } catch (ClassCastException | IndexOutOfBoundsException | NullPointerException ex) {
          Terminal.errorMessage("That interact object isn't available!!");
          Terminal.clearScreen();
        }
      }
    } while (!validInput);
  }

  /** Player main Menu */
  private void playerMenu() {
    Terminal.clearScreen();
    Terminal.coloredMessage("---MENU---", Terminal.ANSI_PURPLE);
    Terminal.coloredMessage("current room: " + getCurrentLevel().getName(), Terminal.ANSI_PURPLE);
    Terminal.coloredMessage(
        "HP: "
            + Terminal.bar(getHealthPoints(), getMaxHP())
            + getHealthPoints()
            + " / "
            + getMaxHP(),
        Terminal.ANSI_PURPLE);
    Terminal.coloredMessage("[1] Change Room", Terminal.ANSI_PURPLE);
    Terminal.coloredMessage("[2] Interact list", Terminal.ANSI_PURPLE);
    Terminal.coloredMessage("[3] Inventory \n\n [0] Quit Game", Terminal.ANSI_PURPLE);

    String input = scanner.next();

    switch (input) {
      case "0":
        Core.stopGame();
        break;
      case "1":
        playerChangeRoomMenu();
        break;
      case "2":
        playerInteractMenu();
        break;
      case "3":
        inventoryMenu();
      default:
        break;
    }
  }

  /** change room menu */
  private void playerChangeRoomMenu() {
    Terminal.println("What Room do you want to Enter?");
    Terminal.println("[0] Exit");
    for (int i = 0; i < getCurrentLevel().getConnectedLevel().size(); i++) {
      Terminal.println(
          "[" + (i + 1) + "] " + getCurrentLevel().getConnectedLevel().get(i).getName());
    }
    int input = scanner.nextInt();
    if (input == 0) {
      return;
    }
    try {
      changeLevel(getCurrentLevel().getConnectedLevel().get(input - 1));
    } catch (IndexOutOfBoundsException ex) {
      Terminal.errorMessage("That LEVEL isn't available!!");
    }
  }

  /** Opens inventory */
  private Item inventoryMenu() {
    do {
      Terminal.println("----INVENTORY----");
      int i = 0;
      Terminal.println("[0]Exit");
      for (Item item : inventory.getItemList()) {
        if (item.getDurability() > 0) {
          Terminal.println("[" + (i++ + 1) + "]" + item.getName());
          if (item.getDescription() != null) {
            Terminal.coloredMessage("    (+ " + item.getDescription() + ")", Terminal.ANSI_YELLOW);
          }
        }
      }
      int input;
      Scanner scanner = new Scanner(System.in);
      Terminal.coloredMessage("What item do you want to use: ", Terminal.ANSI_GREEN);
      try {
        input = scanner.nextInt();
      } catch (InputMismatchException ex) {
        Terminal.errorMessage("Invalid Input");
        continue;
      }
      if (input == 0) {
        return null;
      } else {
        Item item;
        try {
          item = inventory.getItemList().get(input - 1);
        } catch (IndexOutOfBoundsException ex) {
          Terminal.errorMessage("That Item is not available!");
          return null;
        }
        if (item.getItemType() == Item.itemType.sword && inInteractObject != null) {
          Terminal.coloredMessage(
              "You can't use a Item of type \"" + item.getItemType() + "\" out side of an interact",
              Terminal.ANSI_RED);
          Terminal.sleep(200);
        } else {
          inventory.useItem(item, this);
        }
        return item;
      }
    } while (true);
  }

  /**
   * players fight menu
   *
   * @param actor enemy object
   */
  public void fightMenu(Actor actor) {
    Terminal.coloredMessage("---Chose your attack---", Terminal.ANSI_BLUE);
    Terminal.coloredMessage(
        "Your HP: "
            + Terminal.bar(getHealthPoints(), getMaxHP())
            + getHealthPoints()
            + " / "
            + getMaxHP(),
        Terminal.ANSI_CYAN);
    Terminal.coloredMessage(
        inInteractObject.getName()
            + " HP: "
            + Terminal.bar(inInteractObject.getHealthPoints(), inInteractObject.getMaxHP())
            + inInteractObject.getHealthPoints()
            + " / "
            + inInteractObject.getMaxHP(),
        Terminal.ANSI_CYAN);
    Terminal.coloredMessage("[1] HIT\n[2] Inventory", Terminal.ANSI_CYAN);
    int input = Terminal.scanner.nextInt();

    if (input == 1) {
      Terminal.coloredMessage("You started an attack and did 20HP damage", Terminal.ANSI_YELLOW);
      Terminal.sleep(1000);
      doDamage(20, inInteractObject);
    } else if (input == 2) {

      Item item = inventoryMenu();
      if (item == null) {
        fightMenu(actor);
        return;
      }
      if (item.getItemType() == Item.itemType.sword) {
        Terminal.coloredMessage(
            "You started an attack with \""
                + item.getName()
                + "\" and did "
                + (20 + item.getDamage())
                + "HP damage",
            Terminal.ANSI_YELLOW);
        doDamage(20 + item.getDamage(), inInteractObject);
      }
    }
  }

  /**
   * returns Inventory object
   *
   * @return Inventory object
   */
  public Inventory getInventory() {
    return inventory;
  }

  /** check if quest has been completed */
  private void checkQuest() {
    ArrayList<Quest> remove = new ArrayList<Quest>();
    for (Quest quest : questList) {
      if (quest.getComplete()) {
        Terminal.coloredMessage("\nQUEST COMPLETE", Terminal.ANSI_GREEN);
        Terminal.sleep(1000);
      }
    }
    for (Quest x : remove) {
      questList.remove(x);
    }
  }

  /**
   * add quest to active quest log
   *
   * @param quest quest to add
   */
  public void addQuest(Quest quest) {
    questList.add(quest);
  }

  /**
   * Sets the interact object
   *
   * @param inInteractObject the NPC that the player interacts with
   */
  public void setInInteractObject(NPC inInteractObject) {
    this.inInteractObject = inInteractObject;
  }
}
