package data.domain.nodes;

/**
 * Created by troy on 5/21/17.
 */
public class Report {
    private String date;
    private String impressions;
    private String clicks;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImpressions() {
        return impressions;
    }

    public void setImpressions(String impressions) {
        this.impressions = impressions;
    }

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public String getEarning() {
        return earning;
    }

    public void setEarning(String earning) {
        this.earning = earning;
    }

    private String earning;
}
