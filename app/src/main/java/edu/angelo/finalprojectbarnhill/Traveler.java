package edu.angelo.finalprojectbarnhill;

/**
 * A class that keeps track of a traveler in a platformer video game
 * @author Mason Barnhill
 */
public class Traveler {
    /**
    * the amount of health the traveler has
     */
    public int health;
    /**
     *the x location of the traveler
     */
    public int locationX;
    /**
     *the y location of the traveler
     */
    public int locationY;
    /*
     *the x velocity of the traveler
     */
    public int velocityX;
    /**
     *the y velocity of the traveler
     */
    public int velocityY;
    /**
     *the width of the traveler
     */
    public final int width = 13;
    /*
     * height of the traveler
     */
    public final int height = 15;
    /**
     * the effect of gravity on the traveler
     */
    final public int gravity = 2;

    /**
     * Constructor: creates a new traveler object
     * @param locationX  the x location of the traveler
     * @param locationY  the y location of the traveler
     * @param health how much health the traveler will have
     */
    public Traveler(int locationX, int locationY, int health) {
        this.locationX = locationX;
        this.locationY = locationY - height;
        this.velocityX = 0;
        this.velocityY = 0;
        this.health = health;
    }
    /**
     * Constructor: creates a new traveler object with 1 health
     * @param locationX  the x location of the traveler
     * @param locationY  the y location of the traveler
     */
    public Traveler(int locationX, int locationY) {
        this(locationX, locationY, 1);
    }
    /*
     * returns true if any corner of the traveler is in range of a given platform set
     * @param platforrmSet  the PlatformSet being interacted with
     * @param displacementX  a change in x, usually used to test moves in advance
     * @param displacementY a change in y, usually used to test moves in advanve
     */
    public boolean isAnyCornerInRange(PlatformSet platformSet, int displacementX, int displacementY) {
        return platformSet.isInRange(locationX + displacementX, locationY + displacementY) || platformSet.isInRange(locationX + displacementX, locationY + displacementY + height) || platformSet.isInRange(locationX + displacementX + width, locationY + displacementY) || platformSet.isInRange(locationX + displacementX + width, locationY + displacementY + height);
    }
    /**
     * if the traveler is grounded.
     * modify their velocity to make the traveler appear to be jumping
     * @param platformSet the platform set used to check if traveler is grounded
     */
    public void jump(PlatformSet platformSet) {
        if (platformSet.isTravelerGrounded(locationX, locationX + width, locationY + height)) {
            velocityY = -16;
        }
    }

    /**
     *advances the traveler according to their xVelocity and yVelocity
     * @param platformSet   that interacts with traveler
     */
    public void advance (PlatformSet platformSet) {
        locationY += velocityY;
        velocityY += gravity;
        locationX += velocityX;
        int displacementX = 0;
        int displacementY = 0;
        int dirX, dirY;
        if (velocityX < 0) {
            dirX = 1;
        }
        else if (velocityX == 0) {
            dirX = 0;
        }
        else {
            dirX = -1;
        }

        if (velocityY - gravity < 0) {
            dirY = 1;
        }
        else if (velocityY - gravity == 0) {
            dirY = 0;
        }
        else {
            dirY = -1;
        }
        while (isAnyCornerInRange(platformSet, displacementX, displacementY)) {
            displacementX += dirX;
            displacementY += dirY;
            if (dirY == -1) {
                velocityY = 0;
            }
        }
        if (!isAnyCornerInRange(platformSet, displacementX, 0)) {
            locationX = locationX + displacementX;
        }
        else if (!isAnyCornerInRange(platformSet, 0, displacementY)) {
            locationY = locationY + displacementY;
        }
        else {
            locationX = locationX + displacementX;
            locationY = locationY + displacementY;
        }

    }

}
