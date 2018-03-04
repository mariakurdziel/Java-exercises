package CV;
import java.io.PrintStream;

public class ListItem {
    public String content_;

    ListItem(String content)
    {
        content_=content;
    }

    void writeHTML(PrintStream out)
    {
        out.println("<li>"+content_+"</li>");
    }
}
