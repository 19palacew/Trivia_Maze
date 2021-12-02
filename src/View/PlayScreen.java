package View;

import javax.swing.*;

public class PlayScreen{
    private JPanel Background;
    private JLabel MazeView;

    public PlayScreen(){
        MazeView.setIcon(new ImageIcon("src/View/TempMaze.png"));
    }

    public JPanel getBackground() {
        return Background;
    }
}
