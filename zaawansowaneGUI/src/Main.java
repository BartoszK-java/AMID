import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
public class Main extends JFrame{
    private TaskTableModel model;
    private JTable table;
    public Main() {
        super("To-Do List Manager");
        model = new TaskTableModel();
        table = new JTable(model);

        table.getColumnModel().getColumn(1).setCellRenderer(new StatusRenderer());
        table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        table.getColumnModel().getColumn(2).setCellRenderer(new PriorityRenderer());
        JPanel controlPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JButton addButton = new JButton("Dodaj Zadanie");
        JButton removeButton = new JButton("Usuń Wybrane");1

        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        addButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Podaj nazwę zadania:");
            if (name != null && !name.trim().isEmpty()) {
                String[] options = {"Niski", "Średni", "Wysoki"};
                String priority = (String) JOptionPane.showInputDialog(this, "Wybierz priorytet:",
                        "Priorytet", JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (priority != null)
                    model.addTask(new Task(name, false, priority));
            }
        });
        removeButton.addActionListener(e -> {
            int[] selected = table.getSelectedRows();
            for (int i = selected.length - 1; i >= 0; i--) {
                model.removeTask(selected[i]);
            }
        });
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                controlPanel, new JScrollPane(table));
        splitPane.setDividerLocation(150);
        add(splitPane);
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    static class StatusRenderer extends JCheckBox implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setSelected(value != null && (Boolean) value);
            setHorizontalAlignment(CENTER);
            return this;
        }
    }
    static class PriorityRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int col) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            String priority = (String) value;
            if (!isSelected) {
                if ("Wysoki".equalsIgnoreCase(priority)) {
                    c.setBackground(Color.PINK);
                } else {
                    c.setBackground(Color.WHITE);
                }
            }
            return c;
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}