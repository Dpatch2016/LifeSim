package cellSim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIBeta extends JInternalFrame implements ActionListener {
        // Create panels for the Model and the View
        JInternalFrame editPanel, gridPanel;
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
        JTextField foodRegenInput = new JTextField("10");
        JTextField initialCellsInput = new JTextField("5");
        JTextField initialFoodInput = new JTextField("3");
        //Buttons & TextFields
        JButton submitButton = new JButton("Submit");
        // Layout
        GridBagLayout editLayout = new GridBagLayout();
        GridBagConstraints editLayoutConstraints = new GridBagConstraints();

        //constructor
        public UIBeta() {
            JTextField[] descriptionList = createDescriptions();

            createEditPanel(descriptionList);
            JTextField instructions = new JTextField("If invalid input is received, will default to original values");
            instructions.setEditable(false);
            editPanel.add(instructions);

            gridPanel = new JInternalFrame();

            // Don't think I need this
            // this.add(gridPanel);
            // addGrid(gridPanel);

            // Control Panel Setup
            JInternalFrame controlPanel = createControlPanel();


        }

        public JInternalFrame getEditPanel(){
            return editPanel;
        }

        public JInternalFrame getGridPanel(){
            return gridPanel;
        }


        private JInternalFrame createControlPanel() {
            JInternalFrame controlPanel = new JInternalFrame();
            this.add(controlPanel);
            addButtonsToControlPanel(controlPanel);
            return controlPanel;

        }

        private void createEditPanel(JTextField[] descriptionList) {
            editPanel = new JInternalFrame();
            this.add(editPanel);
            editPanel.add(hungerDecayInput);
            editPanel.add(foodGenInput);
            editPanel.add(lifeGenInput);
            editPanel.add(foodRegenInput);
            editPanel.add(initialCellsInput);
            editPanel.add(initialFoodInput);

            // Format and add descriptions to editpanel
            for(int i = 1; i < 6; i++){
                editPanel.add(descriptionList[i-1]);
            }
            editPanel.add(submitButton);
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

        public void addGrid(JInternalFrame gridPanel){
            JTextField textField4 = new JTextField("Grid Placeholder");
            gridPanel.add(textField4);

        }

        public void addButtonsToControlPanel(JInternalFrame controlPanel){
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
            controlPanel.add(playButton);
            controlPanel.add(nextButton);
            controlPanel.add(resetButton);
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
                lifeGeneration = 0.5;
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
            if(hungerDecayVal >= 1 && hungerDecayVal <= 100){
                // from 1 to 100
                return false;
            }
            else if(foodGeneration >= 0 && foodGeneration <= 1){
                // from 0 to 1
                return false;
            }
            else if(lifeGeneration >= 0 && lifeGeneration <= 1){
                // from 0 to 10
                return false;
            }
            else if (healthFromFood >= 1 && healthFromFood <= 100){
                // form 0 to 10
                return false;
            }
            else if (initialCellsVal >= 0 && initialCellsVal <= 10){
                //from 0 to 10
                return false;
            }
            else if (initialFoodVal >= 0 && initialFoodVal <= 10){
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
            editPanel.setBackground(Color.GRAY); //filler
            getVariables();
            if(!inputValidation()){
                hungerDecayVal = 1;
                foodGeneration = 0.5;
                lifeGeneration = 0.5;
                healthFromFood = 10;
                initialCellsVal = 5;
                initialFoodVal = 3;
            }
            System.out.println("hungerDecayVal = " + hungerDecayVal);
            System.out.println("foodGeneration = " + foodGeneration);
            System.out.println("lifeGeneration = " + lifeGeneration);
            System.out.println("healthFromFood = " + healthFromFood);
            System.out.println("initialCellsVal = " + initialCellsVal);
            System.out.println("initialFoodVal = " + initialFoodVal + "\n");


        }

        public static void main(String[] args){
            JFrame frame = new JFrame("Life Simulator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            UIBeta one = new UIBeta();
            JInternalFrame editFrame = one.getGridPanel();
            frame.add(editFrame);
            frame.setSize(1280,720);
            frame.setVisible(true);
        }

    }



