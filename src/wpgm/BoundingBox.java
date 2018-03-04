package wpgm;

import com.sun.xml.internal.bind.v2.util.QNameMap;
import javafx.util.Pair;

import java.util.*;
import java.util.Map.Entry;

public class BoundingBox {

    // boolean flag_;
    // BoundingBox(boolean flag)
    // {
    //flag_=flag;
    // }


    double xmin = 1000.0;
    double ymin = 1000.0;
    double xmax;
    double ymax;
    double x_;
    double y_;
    boolean empty = true; //zmienna okreslajaca stan, czy ma tworzyc prostokat czy byc pusty, w wypadku true ma być pusty


    List<Double> iksy = new ArrayList<>();//lista pozwalajaca na latwiejsze wyszukiwanie w mapie
    List<Double> ygreki = new ArrayList<>();//lista stworzona na potrzeby GetWKT(), poniewaz nie mozna stworzyc mapy -wspolrzedne x nie byly unikalne i wartosci y sie nadpisywaly


    /**
     * Powiększa BB tak, aby zawierał punkt (x,y)
     *
     * @param x - współrzędna x
     * @param y - współrzędna y
     */

    void addPoint(double x, double y) {
        if (!empty) { //jeśli bounding box nie jest pusty
            x_ = x;
            y_ = y;

            if (x_ > xmax && y_ > ymax) {
                xmax = x_;
                ymax = y_;
            }


            if (x_ < xmin && y_ < ymin) {
                xmin = x_;
                ymin = y_;
            }

            iksy.add(x_);
            ygreki.add(y_);
        }
    }

    /**
     * Sprawdza, czy BB zawiera punkt (x,y)
     *
     * @param x
     * @param y
     * @return
     */
    boolean contains(double x, double y) {

        boolean flag = false;

        if (!empty) {

            if (x > xmin && x < xmax && y > ymin && y < ymax)
                flag = true;
            else
                flag = false;
        }


        return false;
    }


    /**
     * Sprawdza czy dany BB zawiera bb
     *
     * @param bb
     * @return
     */
    boolean contains(BoundingBox bb) {

        boolean flag = false;

        if (!empty) {

            if (contains(bb.xmin, bb.ymin) && contains(bb.xmax, bb.ymax))
                flag = true;
            else
                flag = false;

        }
        return flag;
    }

    void getWKT() {
        if (!empty) {
            System.out.printf(Locale.US, "LINESTRING(%f %f, %f %f, %f %f, %f %f,%f %f)",
                    iksy.get(0),
                    ygreki.get(0),
                    iksy.get(1),     //Nie korzystam tu z mapy, bo wartosci x bedace kluczami czasem byly takie same i dawalo to zly wynik
                    ygreki.get(1),
                    iksy.get(2),
                    ygreki.get(2),
                    iksy.get(3),
                    ygreki.get(3),
                    iksy.get(4),
                    ygreki.get(4)

            );
        }
    }

    boolean intersects(BoundingBox bb) {

        boolean flag = false;

        if (!empty) {
            if ((contains(bb.xmin, bb.ymin) == true && contains(bb.xmax, bb.ymax) == false || contains(bb.xmin, bb.ymin) == false && contains(bb.xmax, bb.ymax) == true))
                flag = true;

            if ((bb.contains(this.xmin, this.ymin) == true && bb.contains(this.xmax, this.ymax) == false || bb.contains(this.xmin, this.ymin) == false && bb.contains(this.xmax, this.ymax) == true))
                flag=true;

            return flag;

        }
        else

            return flag;
    }



    /**
     * Powiększa rozmiary tak, aby zawierał bb oraz poprzednią wersję this
     *
     * @param bb
     * @return
     */
    BoundingBox add(BoundingBox bb) {
        double new_xmin;
        double new_xmax;
        double new_ymin;
        double new_ymax;

        if(!empty) {
            if (bb.xmin < this.xmin) {

                this.xmin = bb.xmin;

            }

            if (bb.ymin < this.ymin) {

                this.ymin = bb.ymin;

            }

            if (bb.xmax > this.xmax) {

                this.xmax = bb.xmax;

            }

            if (bb.ymax > this.ymax) {

                this.ymax = bb.xmax;

            }


            for (int i = 0; i < bb.iksy.size(); i++) {

                iksy.add(bb.iksy.get(i));
                ygreki.add(bb.iksy.get(i));
            }


        }

        return this;


    }

    /**
     * Sprawdza czy BB jest pusty
     *
     * @return
     */
    boolean isEmpty() {

        if(iksy.isEmpty() && empty==false) // jesli bounding box jest w stanie typu prostokat, ale lista np. ygreków jest pusta
            return true;
        else
            return false;
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     *
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterX() {

        if(!empty) {
            if (iksy.isEmpty())
                throw new RuntimeException("Not implemented");
            else
                return (xmin + xmax) / 2;
        }

        else
            return 0.0;

    }

    /**
     * Oblicza i zwraca współrzędną y środka
     *
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterY() {

        if (!empty) {
            if (ygreki.isEmpty())
                throw new RuntimeException("Not implemented");
            else
                return (ymin + ymax) / 2;
        }
        else
            return 0.0;
    }

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     *
     * @param bbx prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległosci euklidesowej możesz użyć wzoru haversine
     * (ang. haversine formula)
     */
    double distanceTo(BoundingBox bbx) {

        if (!empty) {
            if (iksy.isEmpty() || ygreki.isEmpty())
                throw new RuntimeException("No points!");

            return Math.sqrt(Math.pow(getCenterX() - bbx.getCenterX(), 2) + Math.pow(getCenterY() - bbx.getCenterY(), 2));
        }
        else
            return 0.0;
    }

    public static void main(String[] args)
    {

    }
}
