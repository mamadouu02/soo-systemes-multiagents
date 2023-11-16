package Event;

public abstract class Event {

    private long date;

    public Event(long date) {
        this.date = date;
    }

    public long getDate() {
        return this.date;
    }

    abstract public void execute();
}
