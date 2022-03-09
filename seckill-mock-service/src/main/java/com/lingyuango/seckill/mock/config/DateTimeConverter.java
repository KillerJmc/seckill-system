package com.lingyuango.seckill.mock.config;

import com.lingyuango.seckill.mock.common.Const;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class DateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(@Nonnull String source) {
        return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(Const.DATE_TIME_FORMAT));
    }
}
