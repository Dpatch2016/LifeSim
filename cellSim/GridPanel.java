package Jframe;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel{
    


    private static final long serialVersionUID = 1L;

    static final int cols = 10;
    static final int rows = 10;

    static final int originX = 10;
    static final int originY = 10;
    static final int cellSide = 60;

    @Override
    public void paintComponents(Graphics g){
        super.paintComponents(g);
       
        for (int i=0; i < rows+1; i++){
            g.drawLine(originX, originY + i * cellSide, originX + cols * cellSide, originY + i * cellSide);
        }
        for (int i=0; i < cols+1; i++){
            g.drawLine(originX + i * cellSide, originY, originX + i * cellSide, originY + rows * cellSide);
        }
    }

    public void findCellNum(int j){
        
    }
 
}


