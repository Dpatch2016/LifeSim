package cellSim;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

public class GridConsolidated extends JInternalFrame{



    static final int RANGE = 10;

    static final int originX = 20;
    static final int originY = 40;
    static final int cellSide = 60;
    JPanel controlPanel = new JPanel(new GridLayout(RANGE,RANGE));

    public GridConsolidated(ArrayList<Grids> gridList) {



        for(Grids item:gridList){
            JTextArea box = new JTextArea();
            if(item.hasCell){
                box.setText("Cell  ");
                box.setBackground(new Color(155, 247, 187));
            }
            else if(item.hasFood){
                box.setText("Food");
                box.setBackground(new Color(	231, 224, 211));
            }
            else{
                box.setBackground(new Color(	226, 238, 241));
            }
            Border border = BorderFactory.createLineBorder(Color.white);
            box.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            controlPanel.add(box);
        }

        this.add(controlPanel);


        setSize(300,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void updateValues(ArrayList<Grids> gridList){
        controlPanel.removeAll();


        for(Grids item:gridList){
            JTextArea box = new JTextArea();
            if(item.hasCell){
                box.setText("Cell  ");
                box.setBackground(new Color(155, 247, 187));
            }
            else if(item.hasFood){
                box.setText("Food");
                box.setBackground(new Color(	231, 224, 211));
            }
            else{
                box.setBackground(new Color(	226, 238, 241));
            }
            Border border = BorderFactory.createLineBorder(Color.white);
            box.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            controlPanel.add(box);
        }

        this.add(controlPanel);


        setSize(300,300);
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