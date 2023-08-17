import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();

        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Save Tasks");
            System.out.println("4. Load Tasks");
            System.out.println("5. Delete Tasks");
            System.out.println("6. Complete Tasks");
            System.out.println("7. Edit Tasks");
            System.out.println("0. Exit");
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.println("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.println("Enter due date; ");
                    String dueDate = scanner.nextLine();
                    System.out.println("Enter priority (1-5): ");
                    int priority = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Task task = new Task(title, description, dueDate, priority);
                    toDoList.addTask(task);
                    break;
                case 2:
                    List<Task> tasks = toDoList.getTasks();
                    for (Task t : tasks) {
                        System.out.println("Task Title: '" + t.getTitle() + "' Description: '" + t.getDescription() + "' Due Date: " + t.getDueDate() + " Priority: " + t.getPriority() + " Completed: " + t.getCompletedStatus());
                    }
                    break;
                case 3:
                    toDoList.saveToFile("tasks.dat");
                    System.out.println("Tasks saved");
                    break;
                case 4:
                    toDoList.loadFromFile("tasks.dat");
                    System.out.println("tasks loaded");
                    break;
                case 5:
                    System.out.println("Enter task title to delete: ");
                    String deleteTitle = scanner.nextLine();
                    boolean taskDeleted = toDoList.deleteTask(deleteTitle);

                    if (taskDeleted) {
                        System.out.println("Task '" + deleteTitle + "' deleted.");
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;
                case 6:
                    System.out.println("Enter task title to mark complete: ");
                    String completedTitle = scanner.nextLine();
                    boolean taskCompleted = toDoList.markTaskAsCompleted(completedTitle);

                    if (taskCompleted) {
                        System.out.println("Task '" + completedTitle + "' marked as completed.");
                    } else {
                        System.out.println("Task not found");
                    }
                    break;
                case 7:
                    System.out.println("Enter task title to edit: ");
                    String editTitle = scanner.nextLine();

                    System.out.print("Enter new task title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new task description: ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Enter new due date: ");
                    String newDueDate = scanner.nextLine();
                    System.out.print("Enter new priority (1-5): ");
                    int newPriority = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Is the task completed? (true/false): ");
                    boolean newCompleted = scanner.nextBoolean();
                    scanner.nextLine();

                    boolean taskEdited = toDoList.editTask(editTitle, newTitle, newDescription, newDueDate, newPriority, newCompleted);
                    if (taskEdited) {
                        System.out.println("Task '" + editTitle + "' has been updated to: '" + newTitle + "'.");
                    } else {
                        System.out.println("Task not found");
                    }
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                System.out.println("Invalid choice");
            }
        }
    }
}