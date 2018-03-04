package wpgm;

import CVSReader.CSVReader;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;

import static java.lang.System.out;


public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    List<Long> ids = new ArrayList<>(); //lista przechowujaca id, pomocnicza;
    public Map<AdminUnit, Long> AUtoParentId = new HashMap<>();//mapa Admin Unit-Parent ID
    public Map<Long, AdminUnit> IdtoAdminUnit = new HashMap<>();//mapa wyszukująca po Id AdminUnit, pomocnicza
    Map<Long,List<AdminUnit>> parentid2child = new HashMap<>();
    private Comparator<AdminUnit> cmp;

    String ToString(AdminUnit u) {
        String x = u.name + " " + u.adminLevel + " " + u.population + " " + u.area + " " + u.density +" "+u.bbox.xmin+" "+u.bbox.ymin+" "+u.bbox.xmax+" "+u.bbox.ymax+ "\n";

        return x;
    }
    AdminUnitList(){}

    private void fixMissingValues(AdminUnit au) {
        AdminUnit tmp = new AdminUnit();
        tmp = au;
        double x = 0.0;

        while (AUtoParentId.get(tmp) != 0 && IdtoAdminUnit.get(AUtoParentId.get(tmp)) == null) {
            tmp = IdtoAdminUnit.get(AUtoParentId.get(tmp));
        }

        x = IdtoAdminUnit.get(AUtoParentId.get(tmp)).density;

        if (au.density == 0.0)
            au.density = x;

        if (au.population == 0.0)
            au.population = au.area * au.density;

    }


    void read(String filename) throws IOException {

        CSVReader reader = new CSVReader(filename, ",", true);
        int counter = 0;

        while (reader.next())

        {
            AdminUnit un = new AdminUnit();
            un.parent = null;
            un.name = reader.get(2);

            if (reader.current[3].isEmpty())
                un.adminLevel = 0;
            else
                un.adminLevel = reader.getInt(3);

            if (reader.current.length < 5 || (reader.current.length >= 5) && (reader.current[5].isEmpty()))
                un.area = 0.0;
            else
                un.area = reader.getDouble(5);

            if (reader.current.length >= 5) {    //niektóre pozycje miały poniżej 5 pól
                if (reader.isMissing(4))
                    un.population = 0.0;//tak samo jak w density
                else
                    un.population = reader.getDouble(4);

                if (reader.isMissing(6))
                    un.density = 0.0; //wstępnie przypisujemy 0.0, zmienimy wartosci gdy bedziemy miec juz uzupelnione mapy z parentami
                else
                    un.density = reader.getDouble(6);
            }

            if(reader.current.length>7) //jeżeli element posiada takie wspolrzedne, nie wszystkie posiadaja
            {
                un.bbox.empty=false; //stan bounding-box (mozliwosc utworzenia kwadratu)
                un.bbox.addPoint(reader.getDouble(7),reader.getDouble(8));
                un.bbox.addPoint(reader.getDouble(9),reader.getDouble(10));
                un.bbox.addPoint(reader.getDouble(11),reader.getDouble(12));
                un.bbox.addPoint(reader.getDouble(13),reader.getDouble(14));
                un.bbox.addPoint(reader.getDouble(15),reader.getDouble(16));
            }
            units.add(un);
            ids.add(reader.getLong(0));
            if (reader.current[1].isEmpty())
                AUtoParentId.put(un, (long) 0);
            else
                AUtoParentId.put(un, reader.getLong(1));//przypisujemy każdemu unitowi id jego parenta
            IdtoAdminUnit.put(reader.getLong(0), un);//robimy mapę id do każdego unita

        }
            for (int i = 0; i < units.size(); i++) {
                long x = AUtoParentId.get(units.get(i));//wydobyc id parenta

                if (parentid2child.containsKey(x)) //sprawdzamy czy taki klucz w postaci id parenta juz jest
                {
                    parentid2child.get(x).add(units.get(i));
                } else {
                    List<AdminUnit> childrens = new ArrayList<>();
                    childrens.add(units.get(i));
                    parentid2child.put(x, childrens);
                }
            }

            for (int i = 0; i < units.size(); i++) {
                units.get(i).children = parentid2child.get(ids.get(i));
                //przypisuje unitadmin dzieci, jesli sa rodzicami
            }


            for (int i = 0; i < units.size(); i++) {
                long x = AUtoParentId.get(units.get(i)); //odnajdujemy id parenta
                if (x == 0)// jesli id parenta=0
                    continue;
                else {
                    units.get(i).parent = IdtoAdminUnit.get(x); //przypisujemy polu parent znalezionego po id w mapie IdtoAdminUnit parenta;
                    if (units.get(i).parent != null && (units.get(i).density == 0.0 || units.get(i).population == 0.0))
                        fixMissingValues(units.get(i));
                }
            }

        }


    void list(PrintStream out)
    {
        for(int i=0; i<units.size();i++)
         out.print(ToString(units.get(i)));

    }

    void list(PrintStream out,int offset, int limit )
    {
        int max=offset+limit;

        if(max<units.size())
        {
            for(int i=offset; i<max;i++)
                out.print(ToString(units.get(i)));
        }

    }

    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();

        for(AdminUnit i: units)
        {
                if(i.name.matches(pattern))
                {
                    ret.units.add(i);
                    regex=true;
                }
        }
        return ret;
    }

    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance){

        if(unit.adminLevel==8 && unit.bbox.empty==false)
        {
            for(int i=0; i<this.units.size();i++)
            {
                if(this.units.get(i).bbox.empty==false &&this.units.get(i).adminLevel==8 && unit.bbox.distanceTo(units.get(i).bbox)<maxdistance)
                    unit.neighbors.add(units.get(i));


            }
        }
        if(unit.adminLevel<8 && unit.bbox.empty==false)
        {
            for(int i=0; i<units.size();i++)
            {
                if(this.units.get(i).bbox.empty==false && unit.adminLevel==this.units.get(i).adminLevel && unit.bbox.intersects(units.get(i).bbox))
                    unit.neighbors.add(units.get(i));

            }
        }
        if(unit.bbox.empty)
            System.out.println("Bounding Box of "+" "+unit.name+" is empty, because, there were not enough positions in 'CURRENT' array to fill it\n");
        return this;
    }


    AdminUnitList sortInplaceByName() {

        class ByNames implements Comparator<AdminUnit> {

            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                return o1.name.compareTo(o2.name);
            }

        }

        ByNames x= new ByNames();
        this.units.sort(x);

        return this;
    }



    AdminUnitList  sortInplaceByArea(){

        Comparator <AdminUnit> ByArea = new Comparator <AdminUnit>() {

            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                return Double.compare(o1.area, o2.area);
            }
        };

        this.units.sort(ByArea);
        return this;

        }



  AdminUnitList sortInplaceByPopulation(){

      this.units.sort((AdminUnit o1,AdminUnit o2) ->Double.compare(o1.population, o2.population));

      return this;
    }


    AdminUnitList sortInplace(Comparator<AdminUnit> cmp){

        this.units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp){
        // Tworzy wyjściową listę
        // Kopiuje wszystkie jednostki
        // woła sortInPlace
        AdminUnitList x = new AdminUnitList();
        x.units=new ArrayList<>(this.units);
        x.units.sort(cmp);

     return this;
   }


    AdminUnitList filter(Predicate<AdminUnit> pred)
    {
        AdminUnitList u = new AdminUnitList();

        for(int i=0; i<this.units.size();i++)
        {
            if(pred.test(this.units.get(i)))
                u.units.add(this.units.get(i));
        }
        return u;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int limit) {

        int counter=0;

        AdminUnitList u = new AdminUnitList();

        if (limit>this.units.size())
            System.out.println("Number of elements is smaller than limit!\n");

        for(int i=0; i<this.units.size() && counter<limit;i++) {
            if (pred.test(this.units.get(i))) {
                u.units.add(this.units.get(i));
                counter++;
            }
        }

        if (counter < limit)
            System.out.println("Number of elements fullfiling prediacte is smaller than limit!\n");


        return u;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit){

        int counter=0;

        AdminUnitList u = new AdminUnitList();

        if (limit-offset>this.units.size())
            System.out.println("Number of elements is smaller than limit!\n");

        for(int i=0; i<this.units.size() && counter<limit;i++) {
            if (pred.test(this.units.get(i))) {
                counter++;
            }
            if(counter>=offset)
                u.units.add(this.units.get(i));

        }

        if (counter < limit)
            System.out.println("Number of elements fullfiling prediacte is smaller than limit!\n");


        return u;

    }



    public static void main(String[] args) throws IOException {

        AdminUnitList u = new AdminUnitList();
        u.read("admin-units1.csv");

        System.out.println("Wspolrzedne pliku z dokumentu\n");
        u.list(out,0,100);

        for(int i=0; i<50;i++){
            System.out.println(u.units.get(i).name);  //wypisuje LINESTRING dla poszczegolnych plików
            u.units.get(i).bbox.getWKT();
            System.out.println("\n");
        }


        System.out.println("\n");

        double t1 = System.nanoTime()/1e6;
        for(int i=0; i<u.units.size();i++) {
            u.getNeighbors(u.units.get(i), 15.0);

        }
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);


    }



}
