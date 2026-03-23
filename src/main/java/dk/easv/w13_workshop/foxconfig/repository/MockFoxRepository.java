package dk.easv.w13_workshop.foxconfig.repository;

/**
 * Mock implementation of FoxRepository for testing.
 */
public class MockFoxRepository implements FoxRepository {

    @Override
    public boolean sendCommand(int group, String command, String value) {
        // Simulate command execution
        try {
            Thread.sleep(200); // Simulate processing time
            System.out.println("MOCK: Sending to group " + group + ": " + command + " = " + value);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
