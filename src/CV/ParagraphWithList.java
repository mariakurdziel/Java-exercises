package CV;

import java.io.PrintStream;

public class ParagraphWithList extends Paragraph{
    UnorderedList ul_;

    ParagraphWithList(){}

    ParagraphWithList setContent(String addcontent) {
        content_ = addcontent;
        return this;
    }

    public ParagraphWithList addListItem(String x){
      ul_.addItem(x);
        return this;
    }
    @Override
    void writeHTML(PrintStream out){
        out.printf("<p><ul>");
        for(int i=0;i<ul_.listitems.size();i++) {
            ul_.listitems.get(i).writeHTML(out);
        }
        out.printf("</ul></p>");
        }
}
