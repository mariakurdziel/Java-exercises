package kolokwium;

import CVSReader.CSVReader;
import wpgm.AdminUnit;
import wpgm.AdminUnitList;

import java.io.IOException;
import java.util.*;

import static java.lang.System.out;

public class listsofnames {

    List<String> names = new ArrayList<>();
    List<Dane> dane = new ArrayList<>();
    void popularity()
    {
        String k;
        String m;
        String n;
        int maxk=0;
        int maxm=0;


   for(int i=2000; i<=2016;i++)
        {
              for(int j=0; j<dane.size();j++)
              {
                  if(dane.get(j).year==i && dane.get(j).name.charAt(dane.get(j).name.length()-1)=='a' && dane.get(j).number>maxk)
                  {
                      maxk=dane.get(j).number;
                      k=dane.get(j).name;
                  }
                  if(dane.get(j).year==i && dane.get(j).name.charAt(dane.get(j).name.length()-1)!='a' && dane.get(j).number>maxm)
                  {
                      maxm=dane.get(j).number;
                      m=dane.get(j).name;
                  }

              }

              System.out.println("W roku " + i +" najpopularniejszym imieniem damskim było imie "+k+" a meskim imie "+m+".\n");
              maxm=0;
              maxk=0;
        }
    }

    void bornchilds()
    {
        int numberofchildren=0;

        for(int i=2000; i<=2016;i++)
        {
            for(int j=0; j<dane.size();j++)
            {
                if(dane.get(j).year==i)
                {
                    numberofchildren+=dane.get(j).number;
                }
            }
            System.out.println("W roku "+Integer.toString(i)+" przyszlo na swiat"+ Integer.toString(numberofchildren)+ "dzieci.\n");
            numberofchildren=0;
        }
    }

    double przyrost()
    {
        List<Integer> yearsofname = new ArrayList<>();
        for(int i=0; i<names.size();i++)
        {
            for(int j=0; j<dane.size();j++)
            {
                if(names.get(i)==dane.get(j).name)
                    yearsofname.add(dane.get(j).year);
            }
        }

    }

    void read(String filename) throws IOException {

        CSVReader reader = new CSVReader(filename, ",", true);

        while (reader.next())

        {
            Dane d=new Dane();
            d.name=reader.get(1);
            d.year=Integer.parseInt(reader.get(0));
            d.number=Integer.parseInt(reader.get(2));
            d.sex=reader.get(3);

            dane.add(d);
            names.add(d.name);




        }
        }


    public static void main(String[] args) throws IOException {

         listsofnames u = new listsofnames();
        u.read("imiona-2000-2016.csv");



    }
}
