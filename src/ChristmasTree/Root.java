package ChristmasTree;


import java.awt.*;
import java.awt.geom.AffineTransform;

public class Root implements XmasShape {
    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;

    Root(int x, int y,double scale)
    {
        this.x=x;
        this.y=y;
        this.scale=scale;
        this.lineColor=Color.getHSBColor(102, 51, 0);
        this.fillColor=Color.getHSBColor(102, 51, 0);
    }

    @Override
    public void render(Graphics2D g2d) {
        // ustaw kolor wypełnienia
        AffineTransform mat = g2d.getTransform();
        GradientPaint grad = new GradientPaint(0,0,new Color(111, 31, 0),0,100, new Color(111, 31, 0));
        g2d.setPaint(grad);
        g2d.setTransform(mat);
        int x1[]={280,300,340,360};
        int y1[]={200,100,100,200};
        g2d.fillPolygon(x1,y1,x1.length);

    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}