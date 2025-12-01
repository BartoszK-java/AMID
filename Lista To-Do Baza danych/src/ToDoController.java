import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ToDoController {

    private ToDoView view;
    private ZadanieDAO dao;

    public ToDoController(ToDoView view) {
        this.view = view;
        this.dao = new ZadanieDAO();

        odswiezListe();

        view.przyciskDodaj.addActionListener(new AddListener());
    }

    public void odswiezListe() {
        List<Zadanie> listaZadan = dao.pobierzWszystkie();

        view.modelListy.clear();
        for (Zadanie z : listaZadan) {
            view.modelListy.addElement(z);
        }
    }

    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tekst = view.poleTekstowe.getText().trim();
            if (!tekst.isEmpty()) {
                dao.dodaj(new Zadanie(tekst));
                odswiezListe();
                view.poleTekstowe.setText("");
            }
        }
    }
}
