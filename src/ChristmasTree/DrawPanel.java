package ChristmasTree;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;


public class DrawPanel extends JPanel {

    List<XmasShape> shapes = new ArrayList<>();

    DrawPanel(){
        setBackground(new Color(6, 21, 120));
        setOpaque(true);
        snow();
        root(330,445,0.6);
        branch(250,-20,0.7);
        star();
        snowflakes();
        betlehemstar();
        snowman();
        bubbles();
        bubbles2();



    }

    //public void paintComponent(Graphics g){
      //  g.setFont(new Font("Helvetica", Font.BOLD, 18));
       // g.drawString("Choinka", 500, 20);

       // g.drawLine(190,10,280,100);
       // g.setColor(Color.yellow);
       // g.drawLine(100,100,190,10);
       // g.fillOval(100,101,100,30);
       // g.setColor(Color.green);
       // g.drawOval(100,101,30,30);

       // int x[]={286,253,223,200,148,119,69,45,0};
       // int y[]={0,101,89,108,79,95,66,80,56};
      //  g.fillPolygon(x,y,x.length);

       // int x1[]={100,190,280};
     //   int y2[]={10,100,10};

        //int


    //}

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }


         g.setFont(new Font("Serif", Font.PLAIN, 30));
         g.setColor(Color.orange);
         g.drawString("Wesołych świąt!", 400, 45);



        //int x1[]={100,190,280};
        //int y1[]={10,100,10};
        //g.fillPolygon(x1,y1,x1.length);
        //g.setColor(Color.green);

    }

    void bubbles()
    {
          shapes.add(new Bubble(400,120,0.25));

    }

    void bubbles2(){

        shapes.add(new Bubble2(450,190,0.15));
        shapes.add(new Bubble2(520,250,0.15));
        shapes.add(new Bubble2(540,370,0.15));
        shapes.add(new Bubble2(420,310,0.15));
        shapes.add(new Bubble2(430,440,0.15));

    };

    void branch(int x, int y, double scale)
    {

        int x_=x;
        int y_=y;
        double scale_=scale;

        for(int i=0; i<6;i++) {
            shapes.add(new Branch(x_, y_, scale_));
            x_-=118;
            y_-=25;
            scale_+=0.3;

        }
    }

    void root(int x, int y, double scale)
    {
        shapes.add(new Root(x, y, scale));
    }

    void snow()
    {
        shapes.add(new Snow(62,280,1));

    }

    void star()
    {
        shapes.add(new Star(423,30,0.3));
    }

    void snowflakes()
    {
        shapes.add(new Stars(50,50,0.2));
        shapes.add(new Stars(180,10,0.2));
        shapes.add(new Stars(130,100,0.2));
        shapes.add(new Stars(260,80,0.2));
        shapes.add(new Stars(900,50,0.2));
        shapes.add(new Stars(800,10,0.2));
        shapes.add(new Stars(700,80,0.2));
        shapes.add(new Stars(820,100,0.2));
    }

    void betlehemstar()
    {
        shapes.add(new BetlehemStar(850,300,0.5));
        Star st=new Star(700,140,0.5);
        shapes.add(st);
    }

    void snowman()
    {
        shapes.add(new Snowman(50,180,0.8));
    }


}