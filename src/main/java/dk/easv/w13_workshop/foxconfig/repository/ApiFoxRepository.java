package dk.easv.w13_workshop.foxconfig.repository;

/**
 * API-based implementation of FoxRepository.
 * This would normally connect to http://10.5.10.10:8080
 */
public class ApiFoxRepository implements FoxRepository {

    @Override
    public boolean sendCommand(int group, String command, String value) {
        // Simulate API call
        try {
            Thread.sleep(500); // Simulate network delay
            System.out.println("API: Sending to group " + group + ": " + command + " = " + value);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
