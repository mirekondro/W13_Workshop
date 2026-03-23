package dk.easv.w13_workshop.command;


/**
 * COMMAND PATTERN – Command interface.
 *
 * Every configuration change is wrapped in a FoxCommand so it can be
 * stored, queued, and executed later in a batch.
 */
public interface FoxCommand {

    /**
     * Execute the command (sends it to the repository / terminal).
     */
    void execute();

    /**
     * Human-readable description used in the UI log.
     */
    String getDescription();
}

