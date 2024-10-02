import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    // Method to add a new criminal record
    public void addCriminal(String crimeType, String firNumber, String name) {
        String sql = "INSERT INTO criminals (crime_type, fir_number, criminal_name) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, crimeType);
            pstmt.setString(2, firNumber);
            pstmt.setString(3, name);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all criminal records
    public List<Criminal> getAllCriminals() {
        List<Criminal> criminals = new ArrayList<>();
        String sql = "SELECT * FROM criminals";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Criminal criminal = new Criminal(
                        rs.getInt("id"),
                        rs.getString("crime_type"),
                        rs.getString("fir_number"),
                        rs.getString("criminal_name")
                );
                criminals.add(criminal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return criminals;
    }

    // Method to update an existing criminal record
    public void updateCriminal(int id, String crimeType, String firNumber, String name) {
        String sql = "UPDATE criminals SET crime_type = ?, fir_number = ?, criminal_name = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, crimeType);
            pstmt.setString(2, firNumber);
            pstmt.setString(3, name);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a criminal record by ID
    public void deleteCriminal(int id) {
        String sql = "DELETE FROM criminals WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to search for criminals by keyword (name, FIR number, or crime type)
    public List<Criminal> findCriminal(String keyword) {
        List<Criminal> criminals = new ArrayList<>();
        String sql = "SELECT * FROM criminals WHERE criminal_name LIKE ? OR fir_number LIKE ? OR crime_type LIKE ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Criminal criminal = new Criminal(
                        rs.getInt("id"),
                        rs.getString("crime_type"),
                        rs.getString("fir_number"),
                        rs.getString("criminal_name")
                );
                criminals.add(criminal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return criminals;
    }
}
