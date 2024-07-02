package edu.angelo.finalprojectbarnhill;

import java.util.Scanner;

/**
 * A class that keeps track of a pair of integers, ideally world and time in that order.
 * @author Mason Barnhill
 */
public class IntPair {
    /**
     * which world the player reached
     */
    public int worldNumber;
    /**
     *the time it took the player to get there
     */
    public int time;

    /**
     * Constructor: creates a new IntPair object
     * @param worldNumber  which world to be saved
     * @param time   the time to be saved
     */
    public IntPair(int worldNumber, int time) {
        this.worldNumber = worldNumber;
        this.time = time;
    }
    /**
     * Constructor: creates a new IntPair object with a worldNumber and time of 0
     */
    public IntPair() {
        this.worldNumber = 0;
        this.time = 0;
    }

    /**
     * returns true if other is less  than this
     * @param other  the IntPair that is being compared with this
     */
    public boolean lessThan(IntPair other) {
        if(this.worldNumber == other.worldNumber) {
            return this.time > other.time;
        }
        return this.worldNumber < other.worldNumber;
    }
    /**
     * returns true if other is greater  than this
     * @param other  the IntPair that is being compared with this
     */
    public boolean greaterThan(IntPair other) {
        if(this.worldNumber == other.worldNumber) {
            return this.time < other.time;
        }
        return this.worldNumber > other.worldNumber;
    }

    /**
     * parses an IntPair from a string and returns it.
     * the format is 2 space seperated integers, the first being
     * world and the second being time
     * @param string  the string which is being parsed
     */
    public static IntPair parseIntPair(String string) {
        Scanner scnr = new Scanner(string);
        int whichWorld = scnr.nextInt();
        int time = scnr.nextInt();
        scnr.close();
        return new IntPair(whichWorld, time);
    }

    /**
     * stringifies an IntPair
     * @param intPair  the IntPair being stringified
     */
    public static String toString(IntPair intPair) {
        //return "4 4";
        return "   " + intPair.worldNumber + "   " + intPair.time;
    }
}
