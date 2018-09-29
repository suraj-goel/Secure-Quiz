package mainClasses;

public class TestTime {

    private int timeMagnitude;
    private String timeUnit;

    public TestTime(int timeMagnitude, String timeUnit) {
        this.timeMagnitude = timeMagnitude;
        this.timeUnit = timeUnit;
    }

    public int getTimeMagnitude() {
        return timeMagnitude;
    }

    public String getTimeUnit() {
        return timeUnit;
    }
}