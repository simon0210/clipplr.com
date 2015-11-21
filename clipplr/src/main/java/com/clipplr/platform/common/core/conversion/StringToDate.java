package com.clipplr.platform.common.core.conversion;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;


/**
 * Created by simon on 15. 6. 26.
 */
public class StringToDate implements Converter<String, Date> {

    @Override
    public Date convert(String date) {
        Exception fail = null;

        try {
            // there's a problem with ISO8601Utils, it fails to parse if hh:mm:ss is missing in date string
            if (date.length() < 11) {
                date = date + " 00:00:00";
            }

            ISO8601DateFormat df = new ISO8601DateFormat();
            return df.parse(date);

        }catch (Exception e) {
            String input = (date == null) ? null : ('"' + date + "'");
            throw new IllegalArgumentException("Failed to parse date [" + input + "]: ");
        }

    }
}
