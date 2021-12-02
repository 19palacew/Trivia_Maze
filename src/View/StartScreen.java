package View;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JPanel {
    private static final Color BACKGROUND_COLOR = new Color(71,31,29);
    private static final Color TEXT_COLOR = new Color(204,204,204);

    public StartScreen(){
        JLabel howToPlay = new JLabel("Go", JLabel.CENTER);
        howToPlay.setForeground(TEXT_COLOR);
        howToPlay.setFont(new Font("src/View/Font/Roboto-Medium.ttf", Font.PLAIN, 30));
        JButton newGame = new JButton("New Game");


        add(howToPlay);
        add(newGame);
        setBackground(BACKGROUND_COLOR);
    }
}
