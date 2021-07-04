
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * PotLuck frame creator class.
 * @author Emre Karatas-22001641
 * @version v1.0 4.7.2021
 */
public class PotLuckFrame extends JFrame
{

    //VARIABLES
    private JPanel statusPanel;
    private JLabel status;
    private JPanel buttonPanel;
    JButton[] buttons;


    boolean gameOver = false;


    private int bombIndex1;
    private int bombIndex2;
    private int prizeIndex;


    private int count;

    //CONSTANTS
    private final int ROWS = 4;
    private final int COLUMNS = 4;
    private final int WIDTH = 600;
    private final int HEIGHT = 800;

    //CONSTRUCTOR
    public PotLuckFrame()
    {
        count = 0;
        createButtons();
        createLabelPanel();

        setLayout(new BorderLayout());
        add(status,BorderLayout.NORTH);
        add(buttonPanel,BorderLayout.CENTER);

        setSize(new Dimension(WIDTH,HEIGHT));

    }

    /**
     * Creating buttons
     */
    public void createButtons()
    {
        buttonPanel = new JPanel( new GridLayout(ROWS,COLUMNS));
        buttons = new JButton[ROWS*COLUMNS];
        //Selecting random indexes
        do {
            bombIndex1 = (int) (Math.random() * (ROWS * COLUMNS));
            bombIndex2 = (int) (Math.random() * (ROWS * COLUMNS));
            prizeIndex = (int) (Math.random() * (ROWS * COLUMNS));
        } while ( bombIndex1 == bombIndex2 || bombIndex1 == prizeIndex || bombIndex2 == prizeIndex);

        // creating buttons
        for ( int i = 0; i < ROWS*COLUMNS; i++)
        {
            if ( i == bombIndex1 || i == bombIndex2)
            {
                buttons[i] = new JButton("" + (i + 1));
                buttonPanel.add(buttons[i]);
                buttons[i].addActionListener( new BombButtonListener() );

            }
            else if ( i == prizeIndex)
            {
                buttons[i] = new JButton("" + (i + 1));
                buttonPanel.add(buttons[i]);
                buttons[i].addActionListener(new PrizeButtonListener());
            }
            else
            {
                buttons[i] = new JButton("" + (i + 1));
                buttonPanel.add(buttons[i]);
                buttons[i].addActionListener(new RegularButtonListener());
            }
            buttons[i].setFont(new Font("Comic Sans", Font.BOLD,20));
        }
    }

    /**
     * Creating labelPanel
     */
    public void createLabelPanel()
    {
        statusPanel = new JPanel();
        getContentPane().setBackground(Color.cyan);
        status = new JLabel("Number of guesses so far "+ count , SwingConstants.CENTER);
        status.setFont(new Font("Comic Sans", Font.PLAIN, 30));
        statusPanel.add(status);

    }


    /**
     * ActionListener class for regular buttons
     */
    public class RegularButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            count++;
            status.setText("Number of guesses so far " + count );
            ((JButton) event.getSource()).setEnabled(false);

        }
    }

    /**
     * ActionListener class for bomb buttons
     */
    public class BombButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if ( !gameOver)
            {
                count++;
                status.setText("You have blown at attempt " + count );
                disableRegularButtons();
                gameOver = true;
            }

        }
    }

    /**
     * ActionListener class for PrizeButton Listeners
     */
    public class PrizeButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if ( !gameOver)
            {
                count++;
                status.setText("You got at attempt " + count);
                disableRegularButtons();
                gameOver = true;
            }

        }
    }

    /**
     * This method enables us do disable regular buttons and show BOMB and PRIZE buttons
     */
    private void disableRegularButtons()
    {
        for ( int i = 0; i < buttons.length; i++)
        {
           if ( i != bombIndex1 && i!= bombIndex2 && i!= prizeIndex)
           {
                buttons[i].setEnabled(false);
           }
           else if ( i == bombIndex1 || i == bombIndex2)
           {
               buttons[i].setText("BOMB");
               buttons[i].setFont(new Font( "Comic Sans", Font.BOLD,30));
               buttons[i].setBackground(Color.RED);
           }
           else
           {
               buttons[i].setText("PRIZE");
               buttons[i].setFont(new Font( "Comic Sans", Font.BOLD,30));
               buttons[i].setBackground(Color.green);
           }
        }
    }



}
