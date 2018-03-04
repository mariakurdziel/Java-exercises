package CV;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {

    List<ListItem> listitems = new ArrayList<>() ;

    void addItem(String x){
        ListItem n=new ListItem(x);
        listitems.add(n);
    }

}
