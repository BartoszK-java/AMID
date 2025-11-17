
public class Task {
    private String name;
    private boolean done;
    private String priority;

    public Task(String name, boolean done, String priority) {
        this.name = name;
        this.done = done;
        this.priority = priority;
    }

    public String getName() { return name; }
    public boolean isDone() { return done; }
    public String getPriority() { return priority; }

    public void setDone(boolean done) { this.done = done; }
}
