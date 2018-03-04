package ChristmasTree;

import javax.swing.*;
import java.awt.*;

public class tree {


    public void paintComponent(Graphics g){
        Graphics2D g2d= (Graphics2D)g;
    }


    public static void main(String[] args) {
        // write your code here
        JFrame frame = new JFrame("Choinka");
        frame.setContentPane(new DrawPanel());
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);

    }
}
