import java.util.Random;
import java.util.UUID;

public class OTPGenerator {

    public static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public static String generateSessionId() {
        return UUID.randomUUID().toString();
    }

}