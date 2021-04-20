package test.cellSim; 

import cellSim.Grids;
import org.junit.*;

import static org.junit.Assert.*;

/** 
* Grids Tester. 
* 
* @author David Patch
* @since <pre>Apr 18, 2021</pre> 
* @version 1.0 
*/ 
public class GridsTest {

    private Grids grids;

@Before
public void before() throws Exception {
    int x = 4;
    int y = 8;

    grids = new Grids(x,y);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getX() 
* 
*/ 
@Test
public void testGetX() throws Exception { 
    assertEquals(4,grids.getX());
} 

/** 
* 
* Method: getY() 
* 
*/ 
@Test
public void testGetY() throws Exception {
    assertEquals(8,grids.getY());
}

/**
 *
 * Method: getFood()
 *
 */
@Test
public void testGetFood() throws Exception {
    //Defaults false
    assertFalse(grids.getFood());
}

/**
* 
* Method: addFood() 
* 
*/ 
@Test
public void testAddFood() throws Exception { 
    grids.addFood();
    assertTrue(grids.getFood());
}

/**
 *
 * Method: getCell()
 *
 */
@Test
public void testGetCell() throws Exception {
    //Defaults false
    assertFalse(grids.getCell());
}

/**
* 
* Method: addCell() 
* 
*/ 
@Test
public void testAddCell() throws Exception { 
    grids.addCell();
    assertTrue(grids.getCell());
} 

/** 
* 
* Method: removeFood() 
* 
*/ 
@Test
public void testRemoveFood() throws Exception { 
    grids.addFood();
    grids.removeFood();
    assertFalse(grids.getFood());
} 

/** 
* 
* Method: removeCell() 
* 
*/ 
@Test
public void testRemoveCell() throws Exception { 
    grids.addCell();
    grids.removeCell();
    assertFalse(grids.getCell());
} 






} 
