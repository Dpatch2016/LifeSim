package cellSim;


import java.util.ArrayList;
import java.util.Random;

public class CellSimulator {
    static int RANGE = 10;
    static double FOODRATE = 0.5;
    static double CELLRATE = 0.05;

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

    public static void main(String[] args) {

        ArrayList<Grids> grid = new ArrayList<>();
        ArrayList<Cell> cell = new ArrayList<>();


        for(int i=0;i<RANGE;i++){
            for(int j = 0; j<RANGE; j++){
                grid.add(new Grids(j,i));
            }
        }

        for (int i = 0; i<1; i++){
            cell.add(makeNewCell(grid));
        }

        for (int i = 0; i<4; i++){
            makeNewFood(grid);
        }

        for (int i = 0; i<20; i++){
            try {
                for (Cell item : cell) {
                    if (item.isAlive()) {
                        item.navigate(grid);
                        printOut(grid);
                    } else {
                        findFromCoord(grid, item.getX(), item.getY()).removeCell();
                        cell.remove(item);
                    }

                }
                if(Math.random() <= CELLRATE){
                    cell.add(makeNewCell(grid));
                    System.out.println("NEW CELL MADE");
                }
                if(Math.random() <= FOODRATE){
                    makeNewFood(grid);
                    System.out.println("");
                }
            }catch(IndexOutOfBoundsException e){
                System.out.println("NO CELLS LEFT ALIVE");
                System.exit(0);
            }



        }


    }
}
