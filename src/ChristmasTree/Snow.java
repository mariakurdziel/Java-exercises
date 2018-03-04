package ChristmasTree;

import java.awt.*;

public class Snow implements XmasShape {
    int x;
    int y;
    double scale;

    Snow(int x, int y,double scale)
    {
        this.x=x;
        this.y=y;
        this.scale=scale;
    }

    @Override
    public void render(Graphics2D g2d) {
        // ustaw kolor wypełnienia
        g2d.setColor(Color.white);
        g2d.fillOval(x,y,800,800);
        g2d.fillOval(x+400,y-100,850,850);
        g2d.fillOval(x-550,y-150,950,950);

    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}