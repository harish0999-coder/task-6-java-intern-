import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoApp extends JFrame implements ActionListener {

    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskInputField;
    private JButton addButton;
    private JButton deleteButton;

    public TodoApp() {
        setTitle("Java Swing To-Do App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(new BorderLayout(10, 10)); 

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(5, 5));
        
        taskInputField = new JTextField(25);
        inputPanel.add(taskInputField, BorderLayout.CENTER);
        
        addButton = new JButton("Add Task");
        addButton.addActionListener(this); 
        inputPanel.add(addButton, BorderLayout.EAST);
        
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        add(inputPanel, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList); 
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        deleteButton = new JButton("Delete Selected Task");
        deleteButton.addActionListener(this);
        controlPanel.add(deleteButton);
        
        controlPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addTask();
        } else if (e.getSource() == deleteButton) {
            deleteTask();
        }
    }
    
    private void addTask() {
        String task = taskInputField.getText().trim();
        if (!task.isEmpty()) {
            listModel.addElement(task);
            taskInputField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a task description.", "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to delete.", "Selection Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TodoApp());
    }
}
