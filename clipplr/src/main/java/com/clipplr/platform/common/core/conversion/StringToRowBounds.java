package com.clipplr.platform.common.core.conversion;

import com.clipplr.platform.common.core.CustomObjectMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.core.convert.converter.Converter;

import java.io.IOException;

/**
 * Created by simon on 15. 6. 26.
 */
public class StringToRowBounds implements Converter<String, RowBounds> {

    private CustomObjectMapper objectMapper;

    public StringToRowBounds(CustomObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public RowBounds convert(String value) {
        if (value == null) {
            return null;
        }

        try {
            return objectMapper.readValue(value, RowBounds.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
