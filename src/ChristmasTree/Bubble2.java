package ChristmasTree;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bubble2 implements XmasShape {
    int x;
    int y;
    double scale;

    Bubble2(int x, int y,double scale)
    {
        this.x=x;
        this.y=y;
        this.scale=scale;
    }

    @Override
    public void render(Graphics2D g2d) {
        // ustaw kolor wypełnienia
        AffineTransform mat = g2d.getTransform();
        GradientPaint grad = new GradientPaint(0,0,new Color(232, 11, 62),0,100, new Color(232, 11, 62));
        g2d.setPaint(grad);
        g2d.setTransform(mat);
        int x1[]={270,330,390,330};
        int y1[]={240,340,240,140};
        g2d.fillPolygon(x1,y1,x1.length);
        //g2d.fillOval(x,y,80,80);
       // g2d.fillOval(x+150,y+250,80,80);
        //g2d.fillOval(x-100,y+500,80,80);
        //g2d.fillOval(x+150,y+750,80,80);
        //g2d.fillOval(x-180,y+1000,80,80);
        //g2d.fillOval(x+230,y+1250,80,80);



    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
