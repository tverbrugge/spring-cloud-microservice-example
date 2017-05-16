package data.config;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * Created by Troy on 5/16/2017.
 */
public class LongToDateConverter implements Converter<Long, Date> {
    @Override
    public Date convert(Long source) {
        return new Date(source);
    }
}
