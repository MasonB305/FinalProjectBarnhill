package edu.angelo.finalprojectbarnhill;

import com.badlogic.androidgames.framework.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * A class that keeps track of settings in a game
 * @author the author of the Mr. Nom game, Mason Barnhill made a few adgustments
 */
public class Settings {
    public static boolean soundEnabled = true;
    public static IntPair[] highscores = new IntPair[] {new IntPair(), new IntPair(), new IntPair(), new IntPair(), new IntPair()};

    public static void load(FileIO files) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    files.readFile(".traveler")));
            soundEnabled = Boolean.parseBoolean(in.readLine());
            for (int i = 0; i < 5; i += 1) {
                highscores[i] = IntPair.parseIntPair(in.readLine());
            }
        } catch (IOException e) {
            // :( It's ok we have defaults
        } catch (NumberFormatException e) {
            // :/ It's ok, defaults save our day
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static void save(FileIO files) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    files.writeFile(".traveler")));
            //make the game over saves the sound setting properly
            out.write(Boolean.toString(soundEnabled) + "\n");
            for (int i = 0; i < 5; i += 1) {
                //make sure the high scores are properly saved
                out.write(IntPair.toString(highscores[i]) + "\n");
            }
        } catch (IOException e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
            }
        }
    }

    /*public static void addScore(int score) {
        for (int i = 0; i < 5; i += 1) {
            if (highscores[i] > score) {
                for (int j = 4; j > i; j -= 1) {
                    highscores[j] = highscores[j - 1];
                }
                highscores[i] = score;
                return;
            }
        }
    }*/

    public static void addScore(int world, int score) {
        IntPair currentIntPair = new IntPair(world, score);
        for (int i = 0; i < 5; i += 1) {
            if (highscores[i].lessThan(currentIntPair)) {
                for (int j = 4; j > i; j -= 1) {
                    highscores[j] = highscores[j - 1];
                }
                highscores[i] = currentIntPair;
                return;
            }
        }
    }

}
