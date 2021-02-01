package cellSim;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class Cell {

    boolean alive;
    int hunger;
    int x;
    int y;
    int xFood;
    int yFood;

    public Cell(int x, int y){
        this.alive = true;
        this.hunger = 100;
        this.x = x;
        this.y = y;
        this.xFood = x;
        this.yFood = y;
    }

    public void findNearestFood(ArrayList<Grids> grid){
        double distance = Double.POSITIVE_INFINITY;
        boolean found = false;
        Grids holder =null;

        for(Grids item:grid){
            if(item.getFood()){
                int tempX = item.getX();
                int tempY = item.getY();
                double tempDistance = Point2D.distance(tempX, tempY, this.getX(),this.getY());

                if (tempDistance < distance){
                    distance = tempDistance;
                    holder = item;
                    found = true;
                }

            }
        }

        if(found){
            setFood(holder.getX(), holder.getY());
        }
        else{
            Random rand = new Random();
            setFood(rand.nextInt(Main.RANGE), rand.nextInt(Main.RANGE));
            System.out.println("---WANDERING AIMLESSLY---");
        }
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setFood(int x, int y){
        this.xFood = x;
        this.yFood = y;
    }

    public boolean isAlive(){
        return this.alive;
    }

    public int getxFood(){
        return this.xFood;
    }

    public int getyFood(){
        return this.yFood;
    }

    public void navigate(ArrayList<Grids> grid){
        findNearestFood(grid);
        int xDistance = this.getX() - this.getxFood();
        int yDistance = this.getY() - this.getyFood();

        //System.out.println("My coords are " + self.x + ", " + self.y + ". And my nearest food is " + self.xfood +", " + self.yfood);
        Main.findFromCoord(grid, this.getX(), this.getY()).removeCell();
        if (Math.abs(yDistance) > Math.abs(xDistance)){
            if(yDistance < 0)
                this.y++;
            else
                this.y--;
        }
        else{
            if(xDistance < 0)
                this.x++;
            else
                this.x--;
        }
        if(Main.findFromCoord(grid, this.getX(), this.getY()).hasCell){
            this.x +=  (int)(Math.random() * (1 - -1 + 1) + -1);
            this.y +=  (int)(Math.random() * (1 - -1 + 1) + -1);
        }
        //
        //
        //
        if (this.getX() < 0)
            this.x = 0;
        else if (this.getX() > Main.RANGE)
            this.x = Main.RANGE;
        if (this.getY() < 0)
            this.y = 0;
        else if (this.getY() > Main.RANGE)
            this.y = Main.RANGE;

        Main.findFromCoord(grid, this.getX(),this.getY()).addCell();
        xDistance = this.getX() - this.getxFood();
        yDistance = this.getY() - this.getyFood();

        if(xDistance == 0 && yDistance == 0)
            this.eat(grid);
        else
            this.starve();


    }

    public void eat(ArrayList<Grids> grid){
        this.hunger += 5;
            if (this.hunger > 100)
                this.hunger = 100;
        Main.findFromCoord(grid, this.getX(),this.getY()).removeFood();
    }

    public void starve(){
        this.hunger -= 3;
            if (this.hunger < 0){
                this.hunger = 0;
                this.alive = false;
            }
    }


/*
    self.x += random.randrange(-1,1,2)
    self.y += random.randrange(-1,1,2)


    def eat(self):
    self.hunger += random.randrange(5, 10)
            if (self.hunger > 100):
    self.hunger = 100

    def starve(self):
    self.hunger -= random.randrange(1,3)
            if(self.hunger < 0):
    self.hunger = 0
    self.alive = False

 */
}
