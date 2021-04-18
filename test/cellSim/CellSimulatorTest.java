package test.cellSim; 

import cellSim.CellSimulator;
import cellSim.Grids;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;
/** 
* CellSimulator Tester. 
* 
* @author <Authors name> 
* @since <pre>Apr 18, 2021</pre> 
* @version 1.0 
*/ 
public class CellSimulatorTest {
    private CellSimulator cellSimulator;
    private ArrayList<Grids> gridList;

@Before
public void before() throws Exception {
    int hungerDecayVal = 5; // from 1 to 100
    double foodGeneration = 0.5; // from 0 to 1
    double lifeGeneration = 0.1; // from 0 to 1
    int healthFromFood = 3; // from 1 to 100
    int initialCellsVal = 3; // from 0 to 10
    int initialFoodVal = 3; // form 0 to 10
    cellSimulator = new CellSimulator(hungerDecayVal,foodGeneration,lifeGeneration,healthFromFood,initialCellsVal,initialFoodVal);

    gridList = new ArrayList<Grids>();

    for(int i=0;i<10;i++){
        for(int j = 0; j<10; j++){
            gridList.add(new Grids(j,i));
        }
    }
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: returnGrids() 
* 
*/ 
@Test
public void testReturnGrids() throws Exception {
    int countFood = 0;
    int countCell = 0;
    for (Grids item: cellSimulator.returnGrids()){
        if(item.getFood()){
            countFood++;
        }

        if(item.getCell()){
            countCell++;
        }
    }

    assertTrue(countCell == 3 && countFood == 3);
}

/**
* 
* Method: returnNext() 
* 
*/ 
@Test
public void testReturnNext() throws Exception { 

    assertEquals(cellSimulator.returnGrids(),cellSimulator.returnNext());
} 

/** 
* 
* Method: findFromCoord(ArrayList<Grids> grid, int x, int y) 
* 
*/ 
@Test
public void testFindFromCoord() throws Exception {
    gridList.get(0).addCell();


    assertEquals(CellSimulator.findFromCoord(gridList,0,0),gridList.get(0));
} 

/** 
* 
* Method: makeNewFood(ArrayList<Grids> grid) 
* 
*/ 
@Test
public void testMakeNewFood() throws Exception {
    int count1 = 0;
    int count2 = 0;
    for(Grids item: gridList){
        if(item.getFood()){
            count1++;
        }
    }
    cellSimulator.makeNewFood(gridList);
    for(Grids item: gridList){
        if(item.getFood()){
            count2++;
        }
    }


    assertTrue(count1 ==  count2-1);
} 

/** 
* 
* Method: getStats() 
* 
*/ 
@Test
public void testGetStats() throws Exception {
    String temp = "Steps iterated through: " + 0 + "\n----------------------"
            + "\nNumber of cells generated: " + 3
            + "\nCurrent number of living cells: " + 3
            + "\nNumber of cells that died: " + 0
            + "\nAverage hunger level of living cells: " + 100.0

            +"\n\nNumber of food units generated: " + 3
            + "\nCurrent number of food units: " + 3
            ;

    assertEquals(temp,cellSimulator.getStats());
} 

/** 
* 
* Method: makeNewCell(ArrayList<Grids> grid) 
* 
*/ 
@Test
public void testMakeNewCell() throws Exception {
    int count1 = 0;
    int count2 = 0;
    for(Grids item: gridList){
        if(item.getCell()){
            count1++;
        }
    }
    cellSimulator.makeNewCell(gridList);
    for(Grids item: gridList){
        if(item.getCell()){
            count2++;
        }
    }


    assertTrue(count1 ==  count2-1);
} 





} 
