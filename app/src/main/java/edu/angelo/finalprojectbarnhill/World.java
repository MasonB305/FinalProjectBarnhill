package edu.angelo.finalprojectbarnhill;
//the GameScreen.java file has the increasing efficiency and reducing power technique, specifically in drawWorld()


//This game uses lens #43 from the The Art of Game Design, a Book of Lenses, the Second Edition by Jesse Schell
//This lens is the Lens of Competition

//This is the file where I point out a principal of good game design.
//Although at first, the game may be challenging for the player to even finish,
//as the player base becomes better at the game, the primary goal will be to have
//the fastest time while beating all of the levels. This is due to the score of a player
//being calculated by which level the player has gotten too, with ties being broken by who
//has the lowest overall time in seconds during the run.


/**
 * A class that keeps track of the current game world.
 * @author Mason Barnhill
 */
public class World {

    /**
     * the cookie the traveler is trying to reach
     */
    public Cookie cookie;
    /**
     *the enemy set that is used
     */
    public EnemySet enemySet;
    /**
     *the platform set that is used
     */
    public PlatformSet platformSet;
    /**
     *the width of the world
     */
    public static final int WORLD_WIDTH = 320;
    /*
     * the height of the world
     */
    public static final int WORLD_HEIGHT = 480 - 64;
    /**
     * the traveler that travels the world
     */
    Traveler traveler;
    /**
     * the world that the traveler currently is on
     */
    public int whichWorld = 0;
    /**
     * the rate of clock ticks
     */
    static final float TICK_INITIAL = 0.05f;
    /*
     * the number of worlds in the game (the last one ends the game immediately)
     */
    public static final int NUM_WORLDS = 5;

