package Model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * SoundPlay class allows a a wav file to be input into the
 * playSound method and the method will play the sound
 */
public class SoundPlay {

    /**
     * Plays a sound to the system
     * @param theChosenFile the wav file selected by the program
     */
    public static void PlaySound(final File theChosenFile) {
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(theChosenFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException
                | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
