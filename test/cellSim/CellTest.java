package test.cellSim;

import cellSim.Cell;
import cellSim.Grids;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

/** 
* Cell Tester. 
* 
* @author David Patch
* @since <pre>Apr 18, 2021</pre> 
* @version 1.0 
*/ 
public class CellTest {
    private Cell cell;
    private ArrayList<Grids> gridList;

@Before
public void before() throws Exception {
    int x = 1;
    int y = 2;
    int hungerDecay = 3;
    int healthPerFood = 6;
    cell = new Cell(x,y,hungerDecay,healthPerFood);

    gridList = new ArrayList<Grids>();

    for(int i=0;i<10;i++){
        for(int j = 0; j<10; j++){
            gridList.add(new Grids(j,i));
        }
    }
    gridList.get(5).addFood();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: findNearestFood(ArrayList<Grids> grid) 
* 
*/ 
@Test
public void testFindNearestFood() throws Exception { 
    cell.findNearestFood(gridList);


    assertTrue(cell.getxFood()==5 && cell.getyFood() == 0);
} 

/** 
* 
* Method: getX() 
* 
*/ 
@Test
public void testGetX() throws Exception { 
    assertEquals(1,cell.getX());
} 

/** 
* 
* Method: getY() 
* 
*/ 
@Test
public void testGetY() throws Exception { 
    assertEquals(2,cell.getY());
} 

/** 
* 
* Method: setFood(int x, int y) 
* 
*/ 
@Test
public void testSetFood() throws Exception { 
    cell.setFood(4,6);
    assertTrue(cell.getxFood() ==4 && cell.getyFood() == 6);
} 

/** 
* 
* Method: isAlive() 
* 
*/ 
@Test
public void testIsAlive() throws Exception { 
    assertTrue(cell.isAlive());
} 

/** 
* 
* Method: getxFood() 
* 
*/ 
@Test
public void testGetxFood() throws Exception {
    cell.setFood(4,6);
    assertTrue(cell.getxFood() ==4);
} 

/** 
* 
* Method: getyFood() 
* 
*/ 
@Test
public void testGetyFood() throws Exception {
    cell.setFood(4,6);
    assertTrue(cell.getyFood() == 6);
} 

/** 
* 
* Method: navigate(ArrayList<Grids> grid) 
* 
*/ 
@Test
public void testNavigate() throws Exception {
    cell.navigate(gridList);

    assertTrue(cell.getX()==2 && cell.getY()==2);
}
/**
 *
 * Method: starve()
 *
 */
@Test
public void testStarve() throws Exception {
    for(int i = 0; i<50;i++){
        cell.starve();
    }

    assertFalse(cell.isAlive());
}
    /**
* 
* Method: eat(ArrayList<Grids> grid) 
* 
*/ 
@Test
public void testEat() throws Exception {
    cell.starve();

    cell.eat(gridList);
    cell.eat(gridList);
    assertTrue(cell.hunger == 100);

} 




} 
