package dk.easv.w13_workshop;

import dk.easv.w13_workshop.foxconfig.command.CommandManager;
import dk.easv.w13_workshop.foxconfig.command.ConfigureParameterCommand;
import dk.easv.w13_workshop.foxconfig.command.FoxCommand;
import dk.easv.w13_workshop.foxconfig.model.FoxConfig;
import dk.easv.w13_workshop.foxconfig.repository.ApiFoxRepository;
import dk.easv.w13_workshop.foxconfig.repository.FoxRepository;
import dk.easv.w13_workshop.foxconfig.repository.MockFoxRepository;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main UI Controller for Fox Configuration.
 *
 * Wires together the three design patterns:
 *   • COMMAND  – each "Queue" button creates a ConfigureParameterCommand
 *   • ITERATOR – CommandManager.executeAll() iterates via CommandIterator
 *   • REPOSITORY – FoxRepository is injected; swap for Telnet/Mock easily
 */
public class FoxConfigController {

    // ── FXML fields ──────────────────────────────────────────────────────────

    @FXML private TextField tfGroupNumber;

    // Configuration fields
    @FXML private TextField tfTestTime;
    @FXML private TextField tfStartTime;
    @FXML private TextField tfStopTime;
    @FXML private ComboBox<String> cbFoxId;
    @FXML private TextField tfFrequency;
    @FXML private TextField tfPeriod;
    @FXML private TextField tfFoxCall;

    // Queue buttons
    @FXML private Button btnQueueTest;
    @FXML private Button btnQueueStart;
    @FXML private Button btnQueueStop;
    @FXML private Button btnQueueId;
    @FXML private Button btnQueueFreq;
    @FXML private Button btnQueuePeriod;
    @FXML private Button btnQueueCall;

    // Action buttons
    @FXML private Button btnSendAll;
    @FXML private Button btnClear;

    // Status / log area
    @FXML private Label  lblStatus;
    @FXML private Label  lblPendingCount;
    @FXML private TextArea taPendingLog;
    @FXML private TextArea taExecutionLog;

    // Repository toggle
    @FXML private RadioButton rbApi;
    @FXML private RadioButton rbMock;

    // ── Domain objects ───────────────────────────────────────────────────────

    private final FoxConfig       foxConfig  = new FoxConfig();
    private       FoxRepository   repository = new ApiFoxRepository();
    private final CommandManager  manager    = new CommandManager();
    private final ExecutorService executor   = Executors.newSingleThreadExecutor();

    // ── Lifecycle ─────────────────────────────────────────────────────────────

    @FXML
    public void initialize() {
        cbFoxId.getItems().addAll("A", "U", "V", "H", "5");
        cbFoxId.setValue(foxConfig.getFoxId());

        // Populate text fields from model defaults
        tfTestTime.setText(foxConfig.getTestTime());
        tfStartTime.setText(foxConfig.getStartTime());
        tfStopTime.setText(foxConfig.getStopTime());
        tfFrequency.setText(foxConfig.getFrequency());
        tfPeriod.setText(foxConfig.getPeriod());
        tfFoxCall.setText(foxConfig.getFoxCall());
        tfGroupNumber.setText("1");

        // Radio group for repository selection
        ToggleGroup repoGroup = new ToggleGroup();
        rbApi.setToggleGroup(repoGroup);
        rbMock.setToggleGroup(repoGroup);
        rbApi.setSelected(true);

        repoGroup.selectedToggleProperty().addListener((obs, o, n) -> {
            if (rbMock.isSelected()) {
                repository = new MockFoxRepository();
                setStatus("🔧 Mock mode activated - Offline testing enabled", false);
            } else {
                repository = new ApiFoxRepository();
                setStatus("🌐 API mode activated - Connected to fox network", false);
            }
        });

        refreshPendingCount();
        setStatus("🎯 Ready for fox configuration", false);
    }

