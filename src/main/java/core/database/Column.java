package core.database;

/**
 * 数据库表中的列.
 *
 * @author 李程鹏
 */
public class Column {
    /**
     * 列名
     */
    private String name;

    /**
     * 列的类型
     */
    private int type;

    /**
     * 列的注释
     */
    private String remark;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取列名.
     * </pre>
     *
     * @return {@code java.lang.String} - 列名
     */
    public String getName() {
        // 返回列名
        return name;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置列名.
     * </pre>
     *
     * @param name 列名
     */
    public void setName(String name) {
        // 赋值
        this.name = name;
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

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取列的注释.
     * </pre>
     *
     * @return {@code java.lang.String} - 列的注释
     */
    public String getRemark() {
        // 返回列的注释
        return remark;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置列的注释.
     * </pre>
     *
     * @param remark 列的注释
     */
    public void setRemark(String remark) {
        // 赋值
        this.remark = remark;
    }
}