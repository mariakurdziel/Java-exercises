package ChristmasTree;

import java.awt.*;

public class BetlehemStar implements XmasShape {
    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;

    BetlehemStar(int x, int y,double scale)
    {
        this.x=x;
        this.y=y;
        this.scale=scale;
        this.lineColor=Color.orange;
        this.fillColor=Color.orange;
    }

    @Override
    public void render(Graphics2D g2d) {
        // ustaw kolor wypełnienia

        int x[]={286,230,286,230,286,0};
        int y[]={0,43,65,87,131,65};
        g2d.setColor(fillColor);
        g2d.rotate(2*Math.PI/2+Math.PI/6);
        g2d.fillPolygon(x,y,x.length);




    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
