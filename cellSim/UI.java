package cellSim;
import javax.swing.*;
import java.awt.*;

public class UI extends JPanel{

        // Create panels for the Model and the View
    JPanel panel, panel2;
    JTextField textField, textField2;

    //Buttons
    // Control Panel buttons
    JButton playButton = new JButton("PLAY");
    JButton nextButton = new JButton("NEXT STEP");
    JButton resetButton = new JButton("RESET");
    JButton displayButton = new JButton("DISPLAY BUTTON");

    //Edit Panel
    /**
     * hunger decay per turn - Int from 1 to 100
     * Rate of food generation - double from 0 to 1
     * rate of new life generation - double from 0 to 1
     * health per food - Int from 1 to 100
     * initial cells - int from 0 to 10
     * initial food - int from 0 to 10
     */
    String[] lifeString = {"Cell", "Fish", "Human", "Martian", "Orc"};
    JComboBox lifeList = new JComboBox(lifeString);
    JTextField hungerDecay = new JTextField("hunger decay per turn (1-100): ");
    JTextField hungerDecayInput = new JTextField("0");
    JTextField foodGen = new JTextField("food generation per turn (0-1): ");
    JTextField foodGenInput = new JTextField("0.5");
    JTextField lifeGen = new JTextField("life generation per turn (0-1): ");
    JTextField lifeGenInput = new JTextField("0.5");
    JTextField foodRegen = new JTextField("health per food (0-100): ");
    JTextField foodRegenInput = new JTextField("20");
    JTextField initialCells = new JTextField("initial cells (0-10): ");
    JTextField initialCellsInput = new JTextField("5");
    JTextField initialFood = new JTextField("initial food (0-10): ");
    JTextField initialFoodInput = new JTextField("3");


        //constructor
        public UI() {
            hungerDecay.setEditable(false);

            JTextField textField2 = new JTextField("0");
            JTextField textField4 = new JTextField("Placeholder");
            this.setLayout(new BorderLayout());
            this.setBackground(Color.ORANGE);
            this.setSize(300, 300);
            this.add(textField4, BorderLayout.CENTER);

            panel = new JPanel();
            panel2 = new JPanel();
            this.add(panel, BorderLayout.WEST);

            // Edit Panel
            panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
            panel.add(lifeList);
            panel.add(hungerDecay);/**
            panel.add(foodGen);
            panel.add(lifeGen);
            panel.add(foodRegen);
            panel.add(initialCells);
            panel.add(initialFood);
**/

            panel.add(textField2);
            panel.setSize(250, 250);
            panel.setBackground(Color.LIGHT_GRAY);



            // This button is only shown when the the playButton is hidden.
            JButton pauseButton = new JButton("PAUSE");


            //Bottom Panel (Control Panel?)
            this.add(panel2, BorderLayout.SOUTH); //Adds the panel and sets panel location
            panel2.setBackground(Color.PINK);
            addButtons(panel2);
            panel2.setSize(250, 250);
            /**
             * panel2.add(getGrid);
             *
             */


        };

    public void addButtons(JPanel panel){
        panel.add(playButton);
        panel.add(nextButton);
        panel.add(resetButton);
        panel.add(displayButton);
    }

    /**
     * pause: pauses the simulation

    public void pause(){
        pause the simulation
        remove the pause button
        add the play button
    }
*/

/**
    public void paintComponent(Graphics g) {
        // This method is needed to draw something on JPanel
        super.paintComponent(g);
 }
**/


        public static void main(String[] args){
            JFrame frame = new JFrame("Life Simulator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new UI());
            frame.setSize(800,500);
            frame.setVisible(true);

        }


    }


