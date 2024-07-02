package edu.angelo.finalprojectbarnhill;

public class Cookie {
    /**
     * The height of the traveler
     */
    public int height = 25;
    /**
     *the width of the traveler
     */
    public int width = 25;
    /**
     * the x location of the traveler
     */
    public int locationX;
    /**
     * the y location of the traveler
     */
    public int locationY;

    /**
     *returns true if a traveler point is intersecting with the cookie
     * @param  playerLocX   the X location of the player
     * @param  playerLocY   the Y location of the player
     */
    public boolean isTravelerPointInRange(int playerLocX, int playerLocY) {
        return playerLocX > locationX && playerLocX < locationX + width &&
                playerLocY > locationY && playerLocY < locationY + height;
    }

    /**
     * Return true if the traveler is intersecting the cookie
     * @param traveler  the traveler object that will be checked
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

    /**
     * Constructor: a 2 parameter constructor that gives the cookie a locationX and a locationY
     * @Param  locationX   the X location of the cookie\
     * @Param  locationY   the Y location of the cookie
     */
    public Cookie (int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY - height;
    }

    /**
     * checks if the cookie is in range of the traveler and shoots them into the
     * next world if they are
     * @param traveler   the Traveler object being checked
     * @param world   the world that is updated if the traveler is in range of the cookie
     */
    public void interactWithTraveler(Traveler traveler, World world) {
        if (isTravelerInRange(traveler)) {
            if (world.whichWorld + 1 < World.NUM_WORLDS) {
                world.whichWorld += 1;
            }
            world.loadWorld();
        }
    }
}
