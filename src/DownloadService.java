import java.sql.Connection;
import java.sql.PreparedStatement;

public class DownloadService {

    public static void markAsDownloaded(String sessionId) {

        try {

            String url = "jdbc:mysql://localhost:3306/secure_file_share";
            String user = "root";
            String password = "root123";

            Connection conn = java.sql.DriverManager.getConnection(url, user, password);

            String sql = "UPDATE file_session SET downloaded = 1 WHERE session_id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, sessionId);

            stmt.executeUpdate();

            System.out.println("Download status updated.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}