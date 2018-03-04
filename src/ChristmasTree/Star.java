package ChristmasTree;

import java.awt.*;

public class Star implements XmasShape {
    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;

    Star(int x, int y,double scale)
    {
        this.x=x;
        this.y=y;
        this.scale=scale;
        this.lineColor=Color.YELLOW;
        this.fillColor=Color.YELLOW;
    }

    @Override
    public void render(Graphics2D g2d) {
        // ustaw kolor wypełnienia
        g2d.setColor(fillColor);
        int x1[]={270,330,390};
        int y1[]={240,340,240};
        int x2[]={270,330,390};
        int y2[]={310,210,310};
        g2d.fillPolygon(x1,y1,x1.length);
        g2d.fillPolygon(x2,y2,x2.length);

    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
