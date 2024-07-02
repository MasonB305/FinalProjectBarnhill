package edu.angelo.finalprojectbarnhill;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("background.png", PixmapFormat.ARGB8888);
        Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB8888);
        Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB8888);
        Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB8888);
        Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB8888);
        Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB8888);
        Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB8888);
        Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB8888);
        Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB8888);
        Assets.pause = g.newPixmap("pausemenu.png", PixmapFormat.ARGB8888);
        Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB8888);
        Assets.hit = game.getAudio().newSound("hit.ogg");
        Assets.click = game.getAudio().newSound("click.ogg");
        //Assets.eat = game.getAudio().newSound("eat.ogg");
        Assets.bitten = game.getAudio().newSound("gameover.ogg");

        Assets.paused = game.getAudio().newSound("paused.ogg");
        Settings.load(game.getFileIO());
        Assets.background2 = g.newPixmap("funinthesun.png", PixmapFormat.ARGB8888);
        Assets.apple2Flipped = g.newPixmap("apple2_flipped.png", PixmapFormat.ARGB8888);
        Assets.appleFlipped = g.newPixmap("apple_flipped.png", PixmapFormat.ARGB8888);
        Assets.cookie = g.newPixmap("cookie.png", PixmapFormat.ARGB8888);
        Assets.leftArrow = g.newPixmap("larrow.png", PixmapFormat.ARGB8888);
        Assets.rightArrow = g.newPixmap("rarrow.png", PixmapFormat.ARGB8888);
        Assets.upArrow = g.newPixmap("uparrow.png", PixmapFormat.ARGB8888);
        Assets.apple = g.newPixmap("apple.png", PixmapFormat.ARGB8888);
        Assets.apple2 = g.newPixmap("apple2.png", PixmapFormat.ARGB8888);
        Assets.sandwich = g.newPixmap("sandwich.png", PixmapFormat.ARGB8888);
        Assets.sandwich2 = g.newPixmap("sandwich2.png", PixmapFormat.ARGB8888);
        Assets.sandwichFlipped = g.newPixmap("sandwich_flipped.png", PixmapFormat.ARGB8888);
        Assets.sandwich2Flipped = g.newPixmap("sandwich2_flipped.png", PixmapFormat.ARGB8888);

        Assets.potato = g.newPixmap("potato.png", PixmapFormat.ARGB8888);
        Assets.potato2 = g.newPixmap("potato2.png", PixmapFormat.ARGB8888);
        Assets.potatoFlipped = g.newPixmap("potato_flipped.png", PixmapFormat.ARGB8888);
        Assets.potato2Flipped = g.newPixmap("potato2_flipped.png", PixmapFormat.ARGB8888);
        Assets.travelerMoving = g.newPixmap("redtravelermoving.png", PixmapFormat.ARGB8888);
        Assets.travelerStanding = g.newPixmap("redtravelerstanding.png", PixmapFormat.ARGB8888);
        Assets.travelerMovingFlipped = g.newPixmap("redtravelermovingflipped.png", PixmapFormat.ARGB8888);
        Assets.travelerStandingFlipped = g.newPixmap("redtravelerstandingflipped.png", PixmapFormat.ARGB8888);
        Assets.congratsScreen = g.newPixmap("congrats.png", PixmapFormat.ARGB8888);
        Assets.platform = g.newPixmap("platform.png", PixmapFormat.ARGB8888);
        Assets.categories = g.newPixmap("categories.png", PixmapFormat.ARGB8888);
        Assets.watermelon = g.newPixmap("watermelon.png", PixmapFormat.ARGB8888);
        Assets.watermelonFlipped = g.newPixmap("watermelonflipped.png", PixmapFormat.ARGB8888);
        Assets.credits = g.newPixmap("credits.png", PixmapFormat.ARGB8888);
        game.setScreen(new MainMenuScreen(game));
    }

    public void present(float deltaTime) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
    }
}
