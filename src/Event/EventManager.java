package event;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;

public class EventManager {

    private long currentDate;

    // On choisit une table de hachage pour gérer les évènements : à chaque date (un long) est associée
    // une file d'évènements qui doivent s'éxécuter à cette date.
    private final Map<Long, Queue<Event>> events;

    public EventManager() {
        this.events = new HashMap<>();
    }

    public void next() {
        this.currentDate++;

        if (events.containsKey(this.currentDate)) {
            Queue<Event> file = events.get(this.currentDate);
            Event e;

            // On vide et exécute la file d'évènements pour la date voulue.
            while (!file.isEmpty()) {
                e = file.remove();
                e.execute();
            }

            // Plus aucun évènement attendu à cette date, on peut ainsi supprimer la clé de la table de hachage.
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
