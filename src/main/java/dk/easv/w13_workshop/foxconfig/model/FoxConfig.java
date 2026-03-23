package dk.easv.w13_workshop.foxconfig.model;

/**
 * Data model representing fox configuration parameters.
 */
public class FoxConfig {
    private String testTime = "1230";
    private String startTime = "1300";
    private String stopTime = "1500";
    private String foxId = "A";
    private String frequency = "434750";
    private String period = "5";
    private String foxCall = "OZ7FOX";

    public String getTestTime() { return testTime; }
    public void setTestTime(String testTime) { this.testTime = testTime; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getStopTime() { return stopTime; }
    public void setStopTime(String stopTime) { this.stopTime = stopTime; }

    public String getFoxId() { return foxId; }
    public void setFoxId(String foxId) { this.foxId = foxId; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }

    public String getFoxCall() { return foxCall; }
    public void setFoxCall(String foxCall) { this.foxCall = foxCall; }
}
