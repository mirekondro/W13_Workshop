package dk.easv.w13_workshop.command;

import com.foxconfig.repository.FoxRepository;

/**
 * COMMAND PATTERN – Concrete command.
 *
 * Stores a single "wr &lt;param&gt; &lt;value&gt;" command together with the
 * repository it should be sent to.  Calling execute() delegates to the
 * repository so the command itself stays independent of transport details.
 */
public class ConfigureParameterCommand implements FoxCommand {

    private final FoxRepository repository;
    private final int           groupNumber;
    private final String        command;   // e.g. "wr start"
    private final String        value;     // e.g. "1300"

    public ConfigureParameterCommand(FoxRepository repository,
                                     int groupNumber,
                                     String command,
                                     String value) {
        this.repository  = repository;
        this.groupNumber = groupNumber;
        this.command     = command;
        this.value       = value;
    }

    @Override
    public void execute() {
        repository.sendCommand(groupNumber, command, value);
    }

    @Override
    public String getDescription() {
        return String.format("Group %d  →  %s %s", groupNumber, command, value);
    }

    // ── Accessors used by the UI log ─────────────────────────────────────────
    public String getCommand() { return command; }
    public String getValue()   { return value;   }
}

