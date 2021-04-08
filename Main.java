import javax.swing.*;

public class Main {

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Brick Breaker v0.2");
        GameManager manager = new GameManager();
        manager.setSize(1000,800);
        frame.setSize(1000,800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(manager);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
