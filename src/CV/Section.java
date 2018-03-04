package CV;

import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;

public class Section {
    String title;
    List<Paragraph> paragraphs = new ArrayList<>() ;

    Section setTitle(String title)
    {
        this.title=title;
        return this;
    }

    Section addParagraph(String paragraphText)
    {
        Paragraph x = new Paragraph(paragraphText);
        this.paragraphs.add(x);
        return this;
    }

    Section addParagraph(Paragraph p){
        this.paragraphs.add(p);
        return this;

    }
    void writeHTML(PrintStream out){

        for(int i=0; i<paragraphs.size();i++)
        {
            Paragraph x=paragraphs.get(i);
            x.writeHTML(out);
        }

    }
}
