package edu.angelo.finalprojectbarnhill;

/**
 * A class that keeps track of a set of enemies in a platformer video game.
 * @author Mason Barnhill
 */
public class EnemySet {
    /**
    * The number of different kinds of enemies. The fifth one was unused.
     */
    final static int numKinds = 5;
    /**
     *The number of enemies in an EnemySet object
     */
    public int numEnemies;
    /**
     *The array where a collection of enemies is stored
     */
    public Enemy[] enemies;
    /**
     * Constructor: creates a new enemySet with N elements
     * @param n   the number of elements to be created
     */
    public EnemySet(int n) {
        numEnemies = n;
        enemies = new Enemy[n];
    }
    /**
     *Constructor: creates a new enemy set with 10 elements
     */
    public EnemySet() {
        this(10);
    }
    /**
     * A class that keeps track of the current game world.
     * @param platformSet  the set of platforms the enemies will interact with
     */
    public void advance(PlatformSet platformSet) {
        for (Enemy enemy: enemies) {
            enemy.advance(platformSet);
        }
    }

    /**
     *Returns true if a point of the player is in range of the set of enemeis
     * @param playerLocationX   the x coordinate of the player
     * #param playerLocationY   the y coordinate of the player
     */
    public boolean isInRange(int playerLocationX, int playerLocationY) {
        for (Enemy e : enemies) {
            if (isInRange(playerLocationX, playerLocationY)) {
                return true;
            }
        }
        return false;
    }

    /**
     * returns true if the traveler is in range of the enemy set.
     * Note: if the traveler jumps on an enemies head, the enemy will
     * be removed from the enemy set and the traveler will bounce slightly.
     * @param  traveler  the traveler that will be interacting with the enemy set
     */
    public boolean isTravelerInRange(Traveler traveler) {
        //ArrayList<Integer> list;
        int i = 0;
        for (Enemy enemy : enemies) {
            if (enemy.isTravelerInRange(traveler)) {
                //if (traveler.velocityY > 2 && enemy.kind != 4 && (traveler.locationY + traveler.height <= enemy.locationY + enemy.height / 2 || enemy.kind == 2)) {
                if (traveler.velocityY > 2 && enemy.kind != 4 && (traveler.locationY + traveler.height - traveler.velocityY <= enemy.locationY + enemy.height / 3)) {
                    enemy.health -= 1;
                    traveler.velocityY = -6;
                    traveler.locationY = enemy.locationY - traveler.height;
                    //traveler.locationY = enemy.locationY - 1;
                    Enemy[] newEnemies = new Enemy[numEnemies - 1];
                    int tempPad = 0;
                    if (enemy.health == 0) {
                        //play the hit sound
                        if (Settings.soundEnabled) {
                            Assets.hit.play(1);
                        }
                        for (int j = 0; j < numEnemies - 1; j += 1) {
                            if (j == i) {
                                tempPad = 1;
                            }

                            newEnemies[j] = enemies[j + tempPad];
                        }
                        enemies = newEnemies;
                        numEnemies -= 1;
                    }
                    return false;
                }
                return true;
            }
            i += 1;
        }
        return false;
    }
}
