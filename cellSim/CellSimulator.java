package cellSim;


import javax.swing.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Random;

public class CellSimulator {
    static int RANGE = 10;
    static double FOODRATE = 0.5;
    static double CELLRATE = 0.05;

    static int hungerDecayVal;
    double foodGeneration;
    double lifeGeneration;
    static int healthFromFood;
    int initialCellsVal;
    int initialFoodVal;

    int stepsIterated;
    int foodGenerated;
    int cellsGenerated;
    int deadCells;
    int livingCells;
    int foodUnits;
    double averageHunger;


    ArrayList<Grids> grid;
    ArrayList<Cell> cell;




    public CellSimulator(int hungerDecayVal, double foodGeneration, double lifeGeneration,
                                             int healthFromFood, int initialCellsVal, int initialFoodVal){

        stepsIterated = 0;
        foodGenerated = 0;
        deadCells = 0;
        livingCells = 0;
        foodUnits = 0;
        averageHunger = 0;

        this.hungerDecayVal = hungerDecayVal;
        this.foodGeneration = foodGeneration;
        this.lifeGeneration = lifeGeneration;
        this.healthFromFood = healthFromFood;
        this.initialCellsVal = initialCellsVal;
        this.initialFoodVal = initialFoodVal;

        this.grid = new ArrayList<>();
        this.cell = new ArrayList<>();

        for(int i=0;i<RANGE;i++){
            for(int j = 0; j<RANGE; j++){
                this.grid.add(new Grids(j,i));
            }
        }

        for (int i = 0; i<initialCellsVal; i++){
            cellsGenerated++;
            cell.add(makeNewCell(this.grid));
        }

        for (int i = 0; i<initialFoodVal; i++){
            foodGenerated++;
            makeNewFood(this.grid);
        }


    }

    public ArrayList<Grids> returnGrids(){
        return this.grid;
    }

    public ArrayList<Grids> returnNext() {
        Iterator<Cell> itr = this.cell.iterator();
        while (itr.hasNext()) {
            Cell item = itr.next();
            if (item.isAlive()) {
                item.navigate(this.grid);

            } else {
                findFromCoord(this.grid, item.getX(), item.getY()).removeCell();
                itr.remove();
                deadCells++;
            }
        }

        if (Math.random() <= this.lifeGeneration) {
            if (cell.size() < RANGE) {
                this.cell.add(makeNewCell(this.grid));
                cellsGenerated++;
            }
        }
        if (Math.random() <= this.foodGeneration) {
            int temp = 0;
            for(Grids item: grid){
                if (item.hasFood){
                    temp++;
                }
            }
            if(temp < 2*RANGE) {
                makeNewFood(this.grid);
                foodGenerated++;
            }
        }

        stepsIterated++;

        return this.grid;
    }

    public static Grids findFromCoord(ArrayList<Grids> grid, int x, int y){
        for (Grids item: grid){
            if (item.getX() == x){
                if (item.getY() == y){
                    return item;
                }
            }
        }
        return grid.get(RANGE*RANGE-1);
    }

    public static void makeNewFood(ArrayList<Grids> grid){
        Random rand = new Random();
        int numb = rand.nextInt(RANGE*RANGE-1);
        while(grid.get(numb).getFood() || grid.get(numb).getCell()){
            numb = rand.nextInt(RANGE*RANGE-1);
        }
        grid.get(numb).addFood();
    }

    public String getStats(){
        int temp = 0;
        for (Cell item:cell){
            temp += item.hunger;
        }
        if (cell.size() != 0) {
            temp /= cell.size();
        }
        else{
            temp = 0;
        }
        averageHunger = temp;
        livingCells = cell.size();

        temp = 0;
        for (Grids item2:grid){
            if (item2.hasFood){
                temp++;
            }
        }

        foodUnits = temp;



        return "Steps iterated through: " + stepsIterated + "\n----------------------"
                + "\nNumber of cells generated: " + cellsGenerated
                + "\nCurrent number of living cells: " + livingCells
                + "\nNumber of cells that died: " + deadCells
                + "\nAverage hunger level of living cells: " + averageHunger

                +"\n\nNumber of food units generated: " + foodGenerated
                 + "\nCurrent number of food units: " + foodUnits
                ;
    }

    public static Cell makeNewCell(ArrayList<Grids> grid){
        Random rand = new Random();
        int numb = rand.nextInt(RANGE*RANGE-1);
        while(grid.get(numb).getFood() || grid.get(numb).getCell()){
            numb = rand.nextInt(RANGE*RANGE-1);
        }
        grid.get(numb).addCell();
        return new Cell(grid.get(numb).getX(),grid.get(numb).getY(),hungerDecayVal,healthFromFood);
    }

}
