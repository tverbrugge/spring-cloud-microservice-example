package data.config;

import data.domain.nodes.Report;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.PassThroughFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

import javax.sql.DataSource;

/**
 * Created by troy on 5/19/17.
 */
@Configuration
public class JobReport {


//    @Bean(name = "report")
//    public Report

    @Bean(name = "csvFileItemReader")
    public FlatFileItemReader getCSVFileItemReader() {

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames(new String[]{"date", "impressions", "clicks", "earnings"});

//        PassThroughFieldSetMapper passThroughFieldSetMapper = new PassThroughFieldSetMapper();
        BeanWrapperFieldSetMapper beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper();
        beanWrapperFieldSetMapper.setPrototypeBeanName("report");

        DefaultLineMapper lineMapper = new DefaultLineMapper();
        lineMapper.setLineTokenizer(delimitedLineTokenizer);
        lineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);


        FlatFileItemReader reader = new FlatFileItemReader();
        reader.setResource(new PathResource("classpath:csv/report.csv"));
        reader.setLineMapper(lineMapper);


        return reader;
    }

    private static final String SQL = "insert into RAW_REPORT(DATE,IMPRESSIONS,CLICKS,EARNINGS) " +
            "values (:date, :impressions, :clicks, :earning)";

    @Bean(name="mysqlItemWriter")
    public JdbcBatchItemWriter getMySQLItemWriter(DataSource dataSource) {
        JdbcBatchItemWriter itewmWriter = new JdbcBatchItemWriter();
        itewmWriter.setDataSource(dataSource);

        itewmWriter.setSql(SQL);

        BeanPropertyItemSqlParameterSourceProvider beanPropertyItemSqlParameterSourceProvider = new BeanPropertyItemSqlParameterSourceProvider();

        itewmWriter.setItemSqlParameterSourceProvider(beanPropertyItemSqlParameterSourceProvider);

        return itewmWriter;
    }

    public Report getReport() {
        return new Report();
    }
}
