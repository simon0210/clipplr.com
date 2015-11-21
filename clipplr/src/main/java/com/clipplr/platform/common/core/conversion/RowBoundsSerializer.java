package com.clipplr.platform.common.core.conversion;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import org.apache.ibatis.session.RowBounds;

import java.io.IOException;

/**
 * Created by simon on 15. 6. 26.
 */
public class RowBoundsSerializer extends StdScalarSerializer<RowBounds> {

    private static final long serialVersionUID = -4748206011754942788L;

    public RowBoundsSerializer() {
        super(RowBounds.class);
    }

    @Override
    public void serialize(RowBounds value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {

        jgen.writeStartObject();
        jgen.writeFieldName("offset");
        jgen.writeNumber(value.getOffset());
        jgen.writeFieldName("limit");
        jgen.writeNumber(value.getLimit());
        jgen.writeEndObject();

    }

}

