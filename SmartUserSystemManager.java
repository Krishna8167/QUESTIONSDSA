import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

public class SmartUserSystemManager {

    private static final String LOG_FILE = "smart_user_manager.log";

    public static void main(String[] args) {
        SmartUserSystemManager manager = new SmartUserSystemManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--------------------------------------------");
            System.out.println(" SMART USER & SYSTEM AUTOMATION MANAGER ");
            System.out.println("--------------------------------------------");
            System.out.println("1. Create New User (simulated)");
            System.out.println("2. Run System Health Check");
            System.out.println("3. Schedule System Health Check (Executor)");
            System.out.println("4. Setup Daily Backup Job (Simulated)");
            System.out.println("5. View Log File");
            System.out.println("6. Exit");
            System.out.println("--------------------------------------------");
            System.out.print("Enter choice [1-6]: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> manager.createUser(sc);
                case 2 -> manager.systemHealthCheck();
                case 3 -> manager.scheduleHealthCheck(sc);
                case 4 -> manager.createBackupJob();
                case 5 -> manager.viewLog();
                case 6 -> {
                    manager.log("Exiting program.");
                    System.out.println("Goodbye!");
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // ---------- Logging ----------
    private void log(String message) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            fw.write(time + " | " + message + "\n");
        } catch (IOException e) {
            System.err.println("Error writing log: " + e.getMessage());
        }
    }

    // ---------- Simulated User Creation ----------
    private void createUser(Scanner sc) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        File userDir = new File("users/" + username);
        if (userDir.exists()) {
            System.out.println("User '" + username + "' already exists!");
            log("Attempted to create existing user: " + username);
        } else {
            if (userDir.mkdirs()) {
                System.out.print("Enter password: ");
                String password = sc.nextLine();
                try (FileWriter fw = new FileWriter(userDir + "/info.txt")) {
                    fw.write("username=" + username + "\npassword=" + password);
                } catch (IOException e) {
                    System.err.println("Failed to save user info.");
                }
                System.out.println("User '" + username + "' created successfully!");
                log("User '" + username + "' created.");
            }
        }
    }

    // ---------- System Health Check ----------
    private void systemHealthCheck() {
        log("Running system health check...");
        System.out.println("\n--- System Health Check ---");

        double cpuLoad = getCpuLoad();
        double memUsage = getMemoryUsage();
        double diskUsage = getDiskUsage();

        System.out.printf("CPU Load: %.2f%%\n", cpuLoad);
        System.out.printf("Memory Usage: %.2f%%\n", memUsage);
        System.out.printf("Disk Usage: %.2f%%\n", diskUsage);

        log(String.format("CPU: %.2f%% | MEM: %.2f%% | DISK: %.2f%%", cpuLoad, memUsage, diskUsage));
    }

    // ---------- Schedule Health Check ----------
    private void scheduleHealthCheck(Scanner sc) {
        System.out.print("Enter delay (in seconds) to run health check: ");
        int delay = sc.nextInt();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(this::systemHealthCheck, delay, TimeUnit.SECONDS);

        System.out.println("Health check scheduled to run in " + delay + " seconds.");
        log("Scheduled health check after " + delay + " seconds.");
    }

    // ---------- Backup Job (Simulated) ----------
    private void createBackupJob() {
        log("Creating daily backup job...");
        System.out.println("Simulating backup... copying /etc and /home -> backup/");
        File backupDir = new File("backup/" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        if (backupDir.mkdirs()) {
            log("Backup directory created: " + backupDir.getName());
            System.out.println("Backup completed successfully!");
        } else {
            log("Backup creation failed!");
            System.out.println("Backup failed!");
        }
    }

    // ---------- View Log File ----------
    private void viewLog() {
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE))) {
            System.out.println("\n--- Log File ---");
            String line;
            while ((line = br.readLine()) != null) System.out.println(line);
        } catch (IOException e) {
            System.err.println("No log found yet.");
        }
    }

    // ---------- Utility Methods ----------
    private double getCpuLoad() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                return new Random().nextDouble() * 50; // simulate
            } else {
                Process p = Runtime.getRuntime().exec("uptime");
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = reader.readLine();
                if (line != null && line.contains("load average:")) {
                    String loadStr = line.substring(line.lastIndexOf("average:") + 9).split(",")[0].trim();
                    return Double.parseDouble(loadStr) * 100 / Runtime.getRuntime().availableProcessors();
                }
            }
        } catch (Exception ignored) {}
        return new Random().nextDouble() * 70; // fallback
    }

    private double getMemoryUsage() {
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        return ((double) (total - free) / total) * 100;
    }

    private double getDiskUsage() {
        File root = new File("/");
        long total = root.getTotalSpace();
        long free = root.getFreeSpace();
        return ((double) (total - free) / total) * 100;
    }
}
