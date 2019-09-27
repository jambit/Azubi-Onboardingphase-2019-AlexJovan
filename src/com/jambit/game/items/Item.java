package com.jambit.game.items;

import com.jambit.Actor;
import com.jambit.game.Player;

public abstract class Item {

  private String name;
  private itemType currentItemType;
  protected int durability = 1;
  protected int damage = 0;

  public enum itemType {
    hpPotion,
    shield,
    sword
  }

  /**
   * sets item type
   *
   * @param itemType itemtype to set it to
   */
  protected void setItemType(itemType itemType) {
    this.currentItemType = itemType;
  }

  /**
   * get item type
   *
   * @return return item type
   */
  public itemType getItemType() {
    return this.currentItemType;
  }

  /**
   * returns Name
   *
   * @return item Name
   */
  public String getName() {
    return name;
  }

  /**
   * sets players name
   *
   * @param name name to set it to
   */
  protected void setName(String name) {
    this.name = name;
  }

  /**
   * removes item out of players inventory
   *
   * @param player remove it out of this player
   */
  public void removeInInventory(Player player) {
    for (int i = 0; i < player.getInventory().getItemList().size(); i++) {
      if (player.getInventory().getItemList().get(i).equals(this) && this.getDurability() <= 0) {
        player.getInventory().getItemList().remove(i);
      }
    }
  }

  /**
   * sets the damage of the item
   *
   * @param damage value to set it to
   */
  public void setDamage(int damage) {
    this.damage = damage;
  }

  /**
   * returns damage value
   *
   * @return damage value
   */
  public int getDamage() {
    return damage;
  }

  /**
   * Sets item durability(how often it can be used)
   *
   * @param durability how many uses it can last
   */
  public void setDurability(int durability) {
    this.durability = durability;
  }

  /**
   * returns item durability(how often it can be used)
   *
   * @return how many uses it can last
   */
  public int getDurability() {
    return durability;
  }

  /**
   * What happens when item gets used [OVERRIDE]
   *
   * @param actor Actor that uses the item
   */
  protected abstract void absUse(Actor actor);

  /**
   * Use function that gets called when the item is used
   *
   * @param actor actor that uses the item
   */
  public void use(Actor actor) {
    durability--;
    absUse(actor);
    if (actor instanceof Player) {
      removeInInventory(((Player) actor));
    }
  }
}
