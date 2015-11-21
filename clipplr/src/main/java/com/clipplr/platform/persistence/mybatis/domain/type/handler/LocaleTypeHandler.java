package com.clipplr.platform.persistence.mybatis.domain.type.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by simon on 15. 6. 25.
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Locale.class)
public class LocaleTypeHandler extends BaseTypeHandler<Locale> {

    @Override
    public Locale getNullableResult(ResultSet rs, String columnName) throws SQLException {
        try {
            return Locale.forLanguageTag(rs.getString(columnName));
        } catch (IllegalArgumentException ex) {
            throw new IllegalStateException("Invalid locale code is in database: " + rs.getString(columnName), ex);
        }
    }

    @Override
    public Locale getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        try {
            return Locale.forLanguageTag(rs.getString(columnIndex));
        } catch (IllegalArgumentException ex) {
            throw new IllegalStateException("Invalid locale code is in database: " + rs.getString(columnIndex), ex);
        }
    }

    @Override
    public Locale getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        try {
            return Locale.forLanguageTag(cs.getString(columnIndex));
        } catch (IllegalArgumentException ex) {
            throw new IllegalStateException("Invalid locale code is in database: " + cs.getString(columnIndex), ex);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Locale parameter, JdbcType arg3) throws SQLException {
        ps.setString(i, (parameter != null)? parameter.toLanguageTag(): "");
    }

}
