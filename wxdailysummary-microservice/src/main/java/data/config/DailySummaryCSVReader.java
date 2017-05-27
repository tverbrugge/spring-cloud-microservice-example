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

/**
 * Created by troy on 5/26/17.
 */
@Component
public class DailySummaryCSVReader extends FlatFileItemReader<DailySummary> {

    public DailySummaryCSVReader() {
        DefaultLineMapper<DailySummary> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer() {
            {
                setNames(new String[]{"STATION", "STATION_NAME", "DATE", "PRCP", "SNWD", "SNOW", "PSUN", "TSUN",
                        "TAVG", "TMAX", "TMIN", "AWND", "WT09", "WT14", "WT07", "WT01", "WT15", "WT17", "WT06",
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
                if(fs == null){
                    return null;
                }
                DailySummary ds = new DailySummary();
//                ds.setDate(fs.readDate("DATE"));
                ds.setMaxTemperature(fs.readInt("TMAX"));
                ds.setMinTemperature(fs.readInt("TMIN"));
                ds.setPrecip(fs.readFloat("PRCP"));
                ds.setSnow(fs.readFloat("SNOW"));

                return ds;

//                return super.mapFieldSet(fs);
            }
        });


        setResource(new PathResource("../data/DENVER_INTERNATIONAL_AIRPORT_CO-wxdata.csv"));
        setLinesToSkip(1);
        setLineMapper(lineMapper);
    }

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
