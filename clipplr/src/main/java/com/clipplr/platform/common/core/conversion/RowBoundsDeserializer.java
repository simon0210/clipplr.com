package com.clipplr.platform.common.core.conversion;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import org.apache.ibatis.session.RowBounds;

import java.io.IOException;

/**
 * Created by simon on 15. 6. 26.
 */
public class RowBoundsDeserializer extends StdScalarDeserializer<RowBounds> {

    private static final long serialVersionUID = 1531080116843833177L;

    public RowBoundsDeserializer() {
        super(RowBounds.class);
    }

    @Override
    public RowBounds deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        if (jp.getCurrentToken() != JsonToken.START_OBJECT) {
            throw ctxt.mappingException("Expected JSON String");
        }

        Integer offset = null;
        Integer limit = null;

        JsonToken token = jp.nextToken();

        while (token != JsonToken.END_OBJECT) {
            if (token != JsonToken.FIELD_NAME) {
                throw ctxt.mappingException("Expected JSON Field Name");
            }

            final String fieldName = jp.getCurrentName();
            if (fieldName.equals("offset")) {
                token = jp.nextToken();
                if (token != JsonToken.VALUE_NUMBER_INT) {
                    throw ctxt.mappingException("Expected JSON String for the field 'offset'");
                }

                offset = jp.getValueAsInt();
            }

            if (fieldName.equals("limit")) {
                token = jp.nextToken();
                if (token != JsonToken.VALUE_NUMBER_INT) {
                    throw ctxt.mappingException("Expected JSON String for the field 'limit'");
                }

                limit = jp.getValueAsInt();
            }

            token = jp.nextToken();
        }

        if (offset == null) {
            throw ctxt.mappingException("JSON Field Name 'offset' is required");
        }

        if (limit == null) {
            throw ctxt.mappingException("JSON Field Name 'limit' is required");
        }

        return new RowBounds(offset, limit);
    }

}
