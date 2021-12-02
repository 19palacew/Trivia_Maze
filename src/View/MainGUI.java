package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame{
    private JButton fileButton;
    private JButton helpButton;
    private JPanel BasePanel;
    private JToolBar ToolBar;
    private JPanel MazeDisplay;
    private PlayScreen playScreen;

    public MainGUI(){
        setTitle("Trivia Maze");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(720, 560);
        setContentPane(BasePanel);
        setVisible(true);
        playScreen = new PlayScreen();
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MazeDisplay.add(playScreen.getBackground());
                setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        MainGUI gui = new MainGUI();
    }
}
