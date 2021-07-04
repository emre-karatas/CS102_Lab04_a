import javax.swing.*;

/**
 * PotLuck test class.
 * @author Emre Karatas-22001641
 * @version v1.0 4.7.2021
 */
public class PotLuckTester
{
    public static void main( String[] args)
    {
        JFrame game = new PotLuckFrame();
        game.setTitle("PotLuck Game");
        game.setVisible(true);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
