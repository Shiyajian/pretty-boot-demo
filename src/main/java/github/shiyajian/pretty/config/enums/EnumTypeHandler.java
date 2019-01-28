package github.shiyajian.pretty.config.enums;

import github.shiyajian.pretty.commons.Enumerable;
import github.shiyajian.pretty.utils.EnumUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
public class EnumTypeHandler<E extends Enumerable> extends BaseTypeHandler<E> {

    private Class<E> enumType;

    public EnumTypeHandler() { /* instance */ }


    public EnumTypeHandler(Class<E> enumType) {
        this.enumType = enumType;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, e.getValue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        return rs.wasNull() ? null : EnumUtil.of(this.enumType, value);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int value = rs.getInt(columnIndex);
        return rs.wasNull() ? null : EnumUtil.of(this.enumType, value);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int value = cs.getInt(columnIndex);
        return cs.wasNull() ? null : EnumUtil.of(this.enumType, value);
    }

}
