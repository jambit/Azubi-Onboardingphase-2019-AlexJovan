package com.jambit;

import com.jambit.game.Music;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/** Utility class for Terminal outputs */
public class Terminal {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";

  public static Scanner scanner = new Scanner(System.in);

  public static Music music = new Music();

  /** Prevents object being created */
  private Terminal() {}

  /** Clears Terminal */
  public static void clearScreen() {
    System.out.println("\n\n");
  }

  /**
   * Prints a message and ends line
   *
   * @param msg message to print
   */
  public static void println(String msg) {
    System.out.println(msg);
  }

  /**
   * pauses Program
   *
   * @param millisec time to pause
   */
  public static void sleep(int millisec) {
    try {
      TimeUnit.MILLISECONDS.sleep(millisec);
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }

  /**
   * pauses Program in microseconds
   *
   * @param microsec time to pause
   */
  public static void sleepMicroseconds(int microsec) {
    try {
      TimeUnit.MICROSECONDS.sleep(microsec);
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }

  /**
   * Print messages character by character
   *
   * @param msg Message to display
   * @param millisec how long to wait between characters
   */
  public static void writeMessage(String msg, int millisec) {
    if (Core.DEBUG) millisec = 0;
    for (int i = 0; i < msg.length(); i++) {
      System.out.print(msg.charAt(i));
      sleep(millisec);
    }
  }

  /**
   * Print messages over time
   *
   * @param msg Message to display
   */
  public static void writeMessage(String msg) {
    int delay = 70;
    if (Core.DEBUG) delay = 0;
    for (int i = 0; i < msg.length(); i++) {
      System.out.print(msg.charAt(i));
      sleep(delay);
    }
  }

  /**
   * Prints error Message
   *
   * @param msg prints error message
   */
  public static void errorMessage(String msg) {
    System.err.println(msg);
    sleep(1500);
  }

  /**
   * Prints message when DEBUG in core is set to true
   *
   * @param msg debug message
   */
  public static void debugMessage(String msg) {
    if (Core.DEBUG) System.err.println(msg);
  }

  /**
   * Prints a message in color
   *
   * @param msg message to print
   * @param color what color to take
   */
  public static void coloredMessage(String msg, String color) {
    System.out.println(color + msg + ANSI_RESET);
  }

  public static boolean testIfValidInput(ArrayList<Integer> validInputs, int input) {
    for (int x : validInputs) {
      if (validInputs.equals(input)) return true;
    }
    return false;
  }

  public static String bar(int x, int y) {
    float z = (float) x / (float) y;
    String bar = "";
    int barSize = 10;
    bar += "[";
    for (int i = 0; i < barSize; i++) {
      if (i < barSize * z) {
        bar += "=";
      } else {
        bar += " ";
      }
    }
    bar += "]";
    return bar;
  }
}
