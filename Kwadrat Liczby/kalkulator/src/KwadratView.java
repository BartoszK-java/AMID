import javax.swing.*;
import java.awt.event.ActionListener;

public class KwadratView extends JFrame {

    private JTextField liczbaField = new JTextField(10);
    private JButton obliczButton = new JButton("Oblicz");
    private JLabel wynikLabel = new JLabel("Wynik: ");

    public KwadratView() {
        super("Kalkulator kwadratu - MVC");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 150);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Podaj liczbę:"));
        panel.add(liczbaField);
        panel.add(obliczButton);
        panel.add(wynikLabel);

        this.add(panel);
    }

    public String getLiczbaTekst() {
        return liczbaField.getText();
    }

    public void setWynik(int wynik) {
        wynikLabel.setText("Wynik: " + wynik);
    }

    public void addObliczListener(ActionListener listener) {
        obliczButton.addActionListener(listener);
    }

    public void pokazBlad(String komunikat) {
        JOptionPane.showMessageDialog(this, komunikat, "Błąd", JOptionPane.ERROR_MESSAGE);
    }
}
