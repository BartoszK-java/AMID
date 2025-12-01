import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KwadratModel model = new KwadratModel();

            KwadratView view = new KwadratView();

            KwadratController controller = new KwadratController(model, view);

            view.setVisible(true);
        });
    }
}