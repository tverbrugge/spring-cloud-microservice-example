package data.config;

import data.domain.nodes.DailySummary;
import data.domain.nodes.User;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by troy on 5/26/17.
 */
@Component
public class DailySummaryCSVReader extends FlatFileItemReader<DailySummary> {

    private enum CSVFields {
        DATE("DATE"), PRECIP("PRCP"), SNOW("SNOW"), SNOW_DEPTH("SNWD"), TEMPERATURE_MIN("TMIN");

        private CSVFields(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }


        private String name;

        @Override
        public String toString() {
            return getName();
        }
    }

    public DailySummaryCSVReader() {
        DefaultLineMapper<DailySummary> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer() {
            {
                setNames(new String[]{"STATION", "STATION_NAME", CSVFields.DATE.getName(), CSVFields.PRECIP.getName(),
                        CSVFields.SNOW_DEPTH.getName(), CSVFields.SNOW.getName(), "PSUN", "TSUN",
                        "TAVG", "TMAX", CSVFields.TEMPERATURE_MIN.getName(), "AWND", "WT09", "WT14", "WT07", "WT01", "WT15", "WT17", "WT06",
                        "WT21", "WT05", "WT02", "WT11", "WT22", "WT04", "WT13", "WT16", "WT08", "WT18", "WT03",
                        "WT10", "WT19", "WV01", "WV03"});
            }
        });
        lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<DailySummary>() {
            {
                setTargetType(DailySummary.class);
//                setPrototypeBeanName("User");
            }

            @Override
            public DailySummary mapFieldSet(FieldSet fs) throws BindException {
                if (fs == null) {
                    return null;
                }
                DailySummary ds = new DailySummary();
                ds.setDate(parseDate(fs.readString(CSVFields.DATE.getName())));
                ds.setMaxTemperature(fs.readInt("TMAX"));
                ds.setMinTemperature(fs.readInt(CSVFields.TEMPERATURE_MIN.getName()));
                ds.setPrecip(fs.readFloat(CSVFields.PRECIP.getName()));
                ds.setSnow(fs.readFloat("SNOW"));
                ds.setSnowDepth(fs.readFloat(CSVFields.SNOW_DEPTH.getName()));

                return ds;

//                return super.mapFieldSet(fs);
            }


            private Date parseDate(String dateStr) {
//                Matcher dateMatcher = datePattern.matcher(dateStr);

                try {
                    return dateFormat.parse(dateStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });


        setResource(new PathResource("../data/DENVER_INTERNATIONAL_AIRPORT_CO-wxdata.csv"));
        setLinesToSkip(1);
        setLineMapper(lineMapper);
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");


//    private DefaultLineMapper<DailySummary> lineMapper;

//    @Override
//    public LineMapper<DailySummary> getLineMapper() {
//        if (lineMapper == null) {
//            lineMapper = new DefaultLineMapper<>();
//            lineMapper.setLineTokenizer(new DelimitedLineTokenizer() {
//                {
//                    setNames(new String[]{"STATION", "STATION_NAME", "DATE", "PRCP", "SNWD", "SNOW", "PSUN", "TSUN",
//                            "TAVG", "TMAX", "TMIN", "AWND", "WT09", "WT14", "WT07", "WT01", "WT15", "WT17", "WT06",
//                            "WT21", "WT05", "WT02", "WT11", "WT22", "WT04", "WT13", "WT16", "WT08", "WT18", "WT03",
//                            "WT10", "WT19", "WV01", "WV03"});
//                }
//            });
//            lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<DailySummary>() {
//                {
//                    setTargetType(DailySummary.class);
////                setPrototypeBeanName("User");
//                }
//            });
//
//        }
//        return lineMapper;
//    }
}
