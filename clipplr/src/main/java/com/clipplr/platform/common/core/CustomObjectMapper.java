package com.clipplr.platform.common.core;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

/**
 * Created by simon on 15. 6. 26.
 */
public class CustomObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = -7242813795911540690L;

    public CustomObjectMapper() {
        super();

        // Format Date value as ISO-8601 in Restful response.
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        setDateFormat(new ISO8601DateFormat());

        // For JsonView.
        configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
    }

}
