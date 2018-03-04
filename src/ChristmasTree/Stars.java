package ChristmasTree;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Stars implements XmasShape {
    int x;
    int y;
    double scale;

    Stars(int x, int y, double scale)
    {
        this.x=x;
        this.y=y;
        this.scale=scale;
    }

    @Override
    public void render(Graphics2D g2d) {
        // ustaw kolor wypełnienia
        g2d.setColor(Color.YELLOW);
        // zachowaj macierz przekształcenia
        AffineTransform mat = g2d.getTransform();
        // przesuń początek układu
        g2d.translate(100,100);
        // zastosuj skalowanie
        g2d.scale(.2,.2);

        int x_=0;
        int y_=0;
        int x2_=100;
        int y2_=100;


              // narysuj linie
              for (int j = 0; j < 12; j++) {
                  g2d.drawLine(x_, y_, x2_, y2_);
                  g2d.rotate(2 * Math.PI / 12);
              }
        //oddtwórz poprzednie ustawienia transformacji układu współrzędnych
        g2d.setTransform(mat);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}