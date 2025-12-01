import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KwadratController {

    private KwadratModel model;
    private KwadratView view;

    public KwadratController(KwadratModel model, KwadratView view) {
        this.model = model;
        this.view = view;

        this.view.addObliczListener(new ObliczListener());
    }

    class ObliczListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int liczba = Integer.parseInt(view.getLiczbaTekst());
                int wynik = model.obliczKwadrat(liczba);
                view.setWynik(wynik);
            } catch (NumberFormatException ex) {
                view.pokazBlad("Wprowadź poprawną liczbę całkowitą!");
            }
        }
    }
}
