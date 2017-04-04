package core.resolver;

import core.file.java.Type;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类型解析器(将JDBC类型解析为Java类型).
 *
 * @author 李程鹏
 */
public class TypeResolver {
    /**
     * 类型映射变量
     */
    private Map<Integer, Type> typeMap;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造创建解析器实例,在创建过程中放入初始化数据.
     * </pre>
     */
    public TypeResolver() {
        typeMap = new HashMap<>();
        typeMap.put(Types.ARRAY, new Type(Object.class.getName()));
        typeMap.put(Types.BIGINT, new Type(Long.class.getName()));
        typeMap.put(Types.BINARY, new Type("byte[]"));
        typeMap.put(Types.BIT, new Type(Boolean.class.getName()));
        typeMap.put(Types.BLOB, new Type("byte[]"));
        typeMap.put(Types.BOOLEAN, new Type(Boolean.class.getName()));
        typeMap.put(Types.CHAR, new Type(String.class.getName()));
        typeMap.put(Types.CLOB, new Type(String.class.getName()));
        typeMap.put(Types.DATALINK, new Type(Object.class.getName()));
        typeMap.put(Types.DATE, new Type(Date.class.getName()));
        typeMap.put(Types.DISTINCT, new Type(Object.class.getName()));
        typeMap.put(Types.DOUBLE, new Type(Double.class.getName()));
        typeMap.put(Types.FLOAT, new Type(Double.class.getName()));
        typeMap.put(Types.INTEGER, new Type(Integer.class.getName()));
        typeMap.put(Types.JAVA_OBJECT, new Type(Object.class.getName()));
        typeMap.put(Types.LONGVARBINARY, new Type("byte[]"));
        typeMap.put(Types.LONGVARCHAR, new Type(String.class.getName()));
        typeMap.put(Types.NULL, new Type(Object.class.getName()));
        typeMap.put(Types.OTHER, new Type(Object.class.getName()));
        typeMap.put(Types.REAL, new Type(Float.class.getName()));
        typeMap.put(Types.REF, new Type(Object.class.getName()));
        typeMap.put(Types.SMALLINT, new Type(Short.class.getName()));
        typeMap.put(Types.STRUCT, new Type(Object.class.getName()));
        typeMap.put(Types.TIME, new Type(Date.class.getName()));
        typeMap.put(Types.TIMESTAMP, new Type(Date.class.getName()));
        typeMap.put(Types.TINYINT, new Type(Byte.class.getName()));
        typeMap.put(Types.VARBINARY, new Type("byte[]"));
        typeMap.put(Types.VARCHAR, new Type(String.class.getName()));
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 根据JDBC类型解析出对应的Java类型.
     * </pre>
     *
     * @param jdbcType JDBC类型
     * @return {@code core.file.java.Type} - Java类型
     */
    public Type resolve(Integer jdbcType) {
        // 在Map查询并返回查询结果
        return typeMap.get(jdbcType);
    }
}
