import javax.swing.*;

public class MainGUI extends JFrame{
    private JButton fileButton;
    private JButton helpButton;
    private JPanel BasePanel;
    private JToolBar ToolBar;
    private JPanel MazeDisplay;
    private JPanel StartPanel;

    public MainGUI(){
        setTitle("Trivia Maze");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(720, 560);
        setContentPane(BasePanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainGUI gui = new MainGUI();
    }
}
