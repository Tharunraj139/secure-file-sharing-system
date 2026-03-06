import java.io.File;

public class FileDeletionService {

    public static void deleteSessionFolder(String sessionId) {

        String folderPath = "../uploads/" + sessionId;

        File folder = new File(folderPath);

        if (folder.exists()) {

            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }

            folder.delete();

            System.out.println("Session files deleted successfully!");
        }
    }
}