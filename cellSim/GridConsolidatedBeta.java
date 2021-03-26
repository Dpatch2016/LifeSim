package Jframe;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import javax.swing.border.Border;

public class GridConsolidatedBeta extends JInternalFrame{



    static final int cols = 10;
    static final int rows = 11;

    static final int originX = 20;
    static final int originY = 40;
    static final int cellSide = 60;



    public GridConsolidatedBeta(int k) {

        JPanel controlPanel = new JPanel(new GridLayout(10,10));

        if (k==1){
        for (int i=0; i <10; i++){
            for(int j = 0; j<10;j++){
                JTextArea item = new JTextArea("");
                if(i==8 && j==9){
                    item.setText("5");
                    item.setBackground(new Color(255,192,203));
                }
                else{
                    item.setBackground(new Color(0,255,255));
                }
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                item.setBorder(BorderFactory.createCompoundBorder(border,
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
                controlPanel.add(item);
            }
        }}
        else if (k == 2){
            for (int i=0; i <10; i++){
                for(int j = 0; j<10;j++){

                    JTextArea item = new JTextArea("");
                    if(i==7 && j ==9){
                        item.setText("5");
                        item.setBackground(new Color(255,192,203));
                    }
                    else{
                        item.setBackground(new Color(0,255,255));
                    }
                    Border border = BorderFactory.createLineBorder(Color.BLACK);
                    item.setBorder(BorderFactory.createCompoundBorder(border,
                            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
                    controlPanel.add(item);
                }
            }
        }
        else if (k == 3){
            for (int i=0; i <10; i++){
                for(int j = 0; j<10;j++){

                    JTextArea item = new JTextArea("");
                    if(i==6 && j ==9){
                        item.setText("5");
                        item.setBackground(new Color(255,192,203));
                    }
                    else{
                        item.setBackground(new Color(0,255,255));
                    }
                    Border border = BorderFactory.createLineBorder(Color.BLACK);
                    item.setBorder(BorderFactory.createCompoundBorder(border,
                            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
                    controlPanel.add(item);
                }
            }
        }
        else if (k ==4){
            for (int i=0; i <10; i++){
                for(int j = 0; j<10;j++){

                    JTextArea item = new JTextArea("");
                    if(i==6 && j ==8){
                        item.setText("5");
                        item.setBackground(new Color(255,192,203));
                    }
                    else{
                        item.setBackground(new Color(0,255,255));
                    }
                    Border border = BorderFactory.createLineBorder(Color.BLACK);
                    item.setBorder(BorderFactory.createCompoundBorder(border,
                            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
                    controlPanel.add(item);
                }
            }
        }

        this.add(controlPanel);


        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

//    public void paint(Graphics g){
//        super.paintComponents(g);
//        for (int i=0; i < rows+1; i++){
//            g.drawLine(originX, originY + i * cellSide, originX + cols * cellSide, originY + i * cellSide);
//        }
//        for (int i=0; i < cols+1; i++){
//            g.drawLine(originX + i * cellSide, originY, originX + i * cellSide, originY + rows * cellSide);
//        }
//    }

    public static void main(String[] args) throws InterruptedException {

        JFrame jf=new JFrame();
        jf.setLayout(null);
        jf.setSize(1280, 720);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JInternalFrame jInternalFrame=new GridConsolidated(1);
        jInternalFrame.setSize(500, 500);
        jInternalFrame.setTitle("Cell Simulator");
        jInternalFrame.setVisible(true);
        jInternalFrame.setLocation(0,0);
        jf.add(jInternalFrame);
        jf.repaint();

        Thread.sleep(2000);
        int tempx = jInternalFrame.getX();
        int tempy = jInternalFrame.getY();
        jf.remove(jInternalFrame);
        jInternalFrame=new GridConsolidated(2);
        jf.add(jInternalFrame);
        jInternalFrame.setTitle("Cell Simulator");
        jInternalFrame.setLocation(tempx,tempy);
        jf.repaint();


        Thread.sleep(2000);
        tempx = jInternalFrame.getX();
        tempy = jInternalFrame.getY();
        jf.remove(jInternalFrame);
        jInternalFrame=new GridConsolidated(3);
        jf.add(jInternalFrame);
        jInternalFrame.setTitle("Cell Simulator");
        jInternalFrame.setLocation(tempx,tempy);
        jf.repaint();

        Thread.sleep(2000);
        tempx = jInternalFrame.getX();
        tempy = jInternalFrame.getY();
        jf.remove(jInternalFrame);
        jInternalFrame=new GridConsolidated(4);
        jf.add(jInternalFrame);
        jInternalFrame.setTitle("Cell Simulator");
        jInternalFrame.setLocation(tempx,tempy);
        jf.repaint();
    }
}
