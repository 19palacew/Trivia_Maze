package View;

import javax.swing.*;

public class MainGUI extends JFrame{
    private JButton fileButton;
    private JButton helpButton;
    private JPanel basePanel;
    private JToolBar ToolBar;
    private JPanel MazeDisplay;
    private JPopupMenu fileMenu, helpMenu;
    private JMenuItem menuItemNewGame, menuItemLoadGame, menuItemExitGame, menuItemAbout, menuItemInstructions, menuItemCheats;
    private final PlayScreen playScreen;

    public MainGUI(){
        setTitle("Trivia Maze");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(720, 560);
        InitializeElements();
        playScreen = new PlayScreen();
        setContentPane(basePanel);
        MazeDisplay.add(new StartScreen());
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
        helpButton.addActionListener(e -> {
            ToolBar.add(helpMenu);
            helpMenu.show(helpMenu, 50, 30);
            setVisible(true);
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
        helpMenu = new JPopupMenu();
        menuItemAbout = new JMenuItem("About");
        helpMenu.add(menuItemAbout);
        menuItemInstructions = new JMenuItem("Instructions");
        helpMenu.add(menuItemInstructions);
        menuItemCheats = new JMenuItem("Cheats");
        helpMenu.add(menuItemCheats);
    }
}
