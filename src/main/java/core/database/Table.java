package core.database;

import java.util.List;

/**
 * 数据库中的表.
 *
 * @author 李程鹏
 */
public class Table {
    /**
     * 表名
     */
    private String name;

    /**
     * 表中的列
     */
    private List<Column> columns;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取表名.
     * </pre>
     *
     * @return {@code java.lang.String} - 表名
     */
    public String getName() {
        // 返回表名
        return name;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置表名.
     * </pre>
     *
     * @param name 表名
     */
    public void setName(String name) {
        // 赋值
        this.name = name;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取列集合.
     * </pre>
     *
     * @return {@code java.util.List<core.database.Column>} - 列集合
     */
    public List<Column> getColumns() {
        // 返回列集合
        return columns;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置列集合.
     * </pre>
     *
     * @param columns 列集合
     */
    public void setColumns(List<Column> columns) {
        // 赋值
        this.columns = columns;
    }
}