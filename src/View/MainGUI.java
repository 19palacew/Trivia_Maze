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
    private JPopupMenu fileMenu;
    private JMenuItem menuItemNewGame, menuItemLoadGame, menuItemExitGame;
    private final PlayScreen playScreen;

    public MainGUI(final PlayScreen thePlayScreen){
        setTitle("Trivia Maze");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(720, 560);
        InitializeElements();
        playScreen = thePlayScreen;
        setContentPane(BasePanel);
        setVisible(true);
        fileButton.addActionListener(e -> {
            ToolBar.add(fileMenu);
            fileMenu.show(fileMenu, 0, 30);
            //MazeDisplay.add(playScreen.getBackground());
            setVisible(true);
        });
        menuItemNewGame.addActionListener(e -> {
            System.out.println("asfsf");
        });
    }

    private void InitializeElements(){
        fileMenu = new JPopupMenu();
        menuItemNewGame = new JMenuItem("New Game");
        fileMenu.add(menuItemNewGame);
        menuItemLoadGame = new JMenuItem("Load Game");
        fileMenu.add(menuItemLoadGame);
        menuItemExitGame = new JMenuItem("Exit Game");
        fileMenu.add(menuItemExitGame);
    }
}
