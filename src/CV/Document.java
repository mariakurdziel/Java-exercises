package CV;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Document {

    String title_;
    Photo photo;
    List<Section> sections = new ArrayList<>();


    public void write(String fileName) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            FileWriter writer = new FileWriter(fileName);
            ;
            m.marshal(this, writer);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       }

    public static Document read(String fileName){
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            //Unmarshaller m = jc.createUnmarshaller();
            FileReader reader = new FileReader(fileName);
            //return (Document) m.unmarshal(reader);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

        Document(String title){
        this.title_=title;
       }

    Document setTitle(String title){
        this.title_ = title;
        return this;
    }

    Document addPhoto(String photoUrl){
        Photo p = new Photo(photoUrl);
        photo=p;
        return this;
    }

    Section addSection(String sectionTitle){
        // utwórz sekcję o danym tytule i dodaj do sections
        Section x = new Section();
        x.setTitle(sectionTitle);
        this.sections.add(x);
        return x;
    }
    Document addSection(Section s){
        this.sections.add(s);
        return this;
    }

    void writeHTML(PrintStream out){
        // zapisz niezbędne znaczniki HTML
        out.println("<?xml version=\"1.0\"?>\n" +
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n" +
                "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "<title>"+title_+"</title>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml;\n" +
                "charset=UTF-8\" /></head>");
        out.println("<body>");
        out.println("<h1>"+title_+"</h1>");
        out.println("</body>");
        this.photo.writeHTML(out);

        for(int i=0; i<sections.size();i++)
        {
            Section x=sections.get(i);
            x.writeHTML(out);
        }
        out.println("</body></html>");
        // dodaj tytuł i obrazek
        // dla każdej sekcji wywołaj section.writeHTML(out)
    }

    public static void main(String[] args)
    {

        Document cv = new Document("Jan Kowalski - CV");
        cv.addPhoto("...");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph("...");
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Umiejętności")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                );

        cv.writeHTML(System.out);
        try {
            cv.writeHTML(new PrintStream("cv.html","UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


}
