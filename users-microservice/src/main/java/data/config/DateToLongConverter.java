package data.config;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * Created by Troy on 5/16/2017.
 */
public class DateToLongConverter implements Converter<Date, Long> {

    @Override
    public Long convert(Date source) {
        return source.getTime();
    }
}
