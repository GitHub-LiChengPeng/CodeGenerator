package core.generator;

import core.database.Column;
import core.database.Table;
import core.util.StringUtils;

/**
 * 生成器抽象类.
 *
 * @author 李程鹏
 */
public abstract class Generator {
    /**
     * 数据库表
     */
    protected Table table;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 使用构造函数对属性赋值.
     * </pre>
     *
     * @param table 表
     */
    public Generator(Table table) {
        // 赋值
        this.table = table;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取驼峰样式的表格名.
     * </pre>
     *
     * @return {@code java.lang.String} - 表格名
     */
    protected String getTableName() {
        // 返回大驼峰样式的表格名
        return StringUtils.toCamelCase(table.getName(), true);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取驼峰样式的列名.
     * </pre>
     *
     * @param column 列对象
     * @return {@code java.lang.String} - 列名
     */
    protected String getColumnName(Column column) {
        // 返回小驼峰样式的列名
        return StringUtils.toCamelCase(column.getName(), false);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 生成代码.
     * </pre>
     *
     * @return {@code java.lang.Object} - 生成的代码字符串
     */
    protected abstract Object generate();
}