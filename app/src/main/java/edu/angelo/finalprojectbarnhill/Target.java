package edu.angelo.finalprojectbarnhill;

import java.util.Random;

/*
 * provides the properties of a target and the functions needed to maintain the target
 * @Author  Mason Barnhill
 */
public class Target {
    /**
     * A Random object to reuse.
     */
    private static final Random random = new Random();

    /**
     * The farthest left the center of a target can go, leaving room for the radius of the target.
     */
    public static final int minX = 16;

    /**
     * The farthest right the center of a target can go, leaving room for the radius of the target.
     */
    public static final int maxX = World.WORLD_WIDTH - 16;

    /**
     * The farthest up the center of a target can go, leaving room at the top of the screen for
     * the pause button and score and for the radius of the target.
     */
    public static final int minY = 64 + 16;

    /**
     * The farthest down the center of a target can go, leaving room for the radius of the target.
     */
    public static final int maxY = World.WORLD_HEIGHT - 16;

    /**
     * The X coordinate of the target
     */
    public  int locationX;

    /**
     * The Y coordinate of the target
     */
    public  int locationY;

    /**
     * The velocity of the target moving along the X coordinate
     */
    public int velocityX;

    /**
     * The velocity of the target moving along the Y coordinate
     */
    public int velocityY;

    /*
     * advance uses the current velocity of the target to properly adjust the x and y
     * locations on the screen
     */
    public void advance() {
        int resultX = locationX + velocityX;
        int resultY = locationY + velocityY;
        int diffX = maxX - resultX;
        int diffY = maxY - resultY;
        if (diffX < 0) {
            velocityX *= -1;
            resultX += 2 * diffX;
        }

        if (diffY < 0) {
            velocityY *= -1;
            resultY += 2 * diffY;
        }
        if (resultX < minX) {
            velocityX *= -1;
            resultX = minX + 2 * (minX - resultX);
        }
        if (resultY < minY) {//note: added 16 to make code work :-0
            velocityY *= -1;
            resultY = minY + 2 * (minY - resultY);
        }
        locationX = resultX;
        locationY = resultY;
    }

    /*
     * generates a new velocity and location within the expected ranges
     */
    public void randomize() {
        velocityY = -10 + random.nextInt(21);//randomInt(-10, 10);
        velocityX = -10 + random.nextInt(21);//randomInt(-10, 10);
        locationX = minX + random.nextInt(maxX - minX + 1);//randomInt(minX, maxX);
        locationY = minY + random.nextInt(maxY - minY + 1);//randomInt(minY, maxY);
    }

    /*
     * creates an instance of a Target object
    */
    public Target() {
        randomize(); // :) ok
    }
}