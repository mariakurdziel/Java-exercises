package ChristmasTree;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Snowman implements XmasShape {
    int x;
    int y;
    double scale;

    Snowman(int x, int y,double scale)
    {
        this.x=x;
        this.y=y;
        this.scale=scale;
    }

    @Override
    public void render(Graphics2D g2d) {
        // ustaw kolor wypełnienia
        AffineTransform mat = g2d.getTransform();
        GradientPaint grad = new GradientPaint(0,0,new Color(111, 31, 0),0,100, new Color(111, 31, 0));
        g2d.setPaint(grad);
        g2d.setTransform(mat);

        int x2[]={120,120,240,240};
        int y2[]={225,233,103,95};
        g2d.fillPolygon(x2,y2,x2.length);

        int x3[]={-20,-20,100,100};
        int y3[]={103,95,225,233};
        g2d.fillPolygon(x3,y3,x3.length);

        g2d.setColor(Color.white);
        g2d.fillOval(x-10,y,150,150);
        g2d.fillOval(x+3,y-100,120,120);
        g2d.fillOval(x+16,y-180,90,90);
        g2d.setColor(Color.black);
        g2d.fillOval(x+40,y-150,10,10);
        g2d.fillOval(x+70,y-150,10,10);
        g2d.setColor(Color.red);
        int x[]={112,112,76};
        int y[]={50,64,57};
        g2d.fillPolygon(x,y,x.length);
        int y_=100;
        g2d.setColor(Color.black);
        for(int i=0; i<5;i++)
        {
            g2d.fillOval(100,y_,20,20);
            y_+=45;
        }
        g2d.setColor(Color.magenta);
        int x1[]={70,70,150,150};
        int y1[]={75,115,75,115};
        g2d.fillPolygon(x1,y1,x1.length);
        g2d.setColor(Color.orange);
        g2d.fillOval(105,90,13,13);


    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}