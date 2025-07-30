package TaskManager.Classes;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskManager {
    private static ArrayList<Task> currentTasks = new ArrayList<>();

    public static ArrayList<Task> getCurrentTasks() {
        return currentTasks;
    }

    public static void addTask(Task task) {
        currentTasks.add(task);
    }

    public static void addTasks(ArrayList<Task> tasks) {
        currentTasks.addAll(tasks);
    }

    public static void removeTask(Task task) {
        currentTasks.remove(task);
    }

    public static void removeTasks(ArrayList<Task> tasks) {
        currentTasks.removeAll(tasks);
    }

    public static void editTaskById(int id, TASK_DATA editData, String newData) throws  NumberFormatException{
        Optional<Task> oldTask = getTaskById(id);
        if (oldTask.isEmpty()) {
            return;
        }
        switch (editData){
            case NAME:
                oldTask.get().setName(newData);
                break;
            case DESCRIPTION:
                oldTask.get().setDescription(newData);
                break;
            case PRIORITY:
                try {
                    oldTask.get().setPriority(Integer.parseInt(newData));
                }
                catch (NumberFormatException e) {
                    throw new NumberFormatException();
                }
                break;
            case LENGTH_TIME:
                try {
                    oldTask.get().setLengthTime(Integer.parseInt(newData));
                }
                catch (NumberFormatException e) {
                    throw new NumberFormatException();
                }
                break;
            case STATUS:
                oldTask.get().setStatus(true);
                break;
        }

    }

    public static ArrayList<Task> getTasks() {
        return currentTasks;
    }

    public static Optional<Task> getTaskById(int id){
        return currentTasks.stream().filter(x->x.getId() == id).findFirst();
    }

    public static Optional<ArrayList<Task>> getLongestTasks(){
        if (!currentTasks.isEmpty()){ return Optional.empty();}

        int longestTime = currentTasks
                .stream()
                .map(x->x.getLengthTime())
                .max(Integer::compareTo).get();

        return Optional.of(currentTasks
                .stream()
                .filter(x->x.getLengthTime() == longestTime)
                .collect(Collectors.toCollection(ArrayList::new)));
    }

}
