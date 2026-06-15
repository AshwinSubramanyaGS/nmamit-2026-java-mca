import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaLoggerLevelsDemo {

    // 1. Initialize the logger for this class
    private static final Logger logger = Logger.getLogger(JavaLoggerLevelsDemo.class.getName());

    public static void main(String[] args) {
        // 2. Configure the logger to display ALL messages (default is INFO and above)
        configureLoggerToDisplayAll();

        System.out.println("--- Starting Java Logging Levels Demo ---\n");

        // 3. Log using every single standard level
        
        // SEVERE (Highest severity)
        logger.log(Level.SEVERE, "SEVERE: Critical failure! Connection to database lost.");
        
        // WARNING
        logger.log(Level.WARNING, "WARNING: Low disk space detected on the host drive.");
        
        // INFO (Default production level)
        logger.log(Level.INFO, "INFO: Application successfully started on port 8080.");
        
        // CONFIG
        logger.log(Level.CONFIG, "CONFIG: Loaded configuration file from: /etc/app/config.properties");
        
        // FINE (Debug)
        logger.log(Level.FINE, "FINE: User 'admin' successfully authenticated.");
        
        // FINER (Detailed Tracing)
        logger.log(Level.FINER, "FINER: Entering method processTransaction() with ID: 55432");
        
        // FINEST (Most verbose Tracing)
        logger.log(Level.FINEST, "FINEST: Raw payload byte array length: 124 bytes.");

        System.out.println("\n--- Demo Completed ---");
    }

    /**
     * Overrides the default logging setup so fine-grained logs print to the console.
     */
    private static void configureLoggerToDisplayAll() {
        // Suppress inheritance from parent loggers to prevent duplicate console printing
        logger.setUseParentHandlers(false);

        // Create a new ConsoleHandler, set it to ALL, and attach it
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);

        // Set the logger itself to ALL
        logger.setLevel(Level.ALL);
    }
}
