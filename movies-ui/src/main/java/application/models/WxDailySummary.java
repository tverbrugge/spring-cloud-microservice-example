package application.models;

import java.util.Date;

/**
 * Created by troy on 5/26/17.
 */
public class WxDailySummary {
    private Long id;

    private Date date;

    private Float precip;

    private Float snwd;

    private Float snow;

    private Integer maxTemperature;

    private Integer minTemperature;

    private Float awnd;

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Float getPrecip() {
        return precip;
    }

    public Float getSnwd() {
        return snwd;
    }

    public Float getSnow() {
        return snow;
    }

    public Integer getMaxTemperature() {
        return maxTemperature;
    }

    public Integer getMinTemperature() {
        return minTemperature;
    }

    public Float getAwnd() {
        return awnd;
    }
}
