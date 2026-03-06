import java.util.concurrent.*;

public class BackgroundScheduler {

    public static void startCleanupTask() {

        ScheduledExecutorService scheduler =
                Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {

            System.out.println("Running cleanup task...");
            CleanupService.deleteExpiredSessions();

        }, 0, 5, TimeUnit.MINUTES);
    }
}