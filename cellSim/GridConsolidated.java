package cellSim;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

public class GridConsolidated extends JInternalFrame{



    static final int cols = 10;
    static final int rows = 11;

    static final int originX = 20;
    static final int originY = 40;
    static final int cellSide = 60;



    public GridConsolidated(ArrayList<Grids> gridList) {

        JPanel controlPanel = new JPanel(new GridLayout(10,10));

        for(Grids item:gridList){
            JTextArea box = new JTextArea("");
            if(item.hasCell){
                box.setBackground(Color.green);
            }
            else if(item.hasFood){
                box.setBackground(Color.red);
            }
            else{
                box.setBackground(Color.white);
            }
            Border border = BorderFactory.createLineBorder(Color.BLACK);
            box.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            controlPanel.add(box);
        }

        this.add(controlPanel);


        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args) throws InterruptedException {

        JFrame jf=new JFrame();
        jf.setLayout(null);
        jf.setSize(1280, 720);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        JInternalFrame jInternalFrame=new GridConsolidated(1);
//        jInternalFrame.setSize(500, 500);
//        jInternalFrame.setTitle("Cell Simulator");
//        jInternalFrame.setVisible(true);
//        jInternalFrame.setLocation(0,0);
//        jf.add(jInternalFrame);
//        jf.repaint();
//
//        Thread.sleep(2000);
//        int tempx = jInternalFrame.getX();
//        int tempy = jInternalFrame.getY();
//        jf.remove(jInternalFrame);
//        jInternalFrame=new GridConsolidated(2);
//        jf.add(jInternalFrame);
//        jInternalFrame.setTitle("Cell Simulator");
//        jInternalFrame.setLocation(tempx,tempy);
//        jf.repaint();
//
//
//        Thread.sleep(2000);
//        tempx = jInternalFrame.getX();
//        tempy = jInternalFrame.getY();
//        jf.remove(jInternalFrame);
//        jInternalFrame=new GridConsolidated(3);
//        jf.add(jInternalFrame);
//        jInternalFrame.setTitle("Cell Simulator");
//        jInternalFrame.setLocation(tempx,tempy);
//        jf.repaint();
//
//        Thread.sleep(2000);
//        tempx = jInternalFrame.getX();
//        tempy = jInternalFrame.getY();
//        jf.remove(jInternalFrame);
//        jInternalFrame=new GridConsolidated(4);
//        jf.add(jInternalFrame);
//        jInternalFrame.setTitle("Cell Simulator");
//        jInternalFrame.setLocation(tempx,tempy);
//        jf.repaint();
    }
}