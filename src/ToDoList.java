import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Task> tasks;

    public ToDoList() {
        tasks = new ArrayList<>();
    }
    public void addTask(Task task) {
        tasks.add(task);
    }
    public List<Task> getTasks() {
        return tasks;
    }
    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            tasks = (List<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public boolean deleteTask(String title) {
        Task taskToDelete = null;

        for (Task t : tasks) {
            if (t.getTitle().equals(title)) {
                taskToDelete = t;
                break;
            }
        }
        if (taskToDelete != null) {
            tasks.remove(taskToDelete);
            return true;
        } else {
            return false;
        }
    }
    public boolean markTaskAsCompleted(String title) {
        for (Task t : tasks) {
            if (t.getTitle().equals(title)) {
                t.setCompleted(true);
                return true;
            }
        }
        return false; // Task not found
    }

    public boolean editTask(String title, String newTitle, String newDescription, String newDueDate, int newPriority, boolean newCompleted) {
        Task taskToEdit = null;

        for (Task t : tasks) {
            if (t.getTitle().equals(title)) {
                taskToEdit = t;
                break;
            }
        }

        if (taskToEdit != null) {
            taskToEdit.setTitle(newTitle);
            taskToEdit.setDescription(newDescription);
            taskToEdit.setDueDate(newDueDate);
            taskToEdit.setPriority(newPriority);
            taskToEdit.setCompleted(newCompleted);

            return true;
        } else {
            return false;
        }
    }
}
