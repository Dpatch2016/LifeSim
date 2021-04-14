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

    int hungerDecayVal;
    double foodGeneration;
    double lifeGeneration;
    int healthFromFood;
    int initialCellsVal;
    int initialFoodVal;


    ArrayList<Grids> grid;
    ArrayList<Cell> cell;




    public CellSimulator(int hungerDecayVal, double foodGeneration, double lifeGeneration,
                                             int healthFromFood, int initialCellsVal, int initialFoodVal){
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
            cell.add(makeNewCell(this.grid));
        }

        for (int i = 0; i<initialFoodVal; i++){
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
            }

            if (Math.random() <= this.lifeGeneration) {
                this.cell.add(makeNewCell(this.grid));
            }
            if (Math.random() <= this.foodGeneration) {
                makeNewFood(this.grid);
            }

        }
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

    public static Cell makeNewCell(ArrayList<Grids> grid){
        Random rand = new Random();
        int numb = rand.nextInt(RANGE*RANGE-1);
        while(grid.get(numb).getFood() || grid.get(numb).getCell()){
            numb = rand.nextInt(RANGE*RANGE-1);
        }
        grid.get(numb).addCell();
        return new Cell(grid.get(numb).getX(),grid.get(numb).getY());
    }

    public static void printOut(ArrayList<Grids> grid) {
        int j = 0;

        for (Grids item : grid) {
            if (item.getCell())
                System.out.print("C");
            else if (item.getFood())
                System.out.print("F");
            else
                System.out.print("X");
            j++;
            if (j == RANGE) {
                System.out.print("\n");
                j = 0;
            }
        }
        System.out.println("\n");
    }

    public static void main(String[] args) throws InterruptedException {
        CellSimulator cellSim = new CellSimulator(0,0.5,0.10,0,1, 4);
        JFrame frame = new JFrame();
        JInternalFrame gridDisplay=new GridConsolidated(cellSim.returnGrids());

        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        for (int i = 0; i<10000000; i++){
            int numberOfAlive = 0;


            try {
                cellSim.returnNext();
                try{
                    frame.remove(gridDisplay);
                }
                catch(Exception e){

                }
                gridDisplay = new GridConsolidated(cellSim.returnGrids());
                frame.add(gridDisplay);
                //printOut(cellSim.grid);
            }catch(Exception e){
                System.out.println("NO CELLS LEFT ALIVE" + e);
                //System.exit(0);
            }


            Thread.sleep(500);
        }
        System.out.println("DONE!!");

    }
}