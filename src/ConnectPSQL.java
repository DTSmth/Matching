
import java.sql.*;

public class ConnectPSQL {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/matchingDB";
        String username = "postgres";
        String password = "";

        Connection conn = null;
        try {
            // Connect to the database
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to PostgreSQL server!");

            // Execute a query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM openhours");

            // Process the results
            while (rs.next()) {
                int id = rs.getInt("id");
                int hours = rs.getInt("total_hours");
                int zipCode = rs.getInt("zip_code");
                System.out.println("id: " + id + ", Open hours: " + hours + " zip code: " + zipCode);
            }

        } catch (SQLException e) {
            System.err.println("Failed to connect to PostgreSQL server: " + e.getMessage());
        } finally {
            // Close the connection
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Disconnected from PostgreSQL server.");
                }
            } catch (SQLException ex) {
                System.err.println("Failed to close connection to PostgreSQL server: " + ex.getMessage());
            }
        }
    }
}
