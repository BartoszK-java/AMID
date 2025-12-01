import javax.swing.*;
import java.awt.*;

public class ToDoView extends JFrame {

    public JTextField poleTekstowe = new JTextField(20);
    public JButton przyciskDodaj = new JButton("Dodaj");
    public DefaultListModel<Zadanie> modelListy = new DefaultListModel<>();
    public JList<Zadanie> lista = new JList<>(modelListy);

    public ToDoView() {
        setTitle("ToDo App");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.add(poleTekstowe);
        panel.add(przyciskDodaj);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(lista), BorderLayout.CENTER);
    }
}
