package application.models;

import java.util.Date;

/**
 * Created by troy on 5/26/17.
 */
public class WxDailySummary {
    private Long id;

    private Date date;

    private Float prcp;

    private Float snwd;

    private Float snow;

    private Integer tmax;

    private Integer tmin;

    private Float awnd;

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Float getPrcp() {
        return prcp;
    }

    public Float getSnwd() {
        return snwd;
    }

    public Float getSnow() {
        return snow;
    }

    public Integer getTmax() {
        return tmax;
    }

    public Integer getTmin() {
        return tmin;
    }

    public Float getAwnd() {
        return awnd;
    }
}
