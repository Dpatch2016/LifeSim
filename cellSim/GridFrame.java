package Jframe;

import javax.swing.*;

public class GridFrame {

    public static void main(String[] args) {

       JFrame frame = new JFrame("Hello World");
        frame.setSize(960,960);
   
        GridPanel panel = new GridPanel();
        frame.add(panel);

        frame.setVisible(true);
        //f.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
