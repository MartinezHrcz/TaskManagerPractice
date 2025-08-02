package TaskManager;

import TaskManager.Classes.Exceptions.TaskManipulationException;
import TaskManager.Classes.TASK_DATA;
import TaskManager.Classes.Task;
import TaskManager.Classes.TaskManager;
import TaskManager.Utils.MenuUtils;

import java.io.InputStreamReader;
import java.util.Scanner;


public class Runner {
    public static void main(String[] args) {
        String input;
        do {
            System.out.print(MenuUtils.getMainMenu());
            input = System.console().readLine();
            switch (input) {
                case "1":
                    AddNewTask();
                    break;
                case "2":
                    RemoveTask();
                    break;
                case "3":
                    EditTask();
                    break;
                case "4":
                    ShowTasks();
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }

        }
        while (!input.trim().equals("5"));{
            System.out.println("Goodbye!");
        }
    }
    private static void AddNewTask() {
        Scanner scanner = new Scanner(System.in);
        String taskInput;

        boolean validEdit = false;
        do {
            System.out.println(MenuUtils.getShowTasks());
            System.out.println(MenuUtils.getAddNewTask());

            taskInput = scanner.nextLine().trim();
            if (taskInput.split(";").length != 4) {
                System.out.println("Invalid input! (There should be 4 ';' separators)\nPress any key to continue");
                scanner.next();
                continue;
            }
            try{
                Integer.parseInt(taskInput.split(";")[2].strip());
                Integer.parseInt(taskInput.split(";")[3].strip());
            }
            catch (NumberFormatException e){
                System.out.println("Invalid input! (Non-numeric arguments!)\nPress any key to continue");
                scanner.next();
                continue;
            }
            validEdit = true;
        }while (!validEdit);
        String[] taskInfo = taskInput.split(";");
        Task taskToBeAdded = new Task(taskInfo[0],taskInfo[1],Integer.parseInt(taskInfo[2]),Integer.parseInt(taskInfo[3]));
        TaskManager.addTask(taskToBeAdded);
        System.out.println(taskToBeAdded.toString());
        System.out.println(
                "Task added successfully!" +
                "\nPress any key to continue");
        scanner.nextLine();
    }

    private static void RemoveTask() {
        Scanner scanner = new Scanner(System.in);
        String Input;
        int InputID = 0;
        boolean validID = false;

        do {
            System.out.println(MenuUtils.getShowTasks());
            System.out.println(MenuUtils.getRemoveTask());
            Input = scanner.nextLine().strip();

            /* Add quitting from menu options
            if (Input.equalsIgnoreCase("q")) {
                System.out.println("Aborting ");
            }
            */
            try{
                InputID = Integer.parseInt(Input);
            }
            catch (NumberFormatException e){
                System.out.println("Invalid input! Non-numeric arguments!\nPress any key to continue");
                scanner.nextLine();
                continue;
            }

            if (TaskManager.getTaskById(InputID).isEmpty()) {
                System.out.println("Task does not exist!\nPress any key to continue");
                scanner.nextLine();
                continue;
            }
            validID = true;
        }
        while (!validID);

        try {
            TaskManager.removeTaskByID(InputID);
            System.out.println("Task removed successfully!\nPress any key to continue");
            scanner.nextLine();
        }
        catch (TaskManipulationException e){
            System.out.println("Remove task failed!\nPress any key to continue");
            scanner.nextLine();
        }


    }

    private static void EditTask() {
        Scanner scanner = new Scanner(System.in);
        String[] taskInput;

        boolean validEdit = false;
        do {
            System.out.println(MenuUtils.getEditTask());
            taskInput = scanner.nextLine().strip().split("-");
            int taskId;
            if (taskInput.length != 3) {
                System.out.println("Invalid input! (Invalid number of arguments) \nPress any key to continue");
                scanner.next();
                continue;
            }

            if (TaskManager.getTaskById(Integer.parseInt(taskInput[0].strip())).isEmpty()) {
                System.out.println("Invalid input! (id does not exist)\nPress any key to continue)");
                scanner.next();
                continue;
            }

            try{
                taskId = Integer.parseInt(taskInput[0].strip());
            }
            catch (NumberFormatException e){
                System.out.println("Invalid input! (Non-numeric id)\nPress any key to continue");
                scanner.next();
                continue;
            }

            int dataToBeEdited;
            try{
                dataToBeEdited = Integer.parseInt(taskInput[1]);
            }
            catch (NumberFormatException e){
                System.out.println("Invalid option! (Non-numeric arguments, 1-4)\nPress any key to continue");
                scanner.next();
                continue;
            }

            if (dataToBeEdited ==3 || dataToBeEdited == 4) {
                try{
                    Integer.parseInt(taskInput[2].strip());
                }
                catch (NumberFormatException e){
                    System.out.println("Invalid option! (Non-numeric information)\nPress any key to continue");
                    scanner.next();
                    continue;
                }
            }

            switch (dataToBeEdited){
                case 1:
                    TaskManager.editTaskById(taskId, TASK_DATA.NAME, taskInput[2]);
                    break;
                case 2:
                    TaskManager.editTaskById(taskId, TASK_DATA.DESCRIPTION, taskInput[2]);
                    break;
                case 3:
                    TaskManager.editTaskById(taskId, TASK_DATA.PRIORITY, taskInput[2]);
                    break;
                case 4:
                    TaskManager.editTaskById(taskId, TASK_DATA.LENGTH_TIME, taskInput[2]);
                    break;
                default:
                    System.out.println("Invalid option! (Option does not exists)\nPress any key to continue");
                    scanner.next();
                    continue;
            }

            validEdit = true;
        }
        while (!validEdit);
        System.out.println("Task edited successfully!\nPress any key to continue");
        scanner.nextLine();
    }

    private static void ShowTasks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(MenuUtils.getShowTasks());
        System.out.println("\nPress any key to continue");
        scanner.nextLine();
    }

}
