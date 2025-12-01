public class Main {
    public static void main(String[] args) {
        ToDoView view = new ToDoView();
        new ToDoController(view);
        view.setVisible(true);
    }
}
