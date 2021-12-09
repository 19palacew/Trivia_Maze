package Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;

/**
 * SoundPlay class allows a wav file to be input into the
 * playSound method and the method will play the sound.
 */
public class SoundPlay {

    /**
     * Plays a sound to the system.
     * @param theChosenFile the wav file selected by the program
     */
    public static void playSound(final File theChosenFile) {
        try {
            AudioInputStream audioInput =
                    AudioSystem.getAudioInputStream(theChosenFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException
                | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
