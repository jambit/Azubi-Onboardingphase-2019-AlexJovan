package com.jambit.game;

import com.jambit.Actor;
import com.jambit.Terminal;
import com.jambit.game.items.Item;
import java.util.ArrayList;

/** Inventory System */
public class Inventory {

  ArrayList<Item> items = new ArrayList<Item>();

  /**
   * Adds Item to inventory
   *
   * @param items item that you want to add
   */
  public void addItem(Item items) {
    this.items.add(items);
  }

  /**
   * Finds a Item in the inventory by its name
   *
   * @param name name to search for
   * @return item object
   */
  private Item findItemByName(String name) {
    for (Item item : items) {
      if (item.getName().equals(name)) {
        return item;
      }
    }
    Terminal.debugMessage("Item " + name + " not found!");
    return null;
  }

  /**
   * Uses item and searches for it by name
   *
   * @param name name to search for
   * @param actor actor that uses the item
   * @return if 1 the item wasn't in the inventory
   */
  public int useItem(String name, Actor actor) {
    Item i = findItemByName(name);
    if (i != null) {
      i.use(actor);
      return 0;
    } else {
      return 1;
    }
  }

  /**
   * use item by object
   *
   * @param item item to use
   * @param actor actor that used the item
   * @return if 1 the item was invalid
   */
  public int useItem(Item item, Actor actor) {
    if (item != null) {
      item.use(actor);
      return 0;
    } else {
      return 1;
    }
  }

  /**
   * Returns inventory as arraylist
   *
   * @return arraylist
   */
  public ArrayList<Item> getItemList() {
    return items;
  }
}
