package edu.angelo.finalprojectbarnhill;

import java.util.Random;

/**
 * A class that keeps track of an enemy in a platformer video game.
 * @author Mason Barnhill
 */
public class Enemy {
    /**
     * a Random objecct that generates random numbers
     */
    public static Random random = new Random();
    /**
     * the number of kinds of enemies
     */
    public static int numKinds = 5;
    /**
     * an array of each height of the enemies, where the ith index indicates the
     * height of the ith enemy
     */
    public static int[] heights = {32, 48, 16, 64, 10};
    /**
     * an array of each width of the enemies, where the ith index indicates the
     * width of the ith enemy
     */
    public static int[] widths = {32, 16, 48, 64, 10};
    /*
     * an array of each velocityX of the enemies, where the ith index indicates the
     * velocityX of the ith enemy
     */
    public static int[] velocitiesX = {1, 2, 1, 2, 0};
    /**
     * an array of each health of the enemies, where the ith index indicates the
     * health of the ith enemy
     */
    public static int[] healths = {1, 1, 1, 1, 0};

    /**
     * which kind the enemy is
     */
    public int kind;
    /**
     *how much health the enemy has
     */
    public int health;
    /**
     * how tall the enemy is
     */
    public int height;
    /**
     *how wide an enemy is
     */
    public int width;
    /**
     *the X velocity of an enemy
     */
    public int velocityX;
    /**
     *the x location of the current enemy
     */
    public int locationX;
    /**
     *the y7 location of the current enemy
     */
    public int locationY;

    /**
     * Constructor: initializes the properties of an enemy based on its location and
     * which type of enemy it is
     * @param  locationX   the x location of the enemy
     * @param locationY   the y location of the enemy
     * @param whichEnemy   the kind of enemy to spawn
     */
     public Enemy(int locationX, int locationY, int whichEnemy) {
        kind = whichEnemy;
        health = healths[whichEnemy];
        height = heights[whichEnemy];
        width = widths[whichEnemy];
        velocityX = velocitiesX[whichEnemy];
        this.locationX = locationX;
        this.locationY = locationY - height;
    }

    /**
     * looking back not sure why I did it like this
     * returns height multiplied by -1
     */
    public int getNegatedHeight() {
         return height * -1;
    }

    /*
     * Constructor: initializes the properties of an enemy based on its location and
     * which type of enemy it is
     * @*param  locationX   the x location of the enemy
     * @param locationY   the y location of the enemy
     */
    public Enemy(int locationX, int locationY) {
        this(locationX, locationY, random.nextInt(numKinds));
    }
    /**
     * returns true if any corner of the traveler is in range
     * @param platform   the platform that is being checked (i believe a dummy
     * platform object is created in place of the enemy to use a lengthy function
     * in the platform object
     * @param displacementX   the change in the xposition of enemy
     * @param displacementY   the change in the yposition of the enemy
     *
     */
    public boolean isAnyCornerInRange(Platform platform, int displacementX, int displacementY) {
        return platform.isInRange(locationX + displacementX, locationY + displacementY) || platform.isInRange(locationX + displacementX, locationY + displacementY + height) || platform.isInRange(locationX + displacementX + width, locationY + displacementY) || platform.isInRange(locationX + displacementX + width, locationY + displacementY + height);
    }
    /**
     * advances each enemy such that enemies walk back and forth on platforms,
     * turning when running into another platform or if the platform they are on ends
     * @param platformSet   is needed to check if the enemy will be grounded after moving
     */
    public void advance(PlatformSet platformSet) {
         Platform[] platforms = platformSet.platforms;
         int resultX = locationX + velocityX;
         for (Platform platform : platforms) {
              if (isAnyCornerInRange(platform, 0, 0)) {
                 if (velocityX < 0) {
                     int bump = platform.finishX;
                     resultX = bump + 1;
                 }
                 else {
                     int bump = platform.startX;
                     resultX = bump - width - 1;
                 }
                 velocityX *= -1;
                 break;
              }
         }
         if (!platformSet.isEnemyGrounded(resultX, resultX + width, locationY + height)) {
             velocityX *= -1;
         }
         else {
             locationX = resultX;
         }
    }

    /**
     * returns true if a player point is in range of enemy, false otherwise
     * @param playerLocX  the x coordinate of the point of the player
     * #param playerLocY  the y coordinate of the point of the player
     */
    public boolean isTravelerPointInRange(int playerLocX, int playerLocY) {
        return playerLocX > locationX && playerLocX < locationX + width &&
                playerLocY > locationY && playerLocY < locationY + height;
    }
    /**
     * checks if four corners of traveler are in range of the enemy
     * @param traveler   the traveler that will be being checked
     */
    public boolean isTravelerInRange(Traveler traveler) {
         int travelerLocationX = traveler.locationX;
         int travelerLocationY = traveler.locationY;
         int travelerWidth = traveler.width;
         int travelerHeight = traveler.height;
         return isTravelerPointInRange(travelerLocationX, travelerLocationY) ||
                 isTravelerPointInRange(travelerLocationX, travelerLocationY + travelerHeight) ||
                 isTravelerPointInRange(travelerLocationX + travelerWidth, travelerLocationY) ||
                 isTravelerPointInRange(travelerLocationX + travelerWidth, travelerLocationY + travelerHeight);
    }
}
