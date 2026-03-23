package dk.easv.w13_workshop.foxconfig.repository;

/**
 * Repository interface for fox configuration operations.
 */
public interface FoxRepository {
    /**
     * Send a configuration command to the fox.
     * @param group the fox group number
     * @param command the command string
     * @param value the value to configure
     * @return true if successful, false otherwise
     */
    boolean sendCommand(int group, String command, String value);
}
