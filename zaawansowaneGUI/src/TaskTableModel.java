
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TaskTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Nazwa", "Status", "Priorytet"};
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
        fireTableRowsInserted(tasks.size() - 1, tasks.size() - 1);
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Task t = tasks.get(row);
        switch (col) {
            case 0: return t.getName();
            case 1: return t.isDone();
            case 2: return t.getPriority();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 1; // tylko kolumna Status edytowalna
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        if (col == 1 && value instanceof Boolean) {
            tasks.get(row).setDone((Boolean) value);
            fireTableCellUpdated(row, col);
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        if (col == 1) return Boolean.class;
        return String.class;
    }
}
