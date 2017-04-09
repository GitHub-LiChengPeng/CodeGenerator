package core.database;

/**
 * 数据库元素类.
 *
 * @author 李程鹏
 */
public class DatabaseElement {
    /**
     * 元素名
     */
    protected String name;

    /**
     * 元素注释
     */
    protected String remark;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取元素名.
     * </pre>
     *
     * @return {@code java.lang.String} - 元素名
     */
    public String getName() {
        // 返回名字
        return name;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置元素名.
     * </pre>
     *
     * @param name 名字
     */
    public void setName(String name) {
        // 赋值
        this.name = name;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取元素注释.
     * </pre>
     *
     * @return {@code java.lang.String} - 元素注释
     */
    public String getRemark() {
        // 返回注释
        return remark;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置元素注释.
     * </pre>
     *
     * @param remark 注释
     */
    public void setRemark(String remark) {
        // 赋值
        this.remark = remark;
    }
}