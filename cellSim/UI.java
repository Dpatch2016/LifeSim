package cellSim;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JPanel implements ActionListener {

    // Create panels for the Model and the View
    JPanel panel, panel2;
    JTextField textField, textField2;

    //Buttons
    // Control Panel buttons
        JButton playButton = new JButton("PLAY");
        JButton nextButton = new JButton("NEXT STEP");
        JButton resetButton = new JButton("RESET");
        JButton displayButton = new JButton("DISPLAY STATS");
        // This button is only shown when the the playButton is hidden.
        JButton pauseButton = new JButton("PAUSE");

    //Variables
    int hungerDecayVal = 0; // from 1 to 100
    double foodGeneration = 0; // from 0 to 1
    double lifeGeneration = 0; // from 0 to 1
    int healthFromFood = 0; // from 1 to 100
    int initialCellsVal = 0; // from 0 to 10
    int initialFoodVal = 0; // form 0 to 10

    //TextField Variables
    JTextField hungerDecayInput = new JTextField("0");
    JTextField foodGenInput = new JTextField("0.5");
    JTextField lifeGenInput = new JTextField("0.5");
    JTextField foodRegenInput = new JTextField("20");
    JTextField initialCellsInput = new JTextField("5");
    JTextField initialFoodInput = new JTextField("3");

    //Buttons & TextFields
    JButton submitButton = new JButton("Submit");

    //constructor
    public UI() {
        createEditVariables();
    };

    public void createEditVariables(){
        String[] lifeString = {"Cell", "Fish", "Human", "Martian", "Orc"};
        JComboBox lifeList = new JComboBox(lifeString);

        JTextField hungerDecay = new JTextField("hunger decay per turn (1-100): ");
        hungerDecay.setEditable(false);
        JTextField foodGen = new JTextField("food generation per turn (0-1): ");
        foodGen.setEditable(false);
        JTextField lifeGen = new JTextField("life generation per turn (0-1): ");
        lifeGen.setEditable(false);
        JTextField foodRegen = new JTextField("health per food (0-100): ");
        foodRegen.setEditable(false);
        JTextField initialCells = new JTextField("initial cells (0-10): ");
        initialCells.setEditable(false);
        JTextField initialFood = new JTextField("initial food (0-10): ");
        initialFood.setEditable(false);

        JTextField textField2 = new JTextField("0");
        JTextField textField4 = new JTextField("Grid Placeholder");
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
        panel.add(hungerDecay);
        panel.add(hungerDecayInput);
        panel.add(submitButton);/**
         panel.add(foodGen);
         panel.add(lifeGen);
         panel.add(foodRegen);
         panel.add(initialCells);
         panel.add(initialFood);
         **/

        panel.add(hungerDecayInput);
        panel.setSize(250, 250);
        panel.setBackground(Color.LIGHT_GRAY);

        //add event listener to SubmitButton
        buttonProperties();





        //Bottom Panel (Control Panel?)
        this.add(panel2, BorderLayout.SOUTH); //Adds the panel and sets panel location
        panel2.setBackground(Color.PINK);
        addButtonsToControlPanel(panel2);
        panel2.setSize(250, 250);
        /**
         * panel2.add(getGrid);
         *
         */

    }


    public void addButtonsToControlPanel(JPanel panel){
        panel.add(playButton);
        panel.add(nextButton);
        panel.add(resetButton);
        panel.add(displayButton);
    }




    /**
     * Stores the value of the TextField into the class variables
     * These variables will be accessed by other functions which will
     *  1. Display the Grid
     *  2. Simulate the Grid
     */
    public void getVariables(){
        hungerDecayVal = Integer.parseInt(hungerDecayInput.getText()); // from 1 to 100
        foodGeneration = Double.parseDouble(foodGenInput.getText()); // from 0 to 1
        lifeGeneration = Double.parseDouble(lifeGenInput.getText()); // from 0 to 1
        healthFromFood = Integer.parseInt(foodRegenInput.getText()); // from 1 to 100
        initialCellsVal = Integer.parseInt(initialCellsInput.getText()); // from 0 to 10
        initialFoodVal = Integer.parseInt(initialFoodInput.getText()); // form 0 to 10

    }

    /**
     * @inputValidation() Makes sure the variables are within the defined bounds.
     * @return true if variables are within the defined bounds, false otherwise.
     */
        public Boolean inputValidation(){
            // Make sure the Edit Screen variables are within specified bounds.
            if(Integer.parseInt(hungerDecayInput.getText()) >= 1 && Integer.parseInt(hungerDecayInput.getText()) <= 100){
                // from 1 to 100
                return false;
            }
            else if(Double.parseDouble(foodGenInput.getText()) >= 0 && Double.parseDouble(foodGenInput.getText()) <= 1){
                // from 0 to 1
                return false;
            }
            else if(Double.parseDouble(lifeGenInput.getText()) >= 0 && Double.parseDouble(lifeGenInput.getText()) <= 1){
                // from 0 to 10
                return false;
            }
            else if (Integer.parseInt(foodRegenInput.getText()) >= 1 && Integer.parseInt(foodRegenInput.getText()) <= 100){
                // form 0 to 10
                return false;
            }
            else if (Integer.parseInt(initialCellsInput.getText()) >= 0 && Integer.parseInt(initialCellsInput.getText()) <= 10){
                //from 0 to 10
                return false;
            }
            else if (Integer.parseInt(initialFoodInput.getText()) >= 0 && Integer.parseInt(initialFoodInput.getText()) <= 10){
                //from 0 to 10
                return false;
            }
            else
                return true;
        }

    /**
     * Attaches an event listener to the submit button in the EditVariables
     */
    public void buttonProperties(){
        submitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.setBackground(Color.BLUE); //filler
        inputValidation();
        getVariables();
        System.out.println("hungerDecayVal = " + hungerDecayVal);
        System.out.println("foodGeneration = " + foodGeneration);

    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Life Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new UI());
        frame.setSize(800,500);
        frame.setVisible(true);
    }

}


