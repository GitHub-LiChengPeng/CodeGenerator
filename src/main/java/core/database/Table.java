package core.database;

import java.util.List;

/**
 * 数据库中的表.
 *
 * @author 李程鹏
 */
public class Table extends DatabaseElement {
    /**
     * 表中的列
     */
    private List<Column> columns;

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