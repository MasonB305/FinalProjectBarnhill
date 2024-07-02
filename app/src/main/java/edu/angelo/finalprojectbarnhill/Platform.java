package edu.angelo.finalprojectbarnhill;

/**
 * A class that keeps track one platform in a platformer video game
 * @author Mason Barnhill
 */
public class Platform {
    /**
     *the x location where the platform starts
     */
    public int startX;
    /**
     *the y location where the platform starts
     */
    public int startY;
    /**
     *the x location the platform finishes
     */
    public int finishX;
    /**
     *the y location the platform finishes
     */
    public int finishY;

    /**
     * Constructror: creates a new platform object
     * @param startX  the x location where the platform begins
     * @param startY  the y location where the platform begins
     * @param finishX the x location where the platform ends
     * @param finishY the y location where the platform ends
     */
    public Platform(int startX, int startY, int finishX, int finishY) {
        this.startX = startX;
        this.startY = startY;
        this.finishX = finishX;
        this.finishY = finishY;
    }

    /**
     * returns true if a point is in range with the platform, false otherwise
     */
    public boolean isInRange(int locX, int locY) {
        return locX > startX && locX < finishX &&
                locY > startY && locY < finishY;
    }

    /**
     *returns true if a point is in range of the y axis
     * @param upper  the higher valued y coordinate
     * @param lower  the lower valued y coordinate
     */
    public boolean isInYRange(int upper, int lower) {
        return !((upper < startY && lower < startY) || (upper > finishY && lower > finishY));
    }

    /**
     *returns true if a point is in range of the x axis
     * @param upper  the higher valued x coordinate
     * @param lower  the lower valued x coordinate
     */
    public boolean isInXRange(int upper, int lower) {
        return !((upper < startX && lower < startX) || (upper > finishX && lower > finishX));
    }

    /**
     *returns true if the traveler is grounded
     * @param startX  the X coordinate of the traveler (leftmost)
     * @param finishX  the X coordinate of the traveler (rightmost)
     * @param upper  traveler.locationY + traveler.height
     */
    public boolean isTravelerGrounded(int startX, int finishX, int upper) {
        return upper == startY && (isInXRange(startX, startX) || isInXRange(finishX, finishX));
        //return upper == startY && (isInXRange(startX, startX) || isInXRange(finishX, finishX));
    }
    /**
     *returns true if the enemy is grounded
     * @param startX  the X coordinate of the enemy (leftmost)
     * @param finishX  the X coordinate of the enemy (rightmost)
     * @param upper  enemy.locationY + enemy.height
     */
    public boolean isEnemyGrounded(int startX, int finishX, int upper) {
        return upper == startY && (isInXRange(startX, startX) && isInXRange(finishX, finishX));
        //return upper == startY && (isInXRange(startX, startX) || isInXRange(finishX, finishX));
    }

}
