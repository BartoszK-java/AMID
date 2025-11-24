import java.sql.*;

public class SklepApp {

    public static final String URL = "jdbc:mysql://localhost:3306/sklep";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    static void main(String[] args) {
        String sqlInsert = "INSERT INTO produkty (nazwa, cena) VALUES ('Kawa', 19.99)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(sqlInsert);
            System.out.println("Wstawiono " + rowsAffected + " produkt(y).");

            czytajProdukty(conn);

        } catch (SQLException e) {
            System.err.println("Błąd bazy danych: " +e.getMessage());
        }
    }

    private static void czytajProdukty(Connection conn) throws SQLException {
        String sqlSelect = "SELECT id, nazwa, cena FROM produkty";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlSelect)) {

            System.out.println("\n--- Lista Produktów ---");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nazwa = rs.getString("nazwa");
                double cena = rs.getDouble("cena");
                System.out.printf("ID: %d | Nazwa: %s | Cena= %.2f%n", id, nazwa, cena);
            }
            System.out.println("-----------------------");
        }
    }
}

