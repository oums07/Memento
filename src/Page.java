import Tags.Tag;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private  String title;
    private  String author;
    private  List<Tag> content = new ArrayList<>();

    public Page(String title, String author){
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String _title){
        this.title = _title;
    }
    public void addTag(Tag tag){
        this.content.add(tag);
    }

    public void removeTag(Tag tag){
        this.content.remove(tag);
    }

    public String toHTML() {
        StringBuffer htmlBuffer = new StringBuffer("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>" + this.title + "</title>\n" +
                "</head>\n" +
                "<body>\n");

        for (Tag tag : content) {
            htmlBuffer.append("    ").append(tag.toHTML()).append("\n");
        }
        htmlBuffer.append("</body>\n" +
                "</html>");

        return htmlBuffer.toString();
    }
    public PageMemento save() {
    return new PageMemento(title, author, content);
    }

    public void restore(PageMemento memento) {
        this.title = memento.getTitle();
        this.author = memento.getAuthor();

        this.content.clear();

        for (Tag tag : memento.getContent()) {
            this.content.add(tag.copy());
        }
    }
}
