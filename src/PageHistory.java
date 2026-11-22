import java.util.ArrayDeque;
import java.util.Deque;

public class PageHistory {

    private Deque<PageMemento> history = new ArrayDeque<>();


    public void add(PageMemento memento) {
        history.push(memento);
    }

    public PageMemento undo() {
        if (history.isEmpty()) {
            return null;
        }

        return history.pop();
    }
}
