import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class KsiegozbiorApp extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private JTextField tytulField;
    private JTextField autorField;
    private JTextField rokField;

    private Connection conn;

    public KsiegozbiorApp() {
        setTitle("Księgozbiór");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        connectDB();

        model = new DefaultTableModel(new String[]{"ID", "Tytuł", "Autor", "Rok"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(4, 2));

        bottomPanel.add(new JLabel("Tytuł:"));
        tytulField = new JTextField();
        bottomPanel.add(tytulField);

        bottomPanel.add(new JLabel("Autor:"));
        autorField = new JTextField();
        bottomPanel.add(autorField);

        bottomPanel.add(new JLabel("Rok wydania:"));
        rokField = new JTextField();
        bottomPanel.add(rokField);

        JPanel buttonPanel = new JPanel();
        JButton addBtn = new JButton("Dodaj");
        JButton deleteBtn = new JButton("Usuń");
        JButton updateBtn = new JButton("Aktualizuj");

        buttonPanel.add(addBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(updateBtn);

        add(buttonPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> addBook());
        deleteBtn.addActionListener(e -> deleteBook());
        updateBtn.addActionListener(e -> updateBook());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                tytulField.setText(model.getValueAt(row, 1).toString());
                autorField.setText(model.getValueAt(row, 2).toString());
                rokField.setText(model.getValueAt(row, 3).toString());
            }
        });

        refreshTable();
        setVisible(true);
    }

    private void connectDB() {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sklep",
                    "root",
                    ""
            );
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd połączenia");
        }
    }

    private void refreshTable() {
        try {
            model.setRowCount(0);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ksiazki");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("tytul"),
                        rs.getString("autor"),
                        rs.getInt("rok_wydania")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addBook() {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO ksiazki (tytul, autor, rok_wydania) VALUES (?, ?, ?)"
            );
            ps.setString(1, tytulField.getText());
            ps.setString(2, autorField.getText());
            ps.setInt(3, Integer.parseInt(rokField.getText()));
            ps.executeUpdate();
            refreshTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Błąd");
        }
    }

    private void deleteBook() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        int id = (int) model.getValueAt(row, 0);

        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM ksiazki WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            refreshTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateBook() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        int id = (int) model.getValueAt(row, 0);

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE ksiazki SET tytul = ?, autor = ?, rok_wydania = ? WHERE id = ?"
            );
            ps.setString(1, tytulField.getText());
            ps.setString(2, autorField.getText());
            ps.setInt(3, Integer.parseInt(rokField.getText()));
            ps.setInt(4, id);
            ps.executeUpdate();
            refreshTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KsiegozbiorApp::new);
    }
}
