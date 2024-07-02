package edu.angelo.finalprojectbarnhill;

import java.util.List;

import android.graphics.Color;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;

/**
 * A class that presents the game screen of a platformer video game with both enemies and platforms
 * @author author of Mr. Nom game, with modifications made by Mason Barnhill
 */
public class GameScreen extends Screen {
    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }

    GameState state = GameState.Ready;
    World world;
    int oldScore = 0;
    String score = "0";

    public GameScreen(Game game) {
        super(game);
        world = new World();
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        if (state == GameState.Ready) {
            updateReady(touchEvents);
        }
        if (state == GameState.Running) {
            updateRunning(touchEvents, deltaTime);
        }
        if (state == GameState.Paused) {
            updatePaused(touchEvents);
        }
        if (state == GameState.GameOver) {
            updateGameOver(touchEvents);
        }
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        if (touchEvents.size() > 0) {
            state = GameState.Running;
        }
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i += 1) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x < 64 && event.y < 64) {
                    if (Settings.soundEnabled) {
                        //play the paused sound :)
                        Assets.paused.play(1);
                    }
                    state = GameState.Paused;
                    return;
                }
            }
            if (event.y > world.WORLD_HEIGHT) {
                if (event.x < 65 && event.type == TouchEvent.TOUCH_UP) {
                    world.traveler.jump(world.platformSet);
                }
                if (event.x > world.WORLD_WIDTH - 128 && event.x < world.WORLD_WIDTH - 64) {
                    if (event.type == TouchEvent.TOUCH_DOWN) {
                        world.traveler.velocityX = -5;
                    }
                    else if (event.type == TouchEvent.TOUCH_UP) {
                        world.traveler.velocityX = 0;
                    }
                }
                if (event.x > world.WORLD_WIDTH - 64) {
                    if (event.type == TouchEvent.TOUCH_DOWN) {
                        world.traveler.velocityX = 5;
                    }
                    else if (event.type == TouchEvent.TOUCH_UP) {
                        world.traveler.velocityX = 0;
                    }
                }
            }

            /*if (event.type == TouchEvent.TOUCH_DOWN && event.y > 63) {
                world.shoot(event.x, event.y);
            }*/

        }

        world.update(deltaTime);
        if (world.gameOver) {
            if (Settings.soundEnabled) {
                Assets.bitten.play(1);
            }
            state = GameState.GameOver;
        }
        if (oldScore != world.score) {
            int t = oldScore;//backup the old score
            oldScore = world.score;
            score = "" + oldScore;
        }
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i += 1) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x > 80 && event.x <= 240) {
                    if (event.y > 100 && event.y <= 148) {
                        if (Settings.soundEnabled) {
                            Assets.click.play(1);
                        }
                        state = GameState.Running;
                        return;
                    }
                    if (event.y > 148 && event.y < 196) {
                        if (Settings.soundEnabled) {
                            Assets.click.play(1);
                        }
                        //update the current score before
                        //resetting the game :)
                        Settings.addScore(world.whichWorld, world.score);
                        Settings.save(game.getFileIO());
                        game.setScreen(new MainMenuScreen(game));
                        return;
                    }
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i += 1) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x >= 128 && event.x <= 192 &&
                        event.y >= 200 && event.y <= 264) {
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        drawWorld(world);
        if (state == GameState.Ready) {
            drawReadyUI();
        }
        if (state == GameState.Running) {
            drawRunningUI();
        }
        if (state == GameState.Paused) {
            drawPausedUI();
        }
        if (state == GameState.GameOver) {
            drawGameOverUI();
        }

        drawText(g, score, g.getWidth() - score.length() * 20, 0);
    }

    private void drawWorld(World world) {
        Graphics g = game.getGraphics();
        //draw the sun background
        g.drawPixmap( Assets.background2, 0, 64);
        //calculate the position of the traveler
        int thirdOfScreen = World.WORLD_WIDTH / 3;
        //every 0.5 seconds, the picture for an enemy changes
        boolean isFirstHalfOfSecond = world.scoreAsFloat - (float)(int)world.scoreAsFloat <= 0.5f;
        //boolean isFirstRunFrame = ((int)(world.scoreAsFloat * 100)) % 20 < 9;
        //create a temporary traveler object
        Traveler t = world.traveler;
        /*
        Note: This is an optimization technique described on https://web.archive.org/web/20210828192644/https://developer.android.com/training/articles/perf-tips.html
        The enhanced for loop is much faster than an a 'counting' for loop in most cases.
        */

        //for each platform
        for (Platform p : world.platformSet.platforms) {
                //g.drawRect(thirdOfScreen - (t.locationX - p.startX), p.startY, p.finishX - p.startX, p.finishY - p.startY, Color.rgb(130, 0, 130));
            //draw it
            g.drawPixmap(Assets.platform, thirdOfScreen - (t.locationX - p.startX), p.startY, 0, 0, p.finishX - p.startX + 1, p.finishY - p.startY);

        }
        //for each enemy
        for (Enemy e : world.enemySet.enemies) {

            //g.drawRect(thirdOfScreen - (t.locationX - e.locationX), e.locationY, e.width, e.height, Color.rgb(250, 120, 40));
            //draw it, with the correct pixmap
            if (e.kind == 0) {
                if (e.velocityX > 0) {
                    if (isFirstHalfOfSecond) {
                        g.drawPixmap(Assets.apple, thirdOfScreen - (t.locationX - e.locationX), e.locationY);
                    }
                    else {
                        g.drawPixmap(Assets.apple2, thirdOfScreen - (t.locationX - e.locationX), e.locationY);
                    }
                }
                else {
                    if (isFirstHalfOfSecond) {
                        g.drawPixmap(Assets.appleFlipped, thirdOfScreen - (t.locationX - e.locationX), e.locationY);
                    }
                    else {
                        g.drawPixmap(Assets.apple2Flipped, thirdOfScreen - (t.locationX - e.locationX), e.locationY);
                    }
                }

            }
            else if (e.kind == 2) {
                if (e.velocityX > 0) {
                    if (isFirstHalfOfSecond) {
                        g.drawPixmap(Assets.sandwich, thirdOfScreen - (t.locationX - e.locationX), e.locationY);
                    }
                    else {
                        g.drawPixmap(Assets.sandwich2, thirdOfScreen - (t.locationX - e.locationX), e.locationY);
                    }
                }
                else {
                    if (isFirstHalfOfSecond) {
                        g.drawPixmap(Assets.sandwichFlipped, thirdOfScreen - (t.locationX - e.locationX), e.locationY);
                    }
                    else {
                        g.drawPixmap(Assets.sandwich2Flipped, thirdOfScreen - (t.locationX - e.locationX), e.locationY);
                    }
                }

            }
            else if (e.kind == 1) {
                if (e.velocityX > 0) {
                    if (isFirstHalfOfSecond) {
                        g.drawPixmap(Assets.potato, thirdOfScreen - (t.locationX - e.locationX), e.locationY);
                    }
                    else {
                        g.drawPixmap(Assets.potato2, thirdOfScreen - (t.locationX - e.locationX), e.locationY);
                    }
                }
                else {
                    if (isFirstHalfOfSecond) {
                        g.drawPixmap(Assets.potatoFlipped, thirdOfScreen - (t.locationX - e.locationX), e.locationY);
                    }
                    else {
                        g.drawPixmap(Assets.potato2Flipped, thirdOfScreen - (t.locationX - e.locationX), e.locationY);
                    }
                }

            }
            else {
                //g.drawRect(thirdOfScreen - (t.locationX - e.locationX), e.locationY, e.width, e.height, Color.rgb(250, 120, 40));
                if (e.velocityX > 0) {
                    g.drawPixmap(Assets.watermelon,thirdOfScreen - (t.locationX - e.locationX), e.locationY);
                }
                else {
                    g.drawPixmap(Assets.watermelonFlipped,thirdOfScreen - (t.locationX - e.locationX), e.locationY);
                }
            }
        }

        //if (world.cookie.locationX <= world.traveler.locationX) {
            //g.drawRect(thirdOfScreen - (t.locationX - world.cookie.locationX), world.cookie.locationY, world.cookie.width, world.cookie.height, Color.rgb(250, 250, 250));\
            //draw the cookie
            g.drawPixmap(Assets.cookie,thirdOfScreen - (t.locationX - world.cookie.locationX), world.cookie.locationY);
        //}
        /*else {
            g.drawPixmap(Assets.cookie,thirdOfScreen - (t.locationX - world.cookie.locationX), world.cookie.locationY);
            //g.drawRect(world.cookie.locationX - t.locationX + thirdOfScreen, world.cookie.locationY, world.cookie.width, world.cookie.height, Color.rgb(250, 250, 250));
        }
        */
        //this code below is to draw traveler, in place of the below line
        //g.drawRect(thirdOfScreen, world.traveler.locationY, world.traveler.width, world.traveler.height, Color.rgb(0, 200, 0));
        //draw the traveler pixmap
        if (world.traveler.velocityX >= 0) {
            if (!world.platformSet.isTravelerGrounded(world.traveler.locationX, world.traveler.locationX + world.traveler.width, world.traveler.locationY + world.traveler.height)) {
                //g(thirdOfScreen, world.traveler.locationY, world.traveler.width, world.traveler.height, Color.rgb(0, 200, 0));
                g.drawPixmap(Assets.travelerMoving, thirdOfScreen, world.traveler.locationY);
            }
            else if (world.traveler.velocityX == 0) {
                g.drawPixmap(Assets.travelerStanding, thirdOfScreen, world.traveler.locationY);
            }
            else {
                g.drawPixmap(Assets.travelerMoving, thirdOfScreen, world.traveler.locationY);
            }
        }
        else {
            if (!world.platformSet.isTravelerGrounded(world.traveler.locationX, world.traveler.locationX + world.traveler.width, world.traveler.locationY + world.traveler.height)) {
                //g(thirdOfScreen, world.traveler.locationY, world.traveler.width, world.traveler.height, Color.rgb(0, 200, 0));
                g.drawPixmap(Assets.travelerMovingFlipped, thirdOfScreen, world.traveler.locationY);
            }
            else if (world.traveler.velocityX == 0) {
                g.drawPixmap(Assets.travelerStandingFlipped, thirdOfScreen, world.traveler.locationY);
            }
            else {
                g.drawPixmap(Assets.travelerMovingFlipped, thirdOfScreen, world.traveler.locationY);
            }
        }

    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.ready, 47, 100);
        //g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.buttons, 0, 0, 64, 128, 64, 64);

        //g.drawRect(0, World.WORLD_HEIGHT, 64, 64, Color.RED);
        g.drawPixmap(Assets.upArrow, 0, World.WORLD_HEIGHT);
        //g.drawRect(World.WORLD_WIDTH - 64, World.WORLD_HEIGHT, 64, 64, Color.RED);
        g.drawPixmap(Assets.rightArrow, World.WORLD_WIDTH - 64, World.WORLD_HEIGHT - 1);
        //g.drawRect(World.WORLD_WIDTH - 128, world.WORLD_HEIGHT, 64, 64, Color.BLUE);
        g.drawPixmap(Assets.leftArrow, World.WORLD_WIDTH - 128 - 10, World.WORLD_HEIGHT);
    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.pause, 80, 100);
        //g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        if (world.whichWorld != 4) {
            g.drawPixmap(Assets.gameOver, 62, 100);
        }
        else {
            g.drawPixmap(Assets.congratsScreen, 62, 100);
        }
        g.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);
        //g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i += 1) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }

    @Override
    public void pause() {
        if (state == GameState.Running) {
            state = GameState.Paused;
        }
        //if the user exits add the score into the high scores list
        if (world.gameOver) {
            Settings.addScore(world.whichWorld, world.score);
            Settings.save(game.getFileIO());
        }
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
