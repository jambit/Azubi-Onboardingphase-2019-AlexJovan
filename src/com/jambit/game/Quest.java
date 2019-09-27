package com.jambit.game;

import com.jambit.Actor;
import java.util.ArrayList;

/** Game Quest Objects */
public class Quest {
  private String name;
  private String description;
  private boolean complete = false;
  private ArrayList<Objective> objectives = new ArrayList<Objective>();
  private questTypes questType;

  public enum questTypes {
    BossFight,
    Find;
  }

  /**
   * Generate a Quest
   *
   * @param qT what type of quest it is
   */
  public Quest(questTypes qT) {
    questType = qT;
  }

  /**
   * Adds an objective that has to be competed to return the quest
   *
   * @param objective the objective
   */
  public void addObjective(Objective objective) {
    objectives.add(objective);
  }

  /**
   * returns if the quest has been completed
   *
   * @return if true it has been completed
   */
  public boolean getComplete() {
    if (objectives.size() > 0) {
      boolean allTrue = true;
      for (Objective objective : objectives) {
        if (!objective.getComplete()) allTrue = false;
      }
      if (allTrue) {
        complete = true;
      }
    }
    return complete;
  }

  /** Base Class for all Objectives */
  private abstract static class Objective {
    protected boolean complete = false;
    protected String description;

    protected abstract void checkComplete();

    /**
     * test if Objective has been completed
     *
     * @return boolean if completed
     */
    public boolean getComplete() {
      if (!complete) {
        checkComplete();
      }
      return complete;
    }
  }

  /** Objective for boss fights */
  public static class BossFight extends Objective {
    private Actor boss;

    public BossFight(Actor actor) {
      boss = actor;
    }

    /** check if the objective has been completed */
    @Override
    protected void checkComplete() {
      if (boss.getIsDead()) complete = true;
    }
  }
}
