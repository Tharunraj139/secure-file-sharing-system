import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SessionService {

    public static void saveSession(String sessionId, String otp, boolean burnAfterDownload) {

        try {

            String url = "jdbc:mysql://localhost:3306/secure_file_share";
            String user = "root";
            String password = "root123";

            Connection conn = java.sql.DriverManager.getConnection(url, user, password);

            LocalDateTime expiry = LocalDateTime.now().plusHours(1);

            String sql = "INSERT INTO file_session(session_id, otp, expiry_time, burn_after_download) VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, sessionId);
            stmt.setString(2, otp);
            stmt.setTimestamp(3, Timestamp.valueOf(expiry));
            stmt.setBoolean(4, burnAfterDownload);

            stmt.executeUpdate();

            System.out.println("Session saved successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}