package dk.easv.w13_workshop.model;


/**
 * Model holding all configurable parameters for a Fox machine.
 */
public class FoxConfig {
    private String testTime   = "1200";
    private String startTime  = "1300";
    private String stopTime   = "1500";
    private String foxId      = "A";
    private String frequency  = "434750";
    private String period     = "5";
    private String foxCall    = "OZ7FOX";

    // ── Getters ──────────────────────────────────────────────────────────────
    public String getTestTime()  { return testTime;  }
    public String getStartTime() { return startTime; }
    public String getStopTime()  { return stopTime;  }
    public String getFoxId()     { return foxId;     }
    public String getFrequency() { return frequency; }
    public String getPeriod()    { return period;    }
    public String getFoxCall()   { return foxCall;   }

    // ── Setters ──────────────────────────────────────────────────────────────
    public void setTestTime(String v)  { this.testTime  = v; }
    public void setStartTime(String v) { this.startTime = v; }
    public void setStopTime(String v)  { this.stopTime  = v; }
    public void setFoxId(String v)     { this.foxId     = v; }
    public void setFrequency(String v) { this.frequency = v; }
    public void setPeriod(String v)    { this.period    = v; }
    public void setFoxCall(String v)   { this.foxCall   = v; }
}

