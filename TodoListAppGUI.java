import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        return description + (isCompleted ? " (Completed)" : " (Pending)");
    }
}


public class TodoListAppGUI extends JFrame {
    private ArrayList<Task> tasks;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;

    public TodoListAppGUI() {
        tasks = new ArrayList<>();
        taskListModel = new DefaultListModel<>();

      
        setTitle("To-Do List Application");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

       
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        JButton addButton = new JButton("Add Task");
        JButton completeButton = new JButton("Mark Completed");
        JButton removeButton = new JButton("Remove Task");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(addButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(exitButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add task button action
        addButton.addActionListener(e -> {
            String taskDescription = JOptionPane.showInputDialog(this, "Enter task description:");
            if (taskDescription != null && !taskDescription.trim().isEmpty()) {
                addTask(taskDescription);
            }
        });

        // Complete task button action
        completeButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                markTaskAsCompleted(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a task to mark as completed.");
            }
        });

        // Remove task button action
        removeButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                removeTask(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a task to remove.");
            }
        });

        // Exit button action
        exitButton.addActionListener(e -> System.exit(0));

        // Add main panel to frame
        add(mainPanel);
    }

    // Add task
    private void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        taskListModel.addElement(task.toString());
    }

    // Mark task as completed
    private void markTaskAsCompleted(int index) {
        Task task = tasks.get(index);
        task.markAsCompleted();
        taskListModel.set(index, task.toString());
    }

    // Remove task
    private void removeTask(int index) {
        tasks.remove(index);
        taskListModel.remove(index);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TodoListAppGUI app = new TodoListAppGUI();
            app.setVisible(true);
        });
    }
}
