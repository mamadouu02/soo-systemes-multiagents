package Event;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;

public class EventManager {

    private long currentDate;
    private final Map<Long, Queue<Event>> events;

    public EventManager() {
        this.events = new HashMap<>();
    }

    public void next() {
        this.currentDate++;

        if (events.containsKey(this.currentDate)) {
            Queue<Event> file = events.get(this.currentDate);
            Event e;

            while (!file.isEmpty()) {
                e = file.remove();
                e.execute();
            }

            events.remove(this.currentDate);
        }
    }

    public boolean isFinished() {
        return this.events.isEmpty();
    }

    public void restart() {
        this.currentDate = 0;
        this.events.clear();
    }

    public void addEvent(Event event) {
        if (events.containsKey(event.getDate())) {
            events.get(event.getDate()).add(event);
        } else {
            Queue<Event> file = new LinkedList<>();
            file.add(event);
            events.put(event.getDate(), file);
        }
    }
}
