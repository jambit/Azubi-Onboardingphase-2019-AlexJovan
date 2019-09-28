package com.jambit;

import java.util.ArrayList;

/** Playable Levels */
public class Level {
  private boolean isActive = true;
  private String name;

  private static ArrayList<Level> levelArrayList = new ArrayList<Level>();
  private ArrayList<Actor> actorArrayList = new ArrayList<Actor>();
  private ArrayList<Level> connectedLevel = new ArrayList<Level>();

  /** Default Constructor */
  public Level() {
    levelArrayList.add(this);
  }

  /**
   * Constructor
   *
   * @param name set name
   */
  public Level(String name) {
    this.name = name;
    levelArrayList.add(this);
  }

  /** Update all Levels Actors that are in an active Level */
  public static void Tick() {
    for (int i = 0; i < levelArrayList.size(); i++) {
      if (levelArrayList.get(i).getIsActive()) {
        levelArrayList.get(i).objectTick();
      }
    }
  }

  /**
   * Returns True if Level is allowed to update
   *
   * @return returns boolean
   */
  public boolean getIsActive() {
    return isActive;
  }

  /**
   * Rooms a normal Player can move into
   *
   * @param level level object
   */
  public void addConnectedLevel(Level level) {
    if (!this.equals(level)) {
      connectedLevel.add(level);
    } else {
      Terminal.debugMessage("You can't add the same LEVEL as connected LEVEL {" + name + "]");
    }
  }

  /**
   * Returns list of Connected levels
   *
   * @return list of levels connected to current level
   */
  public ArrayList<Level> getConnectedLevel() {
    return connectedLevel;
  }

  /**
   * Gets Array list
   *
   * @return ArrayList
   */
  public ArrayList<Actor> getActorArrayList() {
    return actorArrayList;
  }

  /**
   * Adds actor to Level
   *
   * @param actor actor Object
   */
  public void addActor(Actor actor) {
    this.actorArrayList.add(actor);
    actor.setCurrentLevel(this);
    actor.init();
  }

  /**
   * remove Actor from Level
   *
   * @param actor actor object
   */
  public void removeActor(Actor actor) {
    for (int i = 0; i < actorArrayList.size(); i++) {
      if (actorArrayList.get(i).equals(actor)) {
        actorArrayList.remove(i);
      }
    }
  }

  /**
   * Remove actor from any Level
   *
   * @param actor actor object
   * @param level level object
   */
  public static void removeActor(Actor actor, Level level) {
    for (int i = 0; i < level.getActorArrayList().size(); i++) {
      if (level.getActorArrayList().get(i).equals(actor)) {
        level.removeActor(actor);
      }
    }
  }

  /**
   * get Level Name
   *
   * @return level name
   */
  public String getName() {
    return name;
  }

  /**
   * sets Level Name and changes it to lower case
   *
   * @param name Level Name
   */
  public void setName(String name) {
    this.name = name.toLowerCase();
  }

  /** updates all objects in a Level */
  public void objectTick() {
    for (int i = 0; i < actorArrayList.size(); i++) {
      actorArrayList.get(i).tick();
    }
  }

  /**
   * Tests if a Actor has been added to multiple levels [Used for debugging]
   *
   * @param actor gets Actor object to check
   */
  public static void testIfDuplicate(Actor actor) {
    for (int i = 0; i < levelArrayList.size(); i++) {
      for (int j = 0; j < levelArrayList.get(i).getActorArrayList().size(); j++) {
        if (levelArrayList.get(i).getActorArrayList().get(j).equals(actor)
            && !(actor.getCurrentLevel().equals(levelArrayList.get(i)))) {
          Terminal.errorMessage("Actor: " + actor.getName() + " [DUPLICATE]");
          removeActor(actor, levelArrayList.get(i));
        }
      }
    }
  }

  /**
   * Find a Level by its given Name
   *
   * @param name name to search for
   * @return returns Level
   */
  public static Level findLevelByName(String name) {
    for (int i = 0; i < levelArrayList.size(); i++) {
      if (levelArrayList.get(i).getName().equals(name)) {
        return levelArrayList.get(i);
      }
    }
    return null;
  }

  public static Actor findActorInLevel(String levelName, String actorName) {
    Level level = findLevelByName(levelName);
    if (level == null) {
      Terminal.debugMessage("no level fund with the name: " + levelName);
    }
    assert level != null;
    for (Actor actor : level.getActorArrayList()) {
      if (actor.getName().equals(actorName)) {
        return actor;
      }
    }
    return null;
  }
}
