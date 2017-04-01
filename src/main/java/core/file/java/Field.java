package core.file.java;

import core.util.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性.
 *
 * @author 李程鹏
 */
public class Field {
    /**
     * 权限控制符
     */
    private String visibility;

    /**
     * 是否是static属性
     */
    private boolean isStatic;

    /**
     * 是否是final属性
     */
    private boolean isFinal;

    /**
     * 属性类型
     */
    private Type type;

    /**
     * 属性名
     */
    private String name;

    /**
     * 初始值
     */
    private String initValue;

    /**
     * 文档注释
     */
    private List<String> documents = new ArrayList<>();

    /**
     * 注解
     */
    private List<String> annotations = new ArrayList<>();

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置权限控制符.
     * </pre>
     *
     * @param visibility 权限控制符
     */
    public void setVisibility(String visibility) {
        // 赋值
        this.visibility = visibility;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置static属性标志.
     * </pre>
     *
     * @param isStatic static属性标志
     */
    public void setStatic(boolean isStatic) {
        // 赋值
        this.isStatic = isStatic;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置final属性标志.
     * </pre>
     *
     * @param isFinal final属性标志
     */
    public void setFinal(boolean isFinal) {
        // 赋值
        this.isFinal = isFinal;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置属性类型.
     * </pre>
     *
     * @param type 属性类型
     */
    public void setType(Type type) {
        // 赋值
        this.type = type;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置属性名.
     * </pre>
     *
     * @param name 属性名
     */
    public void setName(String name) {
        // 赋值
        this.name = name;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置初始化值.
     * </pre>
     *
     * @param initValue 初始化值
     */
    public void setInitValue(String initValue) {
        // 赋值
        this.initValue = initValue;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加文档注释.
     * </pre>
     *
     * @param document 文档注释
     */
    public void addDocument(String document) {
        // 将文档注释添加到注释集合中
        documents.add(document);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加注解.
     * </pre>
     *
     * @param annotation 注解
     */
    public void addAnnotation(String annotation) {
        // 将注解添加到注解集合中
        annotations.add(annotation);
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
        // 遍历文档注释集合
        for (String document : documents) {
            // 缩进
            TextUtils.addIndentation(field, indentCount);
            // 添加文档内容
            field.append(document);
            // 换行
            TextUtils.addLine(field);
        }
        // 遍历注解集合
        for (String annotation : annotations) {
            // 缩进
            TextUtils.addIndentation(field, indentCount);
            // 添加注解
            field.append(annotation);
            // 换行
            TextUtils.addLine(field);
        }
        // 缩进
        TextUtils.addIndentation(field, indentCount);
        // 添加权限控制符
        field.append(visibility);
        // 如果是static类型的属性
        if (isStatic) {
            // 添加static关键字
            field.append("static");
            // 添加空格
            TextUtils.addSpace(field);
        }
        // 如果是final类型的属性
        if (isFinal) {
            // 添加final关键字
            field.append("final");
            // 添加空格
            TextUtils.addSpace(field);
        }
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
        // 返回结果
        return field.toString();
    }
}