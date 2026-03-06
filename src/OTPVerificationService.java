import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OTPVerificationService {

    public static String verifyOTP(String otp) {

        try {

            String url = "jdbc:mysql://localhost:3306/secure_file_share";
            String user = "root";
            String password = "root123";

            Connection conn = java.sql.DriverManager.getConnection(url, user, password);

            String sql = "SELECT session_id FROM file_session WHERE otp = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, otp);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("session_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}