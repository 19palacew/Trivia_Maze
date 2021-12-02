package View;

import javax.swing.*;

public class PlayScreen{
    private JPanel background;
    private JLabel mazeView;

    public PlayScreen(){
        background = new JPanel();
        mazeView = new JLabel("How to Play");
        mazeView.setIcon(new ImageIcon("src/View/TempMaze.png"));

    }

    public JPanel getBackground() {
        return background;
    }
}
