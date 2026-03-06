import java.sql.*;
import java.io.File;

public class CleanupService {

    public static void deleteExpiredSessions() {

        try {

            String url = "jdbc:mysql://localhost:3306/secure_file_share";
            String user = "root";
            String password = "root123";

            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT session_id FROM file_session WHERE expiry_time < NOW()";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String sessionId = rs.getString("session_id");

                FileDeletionService.deleteSessionFolder(sessionId);

                PreparedStatement deleteStmt =
                        conn.prepareStatement("DELETE FROM file_session WHERE session_id=?");

                deleteStmt.setString(1, sessionId);
                deleteStmt.executeUpdate();

                System.out.println("Expired session deleted: " + sessionId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}