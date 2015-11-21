package com.clipplr.platform.persistence.mybatis.domain.type.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TimeZone;

/**
 * Created by simon on 15. 6. 25.
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({TimeZone.class})
public class TimeZoneTypeHandler  extends BaseTypeHandler<TimeZone> {

    @Override
    public TimeZone getNullableResult(ResultSet rs, String columnName) throws SQLException {
        try {
            return TimeZone.getTimeZone(rs.getString(columnName));
        } catch (IllegalArgumentException ex) {
            throw new IllegalStateException("Invalid time zone code is in database: " + rs.getString(columnName), ex);
        }
    }

    @Override
    public TimeZone getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        try {
            return TimeZone.getTimeZone(rs.getString(columnIndex));
        } catch (IllegalArgumentException ex) {
            throw new IllegalStateException("Invalid time zone code is in database: " + rs.getString(columnIndex), ex);
        }
    }

    @Override
    public TimeZone getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        try {
            return TimeZone.getTimeZone(cs.getString(columnIndex));
        } catch (IllegalArgumentException ex) {
            throw new IllegalStateException("Invalid time zone code is in database: " + cs.getString(columnIndex), ex);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, TimeZone parameter, JdbcType arg3) throws SQLException {
        ps.setString(i, (parameter != null)? parameter.getID(): "");
    }

}
