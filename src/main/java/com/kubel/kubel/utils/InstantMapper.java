package com.kubel.kubel.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@Component
@ApplicationScope
public class InstantMapper {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);

    public String asString(Instant instant) {
        if (instant == null) {
            return null;
        }
        return instant.toString();
    }

    public Instant asInstant(String string) {
        if (string == null) {
            return null;
        }
        if (isValidFormat(DATE_FORMAT, string)) {
            try {
                return format.parse(string).toInstant();
            } catch (ParseException e) {}
        }
        return Instant.parse(string);
    }

    private static boolean isValidFormat(String format, String value) {
        LocalDateTime ldt = null;
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format);

        try {
            ldt = LocalDateTime.parse(value, fomatter);
            String result = ldt.format(fomatter);
            return result.equals(value);
        } catch (DateTimeParseException e) {
            try {
                LocalDate ld = LocalDate.parse(value, fomatter);
                String result = ld.format(fomatter);
                return result.equals(value);
            } catch (DateTimeParseException exp) {
                try {
                    LocalTime lt = LocalTime.parse(value, fomatter);
                    String result = lt.format(fomatter);
                    return result.equals(value);
                } catch (DateTimeParseException e2) { }
            }
        }

        return false;
    }

}
