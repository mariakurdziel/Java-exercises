package ChristmasTree;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Branch implements XmasShape {
    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;

    Branch(int x, int y,double scale)
    {
        this.x=x;
        this.y=y;
        this.scale=scale;
        //this.lineColor=Color.getHSBColor(154, 254, 25);
        //this.fillColor=Color.getHSBColor(154, 254, 25);
    }

    @Override
    public void render(Graphics2D g2d) {

        //Graphics2D g2d= (Graphics2D)g;
        AffineTransform mat = g2d.getTransform();
        GradientPaint grad = new GradientPaint(0,0,new Color(75, 17, 25),0,100, new Color(9, 74, 14));
        g2d.setPaint(grad);
        g2d.setTransform(mat);

        int x1[]={300,390,480};
        int y1[]={300,210,300};
        g2d.fillPolygon(x1,y1,x1.length);





    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}