import java.util.ArrayList;
import java.util.List;
import Tags.Tag;

public class PageMemento {

    private final String title;
    private final String author;
    private final List<Tag> content;

    public PageMemento(String title, String author, List<Tag> content) {
        this.title = title;
        this.author = author;
        this.content = new ArrayList<>();

        for (Tag tag : content) {
            this.content.add(tag.copy());
        }
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<Tag> getContent() {
        List<Tag> copie = new ArrayList<>();

        for (Tag tag : content) {
            copie.add(tag.copy());
        }

        return copie;
    }
}