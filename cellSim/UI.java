package cellSim;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JPanel implements ActionListener {
    // Create panels for the Model and the View
    JPanel editPanel, gridPanel;
    //Variables
    int hungerDecayVal = 0; // from 1 to 100
    double foodGeneration = 0; // from 0 to 1
    double lifeGeneration = 0; // from 0 to 1
    int healthFromFood = 0; // from 1 to 100
    int initialCellsVal = 0; // from 0 to 10
    int initialFoodVal = 0; // form 0 to 10
    //TextField Variables
    JTextField hungerDecayInput = new JTextField("1");
    JTextField foodGenInput = new JTextField("0.5");
    JTextField lifeGenInput = new JTextField("0.5");
    JTextField foodRegenInput = new JTextField("20");
    JTextField initialCellsInput = new JTextField("5");
    JTextField initialFoodInput = new JTextField("3");
    //Buttons & TextFields
    JButton submitButton = new JButton("Submit");
    // Layout
    GridBagLayout editLayout = new GridBagLayout();
    GridBagConstraints editLayoutConstraints = new GridBagConstraints();

    //constructor
    public UI() {

        JTextField[] descriptionList = createDescriptions();

        super.setLayout(new GridBagLayout());

        GridBagConstraints editPanelLayout = new GridBagConstraints();
        GridBagConstraints gridPanelLayout = new GridBagConstraints();
        GridBagConstraints controlPanelLayout = new GridBagConstraints();
        addLayoutConstraints(editPanelLayout, gridPanelLayout, controlPanelLayout);

        GridBagConstraints description = new GridBagConstraints();
        GridBagConstraints inputFields = new GridBagConstraints();
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        inputFields.gridx = 1;
        description.gridx = 0;

        createEditPanel(inputFields, editPanelLayout, description, buttonConstraints, descriptionList);

        // Add dropdown menu with Life options to simulate
        String[] lifeString = {"Cell", "Fish", "Human", "Martian", "Orc"};
        JComboBox lifeList = new JComboBox(lifeString);
        editPanel.add(lifeList, buttonConstraints);

        gridPanel = new JPanel(new GridBagLayout());
        this.add(gridPanel, gridPanelLayout);
        addGrid(gridPanel);

        // Control Panel Setup
        JPanel controlPanel = createControlPanel(controlPanelLayout, buttonConstraints);


    }

    private JPanel createControlPanel(GridBagConstraints controlPanelLayout, GridBagConstraints buttonConstraints) {
        JPanel controlPanel = new JPanel(new GridBagLayout());
        this.add(controlPanel, controlPanelLayout);
        addButtonsToControlPanel(buttonConstraints, controlPanel);
        return controlPanel;

    }

    private void createEditPanel(GridBagConstraints inputFields, GridBagConstraints editPanelLayout, GridBagConstraints description, GridBagConstraints buttonConstraints, JTextField[] descriptionList) {
        editPanel = new JPanel(new GridBagLayout());
        this.add(editPanel, editPanelLayout);
        inputFields.gridy = 1;
        inputFields.gridx = 2;
        editPanel.add(hungerDecayInput, inputFields);
        inputFields.gridy = 2;
        editPanel.add(foodGenInput,inputFields);
        inputFields.gridy = 3;
        editPanel.add(lifeGenInput, inputFields);
        inputFields.gridy = 4;
        editPanel.add(foodRegenInput, inputFields);
        inputFields.gridy = 5;
        editPanel.add(initialCellsInput, inputFields);
        // Format and add descriptions to editpanel
        for(int i = 1; i < 6; i++){
            description.gridy = i;
            editPanel.add(descriptionList[i-1], description);
        }
        buttonConstraints.gridy = 7;
        editPanel.add(submitButton, buttonConstraints);
        buttonConstraints.gridy = 0;
        //add event listener to SubmitButton
        buttonProperties();

    }

    public JTextField[] createDescriptions(){
        JTextField[] descriptionList = new JTextField[6];
        JTextField hungerDecay = new JTextField("hunger decay per turn (1-100): ");
        hungerDecay.setEditable(false);
        descriptionList[0] = hungerDecay;
        JTextField foodGen = new JTextField("food generation per turn (0-1): ");
        foodGen.setEditable(false);
        descriptionList[1] = foodGen;
        JTextField lifeGen = new JTextField("life generation per turn (0-1): ");
        lifeGen.setEditable(false);
        descriptionList[2] = lifeGen;
        JTextField foodRegen = new JTextField("health per food (0-100): ");
        foodRegen.setEditable(false);
        descriptionList[3] = foodRegen;
        JTextField initialCells = new JTextField("initial cells (0-10): ");
        initialCells.setEditable(false);
        descriptionList[4] = initialCells;
        JTextField initialFood = new JTextField("initial food (0-10): ");
        initialFood.setEditable(false);
        descriptionList[5] = initialFood;
        return descriptionList;
    }

    public void addLayoutConstraints(GridBagConstraints editPanelLayout, GridBagConstraints gridPanelLayout, GridBagConstraints controlPanelLayout){
        editPanelLayout.fill = GridBagConstraints.BOTH;
        editPanelLayout.anchor = GridBagConstraints.WEST;
        editPanelLayout.weightx = 0.5;
        editPanelLayout.weighty = 0.5;
        editPanelLayout.gridy = 0;


        gridPanelLayout.fill = GridBagConstraints.HORIZONTAL;
        gridPanelLayout.anchor = GridBagConstraints.EAST;
        gridPanelLayout.weightx = 0.5;
        gridPanelLayout.weighty = 0.5;
        gridPanelLayout.gridy = 0;

        controlPanelLayout.fill = GridBagConstraints.BOTH;
        controlPanelLayout.anchor = GridBagConstraints.SOUTH;
        controlPanelLayout.weightx = 0.5;
        controlPanelLayout.weighty = 0.25;
        controlPanelLayout.gridy = 1;
        controlPanelLayout.gridwidth = 2;
    }

    public void addGrid(JPanel gridPanel){
        JTextField textField4 = new JTextField("Grid Placeholder");
        gridPanel.add(textField4);

    }

    public void addButtonsToControlPanel(GridBagConstraints buttonConstraints, JPanel controlPanel){
        //Buttons
        // Control Panel buttons
        JButton playButton = new JButton("PLAY");
        JButton nextButton = new JButton("NEXT STEP");
        JButton resetButton = new JButton("RESET");
        JButton displayButton = new JButton("DISPLAY STATS");
        // This button is only shown when the the playButton is hidden.
        JButton pauseButton = new JButton("PAUSE");



        //Bottom Panel (Control Panel?)
        controlPanel.setBackground(Color.PINK);
        buttonConstraints.gridy = 0;
        controlPanel.add(playButton, buttonConstraints);
        buttonConstraints.gridy = 1;
        controlPanel.add(nextButton);
        buttonConstraints.gridy = 2;
        controlPanel.add(resetButton);
        buttonConstraints.gridy = 3;
        controlPanel.add(displayButton);
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
        editPanel.setBackground(Color.BLUE); //filler
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


