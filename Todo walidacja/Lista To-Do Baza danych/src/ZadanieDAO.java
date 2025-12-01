import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ZadanieDAO {

    private Connection conn;

    public ZadanieDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ToDo?useSSL=false&serverTimezone=UTC",
                    "root",
                    ""
            );
            System.out.println("Połączono z bazą.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Zadanie> pobierzWszystkie() {
        List<Zadanie> lista = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM zadania");

            while (rs.next()) {
                lista.add(new Zadanie(
                        rs.getInt("id"),
                        rs.getString("nazwa")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void dodaj(Zadanie zad) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO zadania(nazwa) VALUES (?)"
            );
            ps.setString(1, zad.getNazwa());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}