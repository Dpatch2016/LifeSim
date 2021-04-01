package cellSim;


import javax.swing.*;
import java.util.ArrayList;
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

    public static Grids findFromCoord(ArrayList<Grids> grid, int x, int y){
        for (Grids item: grid){
            if (item.getX() == x){
                if (item.getY() == y){
                    return item;
                }
            }
        }
        return grid.get(RANGE*RANGE);
    }

    public static void makeNewFood(ArrayList<Grids> grid){
        Random rand = new Random();
        int numb = rand.nextInt(RANGE*RANGE);
        while(grid.get(numb).getFood() || grid.get(numb).getCell()){
            numb = rand.nextInt(RANGE*RANGE);
        }
        grid.get(numb).addFood();
    }

    public static Cell makeNewCell(ArrayList<Grids> grid){
        Random rand = new Random();
        int numb = rand.nextInt(RANGE*RANGE);
        while(grid.get(numb).getFood() || grid.get(numb).getCell()){
            numb = rand.nextInt(RANGE*RANGE);
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
        CellSimulator cellSim = new CellSimulator(0,0.5,0.05,0,1, 4);
        JFrame frame = new JFrame();
        JInternalFrame gridDisplay=new GridConsolidated(cellSim.returnGrids());

        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        for (int i = 0; i<1000; i++){
            int numberOfAlive = 0;
            try {
                for (Cell item : cellSim.cell) {
                    if (item.isAlive()) {
                        numberOfAlive++;
                        item.navigate(cellSim.grid);

                    } else {

                        findFromCoord(cellSim.grid, item.getX(), item.getY()).removeCell();
                        //cellSim.cell.remove(item);
                    }

                }
                System.out.println("Alive Cells are: "+ numberOfAlive);
                if(Math.random() <= cellSim.lifeGeneration){
                    cellSim.cell.add(makeNewCell(cellSim.grid));
                    System.out.println("NEW CELL MADE");
                }
                if(Math.random() <= cellSim.foodGeneration){
                    makeNewFood(cellSim.grid);
                    System.out.println("");
                }
                try{
                    frame.remove(gridDisplay);
                }
                catch(Exception e){

                }
                gridDisplay = new GridConsolidated(cellSim.returnGrids());
                frame.add(gridDisplay);
                //printOut(cellSim.grid);
            }catch(IndexOutOfBoundsException e){
                System.out.println("NO CELLS LEFT ALIVE" + e);
                //System.exit(0);
            }


        Thread.sleep(500);
        }


    }
}
