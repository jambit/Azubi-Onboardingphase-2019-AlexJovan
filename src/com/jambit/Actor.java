package com.jambit;

/** Player can Interact with this Object# */
public abstract class Actor {
  private String name;
  private Level currentLevel;
  private boolean canBeDamaged = true;
  private int actorXP = 0;
  private int healthPoints = 1;
  private int maxHP = 100;
  protected boolean isDead = false;
  protected boolean deathEventHappened = false;
  protected boolean intractable = false;

  private double dmgReduction = 1;

  /** Empty Constructor */
  public Actor() {}

  /** What happens if object has been spawned [BASE] */
  public void init() {
    deathEventHappened = false;
    beginPlay();
  }

  /** What happens if object has been spawned [Forces you to have this method] */
  protected abstract void beginPlay();

  /**
   * Returns if Actor is dead
   *
   * @return returns true if player is dead
   */
  public boolean getIsDead() {
    return isDead;
  }

  /**
   * Get Actors name
   *
   * @return returns name of Actor
   */
  public String getName() {
    return name;
  }

  /**
   * Sets Actors Name
   *
   * @param name sets name of Actor
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets Current XP
   *
   * @return xp value
   */
  public int getActorXP() {
    return actorXP;
  }

  /**
   * Set Current XP
   *
   * @param xp value to set it to
   */
  public void setActorXP(int xp) {
    actorXP = xp;
  }

  /**
   * Gets HP
   *
   * @return HP points
   */
  public int getHealthPoints() {
    return healthPoints;
  }

  /**
   * sets HP
   *
   * @param healthPoints Set HP to this number
   */
  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
  }

  /**
   * Adds HP to actor
   *
   * @param healthPoints how much HP is added
   */
  public void addHealthPoints(int healthPoints) {
    this.healthPoints += healthPoints;
  }

  /**
   * Adds to Current Actor XP
   *
   * @param xp value to add to current XP
   */
  public void addActorXP(int xp) {
    actorXP += xp;
  }

  /**
   * Gets Current level Objects
   *
   * @return Level class
   */
  public Level getCurrentLevel() {
    return currentLevel;
  }

  /**
   * Sets Current level to level
   *
   * @param level Level class
   */
  public void setCurrentLevel(Level level) {
    currentLevel = level;
  }

  /**
   * Sets if Actor can get damage
   *
   * @param canBeDamaged if boolean is false Actor can't take any kind of damage
   */
  public void setCanBeDamaged(boolean canBeDamaged) {
    this.canBeDamaged = canBeDamaged;
  }

  /**
   * Returns if Actor can get damaged
   *
   * @return boolean
   */
  public boolean getCanBeDamaged() {
    return canBeDamaged;
  }

  /** update Function that gets called once per Tick [BASE] */
  public void tick() {
    if (Core.DEBUG) {
      Level.testIfDuplicate(this);
    }
    if (healthPoints <= 0 && !deathEventHappened) {
      isDead = true;
      onDeath();
    }
    onTick();
  }

  /** update Function that gets called once per Tick [Forces you to have this method] */
  protected abstract void onTick();

  /**
   * checks if actors are in the same Level
   *
   * @param actor Actor object
   * @return returns true if they are in the same Level
   */
  public boolean inRange(Actor actor) {
    return currentLevel.equals(actor.getCurrentLevel());
  }

  /**
   * Gets called if the Actor gets any kind of damage
   *
   * @param damage damage the actor receives
   * @return damage the actor receives
   */
  protected int onTakeAnyDamage(int damage) {
    return damage;
  }

  /**
   * takes damage
   *
   * @param damage takes damage
   * @param dealer the actor that deals the damage
   */
  public void takeDamage(int damage, Actor dealer) {
    if (canBeDamaged) {
      healthPoints -= onTakeAnyDamage(damage);
    }
  }

  /** On Death Event */
  protected void onDeath() {
    deathEventHappened = true;
    // inFocus = null;
  }

  /**
   * changes Actors Level
   *
   * @param level what Level the character should change to
   */
  public void changeLevel(Level level) {
    currentLevel.removeActor(this);
    level.addActor(this);
    // inFocus = null;
  }

  /**
   * interact with a actor
   *
   * @param actor Actor you want to interact with
   */
  protected void interactWith(Actor actor) {
    actor.onInteract();
  }

  /**
   * gets Modifier from Item
   *
   * @return returns damage modifier
   */

  /**
   * gets Reduction from Item
   *
   * @return returns damage reduction modifier
   */
  public double getDmgReduction() {
    return dmgReduction;
  }

  /**
   * sets Reduction for Item
   *
   * @param dmgReduction what to set the damage reduction modifier to
   */
  public void setDmgReduction(double dmgReduction) {
    this.dmgReduction = dmgReduction;
  }

  /** What happens on an Interact */
  public void onInteract() {}

  public void doDamage(int damage, Actor actor) {
    actor.takeDamage(damage, this);
  }

  public int getMaxHP() {
    return maxHP;
  }

  public void setMaxHP(int maxHP) {
    this.maxHP = maxHP;
  }
}
