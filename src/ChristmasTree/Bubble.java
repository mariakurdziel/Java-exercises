package ChristmasTree;

import java.awt.*;
import java.awt.geom.AffineTransform;


public class Bubble implements XmasShape {
        int x;
        int y;
        double scale;

        Bubble(int x, int y,double scale)
        {
            this.x=x;
            this.y=y;
            this.scale=scale;
        }

        @Override
        public void render(Graphics2D g2d) {
            // ustaw kolor wypełnienia
            AffineTransform mat = g2d.getTransform();
            GradientPaint grad = new GradientPaint(0,0,new Color(111, 9, 102),0,100, new Color(111, 9, 102));
            g2d.setPaint(grad);
            g2d.setTransform(mat);
            g2d.fillOval(x,y,80,80);
            g2d.fillOval(x+150,y+250,80,80);
            g2d.fillOval(x-150,y+500,80,80);
            g2d.fillOval(x+180,y+750,80,80);
            g2d.fillOval(x-180,y+1000,80,80);
            g2d.fillOval(x+230,y+1250,80,80);

            GradientPaint grad2 = new GradientPaint(0,0,new Color(232, 223, 8),0,100, new Color(232, 223, 8));
            g2d.setPaint(grad2);
            g2d.setTransform(mat);
            g2d.fillOval(x+50,y+970,50,80);
            g2d.fillOval(x-400,y+1300,50,80);
            g2d.fillOval(x+500,y+1300,50,80);
            g2d.fillOval(x+50,y+550,50,80);





        }

        @Override
        public void transform(Graphics2D g2d) {
            g2d.translate(x,y);
            g2d.scale(scale,scale);
        }
    }

