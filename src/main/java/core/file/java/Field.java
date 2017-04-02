package core.file.java;

import core.util.TextUtils;

/**
 * 属性.
 *
 * @author 李程鹏
 */
public class Field extends JavaComponent {
    /**
     * 初始值
     */
    private String initValue;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置初始值.
     * </pre>
     *
     * @param initValue 初始值
     */
    public void setInitValue(String initValue) {
        // 赋值
        this.initValue = initValue;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 输出属性内容.
     * </pre>
     *
     * @param indentCount 缩进次数
     * @return {@code java.lang.String} - 属性内容
     */
    public String toString(int indentCount) {
        // 定义并初始化属性内容变量
        StringBuilder field = new StringBuilder();
        // 添加文档注释
        putDocuments(field, indentCount);
        // 添加注解
        putAnnotations(field, indentCount);
        // 缩进
        TextUtils.addIndentation(field, indentCount);
        // 添加基本信息
        putInformation(field);
        // 添加内容
        putContent(field, null);
        // 返回结果
        return field.toString();
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加基本信息.
     * </pre>
     *
     * @param field 属性
     */
    @Override
    protected void putInformation(StringBuilder field) {
        // 添加访问控制符
        putVisibility(field);
        // 添加静态标志
        putStatic(field);
        // 添加终结标志
        putFinal(field);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加内容.
     * </pre>
     *
     * @param field       属性
     * @param indentCount 缩进次数
     */
    @Override
    protected void putContent(StringBuilder field, Integer indentCount) {
        // 添加属性类型
        field.append(type.getTypeName());
        // 添加空格
        TextUtils.addSpace(field);
        // 添加属性名
        field.append(name);
        // 如果属性初始值不为空
        if (initValue != null && initValue.length() > 0) {
            // 添加空格
            TextUtils.addSpace(field);
            // 添加等号
            field.append("=");
            // 添加空格
            TextUtils.addSpace(field);
            // 添加初始值
            field.append(initValue);
        }
        // 添加分号
        field.append(';');
    }
}