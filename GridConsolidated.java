package Jframe;

import javax.swing.*;
import java.awt.*;

public class GridConsolidated extends JFrame{
    JFrame f = new JFrame();

    static final int cols = 10;
    static final int rows = 11;

    static final int originX = 20;
    static final int originY = 40;
    static final int cellSide = 60;


    public GridConsolidated() {
        setSize(960,960);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void paint(Graphics g){
        super.paintComponents(g);
        for (int i=0; i < rows+1; i++){
            g.drawLine(originX, originY + i * cellSide, originX + cols * cellSide, originY + i * cellSide);
        }
        for (int i=0; i < cols+1; i++){
            g.drawLine(originX + i * cellSide, originY, originX + i * cellSide, originY + rows * cellSide);
        }
    }

    public static void main(String[] args) {
        new GridConsolidated();
    }
}
