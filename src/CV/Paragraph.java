package CV;

import java.io.PrintStream;

public class Paragraph {

    public String content_;

    Paragraph(String content) {
        content_ = content;
    }

    Paragraph() {}

    Paragraph setContent(String addcontent) {
        content_ = addcontent;
        return this;
    }

    void writeHTML(PrintStream out) {

        content_ = "<p>" + content_ + "</p>";
        out.println(content_);
    }




}


