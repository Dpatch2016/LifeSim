package cellSim;

public class Grids {

    int x;
    int y;
    boolean hasCell;
    boolean hasFood;

    public Grids(int x, int y){
        this.x = x;
        this.y = y;
        this.hasCell = false;
        this.hasFood = false;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
    public void addFood(){
        this.hasFood = true;
    }

    public void addCell(){
        this.hasCell = true;
    }

    public void removeFood(){
        this.hasFood = false;
    }

    public void removeCell(){
        this.hasCell = false;
    }

    public boolean getFood(){
        return this.hasFood;
    }

    public boolean getCell(){
        return this.hasCell;
    }

}
