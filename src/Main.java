import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setName("2d Game");

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}