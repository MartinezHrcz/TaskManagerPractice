package TaskManager.Classes;

public class Task{
    private static int idCounter = 0;
    private int Id;
    private String Name;
    private String Description;
    private int Priority;
    private int LengthTime;
    private boolean Status;

    public Task(String name, String description, int priority, int lengthTime) {
        Id = idCounter;
        Name = name;
        Description = description;
        Priority = priority;
        LengthTime = lengthTime*60;
        Status = false;
        idCounter++;
    }

    public Task(String name, String description, int priority) {
        Id = idCounter;
        Name = name;
        Description = description;
        Priority = priority;
        Status = false;
        idCounter++;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public int getPriority() {
        return Priority;
    }

    public int getLengthTime() {
        return LengthTime;
    }

    public int getLengthTimeInMinutes() {
        return LengthTime / 60;
    }

    public int getLengthTimeInHours() {
        return LengthTime / 3600;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public void setLengthTime(int lengthTime) {
        LengthTime = lengthTime;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("Id=").append(Id);
        sb.append(", Name='").append(Name).append('\'');
        sb.append(", Description='").append(Description).append('\'');
        sb.append(", Priority=").append(Priority);
        sb.append(", LengthTime=").append(LengthTime);
        sb.append(", Status=").append(Status);
        return sb.toString();
    }
}
