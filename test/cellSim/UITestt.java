package test.cellSim;

import cellSim.UI;
import cellSim.Grids;
import org.junit.*;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class UITestt {

    @Test
    public void testCreateDescriptions() throws Exception{
        UI object = new UI();

        String[] descriptionList = new String[6];
        String[] str = new String[6];
        str[0] = new String("hunger decay per turn (1-100): ");
        str[1] = new String("food generation per turn (0-1): ");
        str[2] = new String("life generation per turn (0-1): ");
        str[3] = new String("health per food (1-100): ");
        str[4] = new String("initial cells (0-10): ");
        str[5] = new String("initial food (0-20): ");

        for (int i = 0; i < 6; i++) {
            System.out.println(object.getDescriptions(i));
            descriptionList[i] = object.getDescriptions(i);
        }

        for (int i = 0; i < 6; i++){
            assertEquals("Test Failed", descriptionList[i], str[i]);
        }
    }

    @Test
    public void testGetDescriptions(){
        UI object = new UI();
        String[] strObject = new String[5];
        for (int i = 0; i < 5; i++) {
            System.out.println(object.getDescriptions(i));
            strObject[i] = object.getDescriptions(i);
        }
        assertEquals(strObject[0], object.getDescriptions(0));
    }



}
