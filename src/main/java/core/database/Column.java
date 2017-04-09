package core.database;

/**
 * 数据库表中的列.
 *
 * @author 李程鹏
 */
public class Column extends DatabaseElement {
    /**
     * 列的类型
     */
    private int type;

    /**
     * 是否是主键列
     */
    private boolean isPrimaryKey;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 返回主键列标志.
     * </pre>
     *
     * @return {@code boolean} - 主键列标志
     */
    public boolean isPrimaryKey() {
        // 返回标志
        return isPrimaryKey;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置主键列标志.
     * </pre>
     *
     * @param isPrimaryKey 标志
     */
    public void setPrimaryKey(boolean isPrimaryKey) {
        // 赋值
        this.isPrimaryKey = isPrimaryKey;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取列的类型.
     * </pre>
     *
     * @return {@code int} - 列的类型
     */
    public int getType() {
        // 返回列的类型
        return type;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置列的类型.
     * </pre>
     *
     * @param type 列的类型
     */
    public void setType(int type) {
        // 赋值
        this.type = type;
    }
}