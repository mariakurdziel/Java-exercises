package CVSReader;
import CVSReader.CSVReader;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVReader {

    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    String  filename_;
    public String [] current;
    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    public Map<String,Integer> columnLabelsToInt = new HashMap<>();

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException
    {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        this.filename_ =filename;

        if (hasHeader) parseHeader();
    }

    CSVReader(String filename, String delimiter)
    {
        this.delimiter = delimiter;
        this.filename_=filename;
    }

    CSVReader(String filename)
    {
        this.filename_=filename;
    }

    void parseHeader() throws IOException
    {
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        // podziel na pola
        String[] header = line.split(delimiter);  // przetwarzaj dane w wierszu
       for (int i = 0; i < header.length; i++) {




            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i],i+1);  // dodaj nazwy kolumn do columnLabels i numery do columnLabelsToInt

        }
    }

    public boolean next() throws IOException {
        String line2 = reader.readLine();
        if (line2 == null) {
            return false;
        }
        // podziel na pola
        current = line2.split(delimiter+"(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        return true;
    }

    List<String> getColumnLabels()
    {
        return this.columnLabels;
    }

    int getRecordLength()
    {
        return this.current.length;
    }

    public boolean isMissing(int columnIndex)
    {
        if(current[columnIndex].isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean isMissing(String columnLabel)
    {
        for(int i=0; i<columnLabels.size();i++)
        {
                if (columnLabels.get(i) == columnLabel && current[i].isEmpty()) {

                    return true;

                }
        }
        return false;
    }

    public String get(int columnIndex)
    {
        return current[columnIndex];
    }
    public String get(String columnLabel)
    {
        return current[columnLabelsToInt.get(columnLabel)];
    }
    public int getInt(int columnIndex){return Integer.parseInt(current[columnIndex]);}
    public int getInt2(String columnLabel) {return Integer.parseInt(current[columnLabelsToInt.get(columnLabel)-1]);}
    public long getLong(int columnIndex){return Long.valueOf(current[columnIndex]);}
    public long getLong(String columnLabel){return Long.valueOf(current[columnLabelsToInt.get(columnLabel)-1]);}
    public double getDouble(int columnIndex) {return Double.parseDouble(current[columnIndex]);}
    public double getInt(String columnLabel){return (double)Integer.parseInt(current[columnLabelsToInt.get(columnLabel)-1]);}

    void getTime(int Columnindex,String format)
    {
        LocalTime time = LocalTime.parse(columnLabels.get(Columnindex), DateTimeFormatter.ofPattern(format));
        System.out.println(time);
    }

    void getDate(String text, String pattern)
    {
        LocalDate date = LocalDate.parse(text, DateTimeFormatter.ofPattern(pattern));
        System.out.println(date);
    }




    public static void main(String[] args) throws IOException {

       CSVReader reader=new CSVReader("titanic-part.csv",",",true);

        while(reader.next()){
            int id = reader.getInt(0);
            String name = reader.get(3);
           double fare = reader.getDouble(9);
           System.out.printf("%d %s %f",id, name, fare);
           System.out.println(id+" "+name+" "+fare);

       }

    }




}