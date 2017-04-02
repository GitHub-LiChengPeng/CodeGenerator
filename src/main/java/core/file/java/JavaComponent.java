package core.file.java;

/**
 * Java组件抽象类.
 *
 * @author 李程鹏
 */
public abstract class JavaComponent extends JavaElement {
    /**
     * 组件类型
     */
    protected Type type;

    /**
     * 组件名
     */
    protected String name;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置组件类型.
     * </pre>
     *
     * @param type 组件类型
     */
    public void setType(Type type) {
        // 赋值
        this.type = type;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置组件名.
     * </pre>
     *
     * @param name 组件名
     */
    public void setName(String name) {
        // 赋值
        this.name = name;
    }
}
