package watki;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;

public class ClockWithGui extends JPanel {

    LocalTime time = LocalTime.now();

    class ClockThread extends Thread{
        @Override
        public void run() {
            for(;;){
                time = LocalTime.now();
                System.out.printf("%02d:%02d:%02d\n",time.getHour(),time.getMinute(),time.getSecond());

                //try {
                  //  sleep(1000);
                //} catch (InterruptedException e) {
                  //  e.printStackTrace();
                //}
                repaint();
            }
        }
    }

    public ClockWithGui()
    {
        ClockThread clock=new ClockThread();
        clock.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(getWidth()/2,getHeight()/2);

        for(int i=1;i<13;i++){
            AffineTransform at = new AffineTransform();
            at.rotate(2*Math.PI/12*i);
            Point2D src = new Point2D.Float(0,-120);
            Point2D trg = new Point2D.Float();
            at.transform(src,trg);
            g2d.drawString(Integer.toString(i),(int)trg.getX(),(int)trg.getY());
        }

        AffineTransform saveAT = g2d.getTransform();
        g2d.rotate(time.getHour()%12*2*Math.PI/12);
        g2d.drawLine(0,0,0,-60);
        g2d.setTransform(saveAT);

        AffineTransform saveATmin = g2d.getTransform();
        g2d.rotate(time.getMinute()%60*2*Math.PI/60);
        g2d.drawLine(0,0,0,-90);
        g2d.setTransform(saveATmin);


        AffineTransform saveATsec = g2d.getTransform();
        g2d.rotate(time.getSecond()%3600*2*Math.PI/60);
        g2d.drawLine(0,0,0,-100);
        g2d.setTransform(saveATsec);


        g2d.drawOval(-132,-140,270,270);

        for(int i=0; i<60;i++)
        {
            AffineTransform line= g2d.getTransform();
            g2d.setColor(Color.black);
            g2d.rotate(Math.PI/30);
            if((i+1)%5==0){
            g2d.setColor(Color.blue);
            g2d.drawLine(5,-89,5,-109);}
            else
                g2d.drawLine(5,-94,5,-109);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        frame.setContentPane(new ClockWithGui());
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);

    }
}

