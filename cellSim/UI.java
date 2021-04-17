package cellSim;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UI extends JPanel {
    // Create panels for the Model and the View
    JPanel editPanel, gridPanel;
    //Variables
    int hungerDecayVal = 1; // from 1 to 100
    double foodGeneration = 0.5; // from 0 to 1
    double lifeGeneration = 0.2; // from 0 to 1
    int healthFromFood = 10; // from 1 to 100
    int initialCellsVal = 5; // from 0 to 10
    int initialFoodVal = 3; // form 0 to 10
    boolean playPressed = false;
    boolean getNext = false;
    CellSimulator cellSim = new CellSimulator(hungerDecayVal,foodGeneration,lifeGeneration,healthFromFood,initialCellsVal,initialFoodVal);
    GridConsolidated gridDisplay = new GridConsolidated(cellSim.returnGrids());
    //TextField Variables
    JTextField hungerDecayInput = new JTextField("1");
    JTextField foodGenInput = new JTextField("0.5");
    JTextField lifeGenInput = new JTextField("0.2");
    JTextField foodRegenInput = new JTextField("10");
    JTextField initialCellsInput = new JTextField("5");
    JTextField initialFoodInput = new JTextField("3");
    //Buttons & TextFields
    JButton submitButton = new JButton("Submit");
    // Layout
    GridBagLayout editLayout = new GridBagLayout();
    GridBagConstraints editLayoutConstraints = new GridBagConstraints();

    //constructor
    public UI() {

        JTextArea[] descriptionList = createDescriptions();

        super.setLayout(new GridBagLayout());

        hungerDecayInput.setColumns(4);
        foodGenInput.setColumns(4);
        lifeGenInput.setColumns(4);
        foodRegenInput.setColumns(4);
        initialCellsInput.setColumns(4);
        initialFoodInput.setColumns(4);

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
        JTextArea intro= new JTextArea("Cell Simulator! \nIf improper values are detected, will default to default values");

        intro.setOpaque(false);
        intro.setFont(new Font(intro.getFont().getName(), Font.BOLD, intro.getFont().getSize()));
        Border border = BorderFactory.createLineBorder(Color.black);
        intro.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        editPanel.add(intro, buttonConstraints);

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

    private void createEditPanel(GridBagConstraints inputFields, GridBagConstraints editPanelLayout, GridBagConstraints description, GridBagConstraints buttonConstraints, JTextArea[] descriptionList) {
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
        inputFields.gridy = 6;
        editPanel.add(initialFoodInput, inputFields);
        // Format and add descriptions to editpanel
        for(int i = 1; i < 7; i++){
            description.gridy = i;
            editPanel.add(descriptionList[i-1], description);
        }
        buttonConstraints.gridy = 7;
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getVariables();
                if(!inputValidation()){
                    hungerDecayVal = 1;
                    foodGeneration = 0.5;
                    lifeGeneration = 0.5;
                    healthFromFood = 10;
                    initialCellsVal = 5;
                    initialFoodVal = 3;
                    System.out.println("NOT RIGHT VALUES");
                }
                System.out.println("hungerDecayVal = " + hungerDecayVal);
                System.out.println("foodGeneration = " + foodGeneration);
                System.out.println("lifeGeneration = " + lifeGeneration);
                System.out.println("healthFromFood = " + healthFromFood);
                System.out.println("initialCellsVal = " + initialCellsVal);
                System.out.println("initialFoodVal = " + initialFoodVal + "\n");

                cellSim = new CellSimulator(hungerDecayVal,foodGeneration,lifeGeneration,healthFromFood,initialCellsVal,initialFoodVal);
                gridDisplay.updateValues(cellSim.returnGrids());
                gridDisplay.repaint();
                getNext = false;
                playPressed = false;

            }
        });
        editPanel.add(submitButton, buttonConstraints);
        buttonConstraints.gridy = 0;
        //add event listener to SubmitButton


    }

    public JTextArea[] createDescriptions(){
        JTextArea[] descriptionList = new JTextArea[6];
        JTextArea hungerDecay = new JTextArea("hunger decay per turn (1-100): ");
        hungerDecay.setOpaque(false);
        hungerDecay.setEditable(false);
        descriptionList[0] = hungerDecay;
        JTextArea foodGen = new JTextArea("food generation per turn (0-1): ");
        foodGen.setOpaque(false);
        foodGen.setEditable(false);
        descriptionList[1] = foodGen;
        JTextArea lifeGen = new JTextArea("life generation per turn (0-1): ");
        lifeGen.setOpaque(false);
        lifeGen.setEditable(false);
        descriptionList[2] = lifeGen;
        JTextArea foodRegen = new JTextArea("health per food (1-100): ");
        foodRegen.setOpaque(false);
        foodRegen.setEditable(false);
        descriptionList[3] = foodRegen;
        JTextArea initialCells = new JTextArea("initial cells (0-10): ");
        initialCells.setOpaque(false);
        initialCells.setEditable(false);
        descriptionList[4] = initialCells;
        JTextArea initialFood = new JTextArea("initial food (0-10): ");
        initialFood.setOpaque(false);
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
//        JTextField textField4 = new JTextField("Grid Placeholder");
//        gridPanel.add(textField4);

    }

    public void systemControl(JFrame frame) throws InterruptedException{

        if(playPressed){
            try {
                frame.remove(gridDisplay);
            }
            catch(Exception e){
                //nothing
            }
            gridDisplay = new GridConsolidated(cellSim.returnNext());
            frame.add(gridDisplay);
            System.out.println("PLAYING!");


        }
        if(getNext){
            try {
                frame.remove(gridDisplay);
            }
            catch(Exception e){
                //nothing
            }
            getNext = false;
            gridDisplay = new GridConsolidated(cellSim.returnNext());
            frame.add(gridDisplay);
            System.out.println("PLAYING!");
        }

    }

    public void addButtonsToControlPanel(GridBagConstraints buttonConstraints, JPanel controlPanel){
        //Buttons
        // Control Panel buttons
        JButton playButton = new JButton("PLAY/PAUSE");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("PLAY PRESSED" + playPressed); playPressed = !playPressed;
            }
        });
        JButton nextButton = new JButton("NEXT STEP");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("NEXT PRESSED"); getNext = true; playPressed = false;
            }
        });
        JButton resetButton = new JButton("RESET");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cellSim = new CellSimulator(hungerDecayVal,foodGeneration,lifeGeneration,healthFromFood,initialCellsVal,initialFoodVal);
                gridDisplay.updateValues(cellSim.returnGrids());
                playPressed = false;
                getNext = false;
                System.out.println("RESET PRESSED");

            }
        });
        JButton displayButton = new JButton("DISPLAY STATS");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playPressed = false;
                getNext = false;
                String textToDisplay = cellSim.getStats();
                JOptionPane.showMessageDialog(getParent(),textToDisplay);

                System.out.println("DISPLAY PRESSED");
            }
        });
        // This button is only shown when the the playButton is hidden.
        JButton pauseButton = new JButton("PAUSE");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playPressed = false;
            }
        });



        //Bottom Panel (Control Panel?)
        controlPanel.setBackground(new Color(135, 195, 216));
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
        try {
            hungerDecayVal = Integer.parseInt(hungerDecayInput.getText()); // from 1 to 100
            foodGeneration = Double.parseDouble(foodGenInput.getText()); // from 0 to 1
            lifeGeneration = Double.parseDouble(lifeGenInput.getText()); // from 0 to 1
            healthFromFood = Integer.parseInt(foodRegenInput.getText()); // from 1 to 100
            initialCellsVal = Integer.parseInt(initialCellsInput.getText()); // from 0 to 10
            initialFoodVal = Integer.parseInt(initialFoodInput.getText()); // form 0 to 10
        }catch(Exception e){
            hungerDecayVal = 1;
            foodGeneration = 0.5;
            lifeGeneration = 0.2;
            healthFromFood = 10;
            initialCellsVal = 5;
            initialFoodVal = 3;
        }


    }

    /**
     * @inputValidation() Makes sure the variables are within the defined bounds.
     * @return true if variables are within the defined bounds, false otherwise.
     */
    public Boolean inputValidation(){
        // Make sure the Edit Screen variables are within specified bounds.
        if(!(hungerDecayVal >= 1 && hungerDecayVal <= 100)){
            // from 1 to 100

            return false;
        }
        else if(!(foodGeneration >= 0 && foodGeneration <= 1)){
            // from 0 to 1

            return false;
        }
        else if(!(lifeGeneration >= 0 && lifeGeneration <= 1)){

            // from 0 to 1
            return false;
        }
        else if (!(healthFromFood >= 1 && healthFromFood <= 100)){

            // form 1 to 100
            return false;
        }
        else if (!(initialCellsVal >= 0 && initialCellsVal <= 10)){

            //from 0 to 10
            return false;
        }
        else if (!(initialFoodVal >= 0 && initialFoodVal <= 10)){

            //from 0 to 10
            return false;
        }
        else
            return true;
    }

    /**
     * Attaches an event listener to the submit button in the EditVariables
     */
//    public void buttonProperties(){
//        submitButton.addActionListener(this);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//
//    }

    public static void main(String[] args) throws InterruptedException{
        JFrame frame = new JFrame("Life Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UI self = new UI();
        frame.setContentPane(self);
        frame.add(self.gridDisplay);
        frame.setSize(900,600);
        frame.setVisible(true);


        while(true){
            self.systemControl(frame);
            Thread.sleep(200);

        }
    }

}


