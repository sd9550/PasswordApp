import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    public App() {
        add(new Board());
        setTitle("PasswordApp");
        setSize(Board.WIDTH, Board.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var app = new App();
            app.setResizable(false);
            app.setVisible(true);
        });
    }
}