    // ── Queue button handlers (COMMAND PATTERN) ───────────────────────────────

    @FXML private void onQueueTest()   { queueCommand("wr test",   tfTestTime.getText(),  "Test time"); }
    @FXML private void onQueueStart()  { queueCommand("wr start",  tfStartTime.getText(), "Start time"); }
    @FXML private void onQueueStop()   { queueCommand("wr stop",   tfStopTime.getText(),  "Stop time"); }
    @FXML private void onQueueId()     { queueCommand("wr id",     cbFoxId.getValue(),    "Fox ID"); }
    @FXML private void onQueueFreq()   { queueCommand("wr freq",   tfFrequency.getText(), "Frequency"); }
    @FXML private void onQueuePeriod() { queueCommand("wr period", tfPeriod.getText(),    "Period"); }
    @FXML private void onQueueCall()   { queueCommand("wr call",   tfFoxCall.getText(),   "Fox call"); }

    /**
     * COMMAND PATTERN: create a concrete command and hand it to the manager.
     */
    private void queueCommand(String command, String value, String label) {
        if (value == null || value.isBlank()) {
            setStatus("⚠  " + label + " is empty – not queued.", true);
            return;
        }
        int group = parseGroupNumber();
        if (group < 0) return;

        FoxCommand cmd = new ConfigureParameterCommand(repository, group, command, value);
        manager.addCommand(cmd);

        taPendingLog.appendText("+ " + cmd.getDescription() + "\n");
        refreshPendingCount();
        setStatus("✅ Queued: " + cmd.getDescription(), false);
    }

    // ── Send button handler (ITERATOR PATTERN via CommandManager) ─────────────

    @FXML
    private void onSendAll() {
        if (manager.pendingCount() == 0) {
            setStatus("⚠  No commands queued.", true);
            return;
        }

        btnSendAll.setDisable(true);
        taExecutionLog.clear();
        setStatus("⏳ Executing " + manager.pendingCount() + " command(s)...", false);

        // Run on background thread so UI stays responsive
        executor.submit(() -> {
            // ITERATOR PATTERN: executeAll() creates a CommandIterator internally
            manager.executeAll((cmd, success, error) -> {
                Platform.runLater(() -> {
                    if (success) {
                        taExecutionLog.appendText("✓  " + cmd.getDescription() + "\n");
                    } else {
                        taExecutionLog.appendText("✗  " + cmd.getDescription()
                                + "  [ERROR: " + error + "]\n");
                    }
                    refreshPendingCount();
                });
            });

            Platform.runLater(() -> {
                taPendingLog.clear();
                btnSendAll.setDisable(false);
                setStatus("🎉 All commands executed successfully!", false);
                refreshPendingCount();
            });
        });
    }

    // ── Clear button ──────────────────────────────────────────────────────────

    @FXML
    private void onClear() {
        manager.clearCommands();
        taPendingLog.clear();
        refreshPendingCount();
        setStatus("🗑️ Command queue cleared", false);
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private void refreshPendingCount() {
        int n = manager.pendingCount();
        lblPendingCount.setText("Pending: " + n);
        btnSendAll.setDisable(n == 0);
    }

    private int parseGroupNumber() {
        try {
            return Integer.parseInt(tfGroupNumber.getText().trim());
        } catch (NumberFormatException e) {
            setStatus("⚠  Group number must be an integer.", true);
            return -1;
        }
    }

    private void setStatus(String msg, boolean isError) {
        lblStatus.setText(msg);
        // Remove existing style classes
        lblStatus.getStyleClass().removeAll("status-ready", "status-warning", "status-error");

        if (isError) {
            lblStatus.getStyleClass().add("status-error");
        } else if (msg.contains("⚠") || msg.contains("Warning")) {
            lblStatus.getStyleClass().add("status-warning");
        } else {
            lblStatus.getStyleClass().add("status-ready");
        }
    }
}
