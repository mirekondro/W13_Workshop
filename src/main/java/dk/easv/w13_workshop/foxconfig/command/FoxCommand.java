package dk.easv.w13_workshop.foxconfig.command;

/**
 * Base interface for all fox commands using the Command pattern.
 */
public interface FoxCommand {
    /**
     * Execute the command.
     * @return true if successful, false otherwise
     */
    boolean execute();

    /**
     * Get a description of this command for logging.
     */
    String getDescription();
}