    /**
     * true if the game is over, false otherwise
     */
    public boolean gameOver = false;
    /*
     * the score of the traver, also known as the number of seconds that has passed
     */
    public int score = 0;
    /**
     *the score, stored as a float
     */
    public float scoreAsFloat = 0.0f;
    /**
     * the tick time (number of seconds to pass)
     */
    float tickTime = 0;
    /**
     * how many ticks per second
     */
    float tick = TICK_INITIAL;
    /**
     * sets the world to hold the properties the player currently is on at the start of a level
     */
    public void loadWorld() {
        if (whichWorld == 0) {
            enemySet = new EnemySet(2);
            platformSet = new PlatformSet(2);
            platformSet.platforms[0] = new Platform(0, WORLD_HEIGHT - 40,  WORLD_WIDTH - 40, WORLD_HEIGHT);
            platformSet.platforms[1] = new Platform(90, WORLD_HEIGHT - 90, WORLD_WIDTH - 90, WORLD_HEIGHT - 70);
            enemySet.enemies[0] = new Enemy(15, WORLD_HEIGHT - 40, 0);
            enemySet.enemies[1] = new Enemy(World.WORLD_WIDTH - 70, WORLD_HEIGHT - 40, 1);
            traveler = new Traveler(110, WORLD_HEIGHT - 40);
            cookie = new Cookie(125, WORLD_HEIGHT - 90);
        }
        else if (whichWorld == 1) {
            enemySet = new EnemySet(19);
            platformSet = new PlatformSet(10);
            platformSet.platforms[0] = new Platform(0, WORLD_HEIGHT - 40,  WORLD_WIDTH - 40, WORLD_HEIGHT);
            platformSet.platforms[1] = new Platform(World.WORLD_WIDTH - 120, World.WORLD_HEIGHT - 80, World.WORLD_WIDTH - 104, World.WORLD_HEIGHT - 39);
            platformSet.platforms[2] = new Platform(330, WORLD_HEIGHT - 80, 400, WORLD_HEIGHT - 40);
            for (int i = 3; i < 9; i += 1) {
                platformSet.platforms[i] = new Platform(410 + i * 70 - 210, WORLD_HEIGHT - 30 * i, 410 + (i + 1) * 70 - 20 - 210, WORLD_HEIGHT);
                enemySet.enemies[i] = new Enemy(platformSet.platforms[i].startX, platformSet.platforms[i].startY, Enemy.random.nextInt(3));
            }
            //finishes at 410 + 9 * 70 - 230
            //=180 + 630
            //=810
            platformSet.platforms[9] = new Platform(820, WORLD_HEIGHT - 40, 1100, WORLD_HEIGHT);
            //platformSet.platforms[10] = new Platform(820, WORLD_HEIGHT / 2, 1100, WORLD_HEIGHT / 2 + 40);
            ///////

            enemySet.enemies[0] = new Enemy(World.WORLD_WIDTH - 80, WORLD_HEIGHT - 40, 0);
            enemySet.enemies[1] = new Enemy(World.WORLD_WIDTH - 80, WORLD_HEIGHT - 40, 1);
            enemySet.enemies[2] = new Enemy(335, WORLD_HEIGHT - 80, 0);

            for (int i = 9; i < 19; i += 1) {
                enemySet.enemies[i] = new Enemy(830, WORLD_HEIGHT - 40, 3);
            }
            /////////
            cookie = new Cookie(1050 , WORLD_HEIGHT - 80);
            traveler = new Traveler(110, WORLD_HEIGHT - 80);// this is for the level
            //traveler = new Traveler(830, 0, WORLD_HEIGHT - 80); // this is for debugging

        }
        else if (whichWorld == 2) {
            platformSet = new PlatformSet(1);
            enemySet = new EnemySet(1);
            platformSet.platforms[0] = new Platform(40, WORLD_HEIGHT - 200, 120, WORLD_HEIGHT - 160);
            enemySet.enemies[0] = new Enemy(80, WORLD_HEIGHT - 200, 0);
            traveler = new Traveler(50, WORLD_HEIGHT - 200);
            cookie = new Cookie(80, WORLD_HEIGHT - 40);
        }
        else if (whichWorld == 3) {
            platformSet = new PlatformSet(4);
            //enemySet = new EnemySet(20);
            enemySet = new EnemySet(30);
            for (int i = 0; i < 10; i += 1) {
                if (i < 3) {
                    enemySet.enemies[i] = new Enemy(110 + 50 * i, WORLD_HEIGHT / 3 + 25, 0);
                }
                else {
                    enemySet.enemies[i] = new Enemy(110 + 50 * i, WORLD_HEIGHT / 3 + 25, 1);
                }
                enemySet.enemies[i + 10] = new Enemy(110 + 50 * i, WORLD_HEIGHT / 3 * 2, i % 2 + 1);

                enemySet.enemies[i + 20] = new Enemy(110 + 50 * i, WORLD_HEIGHT - 40, i % 3);
                if (i % 2 == 1) {
                    enemySet.enemies[i + 20].velocityX *= -1;
                }
            }
            platformSet.platforms[0] = new Platform(0, WORLD_HEIGHT / 2, 80, WORLD_HEIGHT);
            platformSet.platforms[1] = new Platform(110, WORLD_HEIGHT / 3 + 25, 800, WORLD_HEIGHT / 3 + 25 + 40);
            platformSet.platforms[2] = new Platform(80, WORLD_HEIGHT / 3 * 2, 800, WORLD_HEIGHT / 3 * 2 + 40);
            platformSet.platforms[3] = new Platform(80, WORLD_HEIGHT - 40, 840, WORLD_HEIGHT);
            cookie = new Cookie(80, WORLD_HEIGHT - 40);
            traveler = new Traveler(0, WORLD_HEIGHT / 2);
        }
        else if (whichWorld == 4) {
            //a congrats screen is displayed
            cookie = new Cookie(0, 1000);
            platformSet = new PlatformSet(1);
            enemySet = new EnemySet(0);
            platformSet.platforms[0] = new Platform(0, WORLD_HEIGHT - 40, WORLD_WIDTH, WORLD_HEIGHT);
            traveler = new Traveler(0, 0);
            traveler.health = 0;
            gameOver = true;
        }
    }

    /**
     * Constructor: sets the current properties of the world based on which world
     */
    public World() {
        loadWorld();
    }


    /*
     * makes all relevant actions occur per tick
     */
    public void update(float deltaTime) {
        if (gameOver) {
            return;
        }

        tickTime += deltaTime;
        while (tickTime > tick) {
            scoreAsFloat += TICK_INITIAL;
            score = (int)scoreAsFloat;
            if (scoreAsFloat > 1000.0f) {
                scoreAsFloat = 1000.0f;
            }
            tickTime -= tick;
            //targetSet.advance(); // changed snake to targetSet
            enemySet.advance(platformSet);
            traveler.advance(platformSet);
            cookie.interactWithTraveler(traveler, this);
            if (enemySet.isTravelerInRange(traveler)) {
                traveler.health -= 1;
            }
            if (traveler.health == 0 || traveler.locationY > World.WORLD_HEIGHT + 80) {
                gameOver = true;
            }

        }
    }
}

