package TaskManager.Utils;

import TaskManager.Classes.TaskManager;

public class MenuUtils {
     private final static String MAIN_MENU = """
            Task Manager
            ------------------------
            1.Add new task
            2.Remove task
            3.Edit task
            4.Show all tasks
            5.Quit
            ------------------------
            """;
     private static String ADD_NEW_TASK = """
             Add new task
             ------------------------
             To add a new task: name; description; priority(1-10); length in minutes
             ------------------------
             """;
     private static String REMOVE_TASK = """
             Remove task
             ------------------------
             To remove a task just type in the ID of the task!
             """;
     private static String EDIT_TASK = """
             Edit task
             ------------------------
             Options: 
             1-name, 2-desciption, 3-priority(1-10), 4-length in minutes
             To edit a task: ID-OptionNumber-changes
             """;
     private static String SHOW_TASKS = """
             All tasks
             ------------------------
             """;

     public static String getMainMenu() {
         return MAIN_MENU;
     }

    public static String getAddNewTask() {
        return ADD_NEW_TASK;
    }

    public static String getRemoveTask() {
        return REMOVE_TASK;
    }

    public static String getEditTask() {
        return EDIT_TASK;
    }

    public static String getShowTasks() {
        StringBuilder currentTasks = new StringBuilder();
        currentTasks.append(SHOW_TASKS);
        TaskManager.getTasks().forEach(task -> {
            currentTasks.append("\n"+task.toString());
        });
        currentTasks.append("\n------------------------");
        return currentTasks.toString();
    }
}
