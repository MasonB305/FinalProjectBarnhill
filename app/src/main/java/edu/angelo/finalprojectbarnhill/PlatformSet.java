package edu.angelo.finalprojectbarnhill;

/**
 * A class that keeps track of a set of platforms in a platformer video game
 * @author Mason Barnhill
 */
public class PlatformSet {
    /**
     *the array of platform for the current level
     */
    public Platform[] platforms;
    /**
     *how many platforms there are
     */
    public int numPlatforms;

    /**
     * Constructor: creates a new platform set with numPlatforms platforms
     * @param numPlatforms: the num. of platforms the be created
     */
    public PlatformSet(int numPlatforms) {
        this.numPlatforms = numPlatforms;
        platforms = new Platform[numPlatforms];
    }

    /**
     * returns true if the enemy is grounded, false otherwise
     * @param enemyStart   the starting x coordinate of the enemy
     * @param enemyFinish   the finishing x coordinate of the enemy
     * @param upper enemy.locationY + enemy.height
     */
    public boolean isEnemyGrounded(int enemyStart, int enemyFinish, int upper) {
        for (Platform platform: platforms) {
            if (platform.isEnemyGrounded(enemyStart, enemyFinish, upper)) {
                return true;
            }
        }
        return false;
    }
    /**
     * returns true if the traveler is grounded, false otherwise
     * @param travelerStart   the starting x coordinate of the enemy
     * @param travelerFinish   the finishing x coordinate of the enemy
     * @param upper traveler.locationY + traveler.height
     */
    public boolean isTravelerGrounded(int travelerStart, int travelerFinish, int upper) {
        for (Platform platform: platforms) {
            if (platform.isTravelerGrounded(travelerStart, travelerFinish, upper)) {
                return true;
            }
        }
        return false;
    }
    /*public boolean isInYRange(int upper, int lower) {
        for (Platform platform : platforms) {
            if (platform.isInYRange(upper, lower)) {
                return true;
            }
        }
        return false;
    }
    public boolean isInXRange(int upper, int lower) {
        for (Platform platform : platforms) {
            if (platform.isInXRange(upper, lower)) {
                return true;
            }
        }
        return false;
    }
    */

    /**
     * returns true if a point is in range of the platformSet
     * @param locX the x coordinate of the point
     * @param locY the y coordinate of the point
     */
    public boolean isInRange(int locX, int locY) {
        for (Platform platform : platforms) {
            if (platform.isInRange(locX, locY)) {
                return true;
            }
        }
        return false;
    }
}
