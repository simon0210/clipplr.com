package com.clipplr.platform.persistence.mybatis.domain.type.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;

/**
 * Created by simon on 15. 6. 25.
 */
@MappedJdbcTypes(JdbcType.CHAR)
@MappedTypes(Currency.class)
public class CurrencyTypeHandler extends BaseTypeHandler<Currency>  {

    @Override
    public Currency getNullableResult(ResultSet rs, String columnName) throws SQLException {
        try {
            return Currency.getInstance(rs.getString(columnName));
        } catch (IllegalArgumentException ex) {
            throw new IllegalStateException("Invalid currency code is in database: " + rs.getString(columnName), ex);
        }
    }

    @Override
    public Currency getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        try {
            return Currency.getInstance(rs.getString(columnIndex));
        } catch (IllegalArgumentException ex) {
            throw new IllegalStateException("Invalid currency code is in database: " + rs.getString(columnIndex), ex);
        }
    }

    @Override
    public Currency getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        try {
            return Currency.getInstance(cs.getString(columnIndex));
        } catch (IllegalArgumentException ex) {
            throw new IllegalStateException("Invalid currency code is in database: " + cs.getString(columnIndex), ex);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Currency parameter, JdbcType arg3) throws SQLException {
        ps.setString(i, (parameter != null)? parameter.getCurrencyCode(): "---");
    }

}
