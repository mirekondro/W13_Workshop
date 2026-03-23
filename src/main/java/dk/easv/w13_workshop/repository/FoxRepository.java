package dk.easv.w13_workshop.repository;


/**
 * REPOSITORY PATTERN – Repository interface.
 *
 * Defines how commands are sent to a Fox machine without coupling the rest
 * of the application to any specific transport (HTTP API, Telnet, mock …).
 *
 * To switch from the API emulator to a real Telnet terminal you only need
 * to write a new class that implements this interface and inject it instead.
 */
public interface FoxRepository {

    /**
     * Send a single configuration command to the Fox machine.
     *
     * @param groupNumber the virtual machine number (your group number)
     * @param command     the command string, e.g. "wr start"
     * @param value       the value string,   e.g. "1300"
     */
    void sendCommand(int groupNumber, String command, String value);
}

