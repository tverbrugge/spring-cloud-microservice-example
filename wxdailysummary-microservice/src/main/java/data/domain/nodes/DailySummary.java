package data.domain.nodes;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by troy on 5/26/17.
 */
@Entity
@Table(name = "wxdailysummary")
public class DailySummary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "date")
    Date date;

    @Column(name = "prcp")
    Float precip;

    @Column(name = "snwd")
    Float snowDepth;

    @Column(name = "snow")
    Float snow;

    @Column(name = "tmax")
    Integer maxTemperature;

    @Column(name = "tmin")
    Integer minTemperature;

    @Column(name = "awnd")
    Float averageWindSpeed;

    @Override
    public String toString() {
        return "DailySummary{" +
                "id=" + id +
                ", date=" + date +
                ", precip=" + precip +
                ", snowDepth=" + snowDepth +
                ", snow=" + snow +
                ", maxTemperature=" + maxTemperature +
                ", minTemperature=" + minTemperature +
                ", averageWindSpeed=" + averageWindSpeed +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Float getPrecip() {
        return precip;
    }

    public Float getSnowDepth() {
        return snowDepth;
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

    public Float getAverageWindSpeed() {
        return averageWindSpeed;
    }
}
