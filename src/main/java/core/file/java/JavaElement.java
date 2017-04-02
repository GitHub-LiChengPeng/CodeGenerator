package core.file.java;

import core.util.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Java元素抽象类.
 *
 * @author 李程鹏
 */
public abstract class JavaElement {
    /**
     * 访问控制符(private,protected,public)
     */
    protected String visibility;

    /**
     * 静态标志
     */
    protected boolean isStatic;

    /**
     * 终结标志
     */
    protected boolean isFinal;

    /**
     * 抽象标志
     */
    protected boolean isAbstract;

    /**
     * 文档注释集合
     */
    protected List<String> documents = new ArrayList<>();

    /**
     * 注解集合
     */
    protected List<String> annotations = new ArrayList<>();

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置访问控制符.
     * </pre>
     *
     * @param visibility 访问控制符
     */
    public void setVisibility(String visibility) {
        // 赋值
        this.visibility = visibility;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置静态标志.
     * </pre>
     *
     * @param isStatic 静态标志
     */
    public void setStatic(boolean isStatic) {
        // 赋值
        this.isStatic = isStatic;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置终结标志.
     * </pre>
     *
     * @param isFinal 终结标志
     */
    public void setFinal(boolean isFinal) {
        // 赋值
        this.isFinal = isFinal;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置抽象标志.
     * </pre>
     *
     * @param isAbstract 抽象标志
     */
    public void setAbstract(boolean isAbstract) {
        // 赋值
        this.isAbstract = isAbstract;
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
        // 将文档注释添加到集合中
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
        // 将注解添加到集合中
        annotations.add(annotation);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为Java元素放入文档注释.
     * </pre>
     *
     * @param javaElement Java元素
     * @param indentCount 缩进次数
     */
    protected void putDocuments(StringBuilder javaElement, Integer indentCount) {
        // 放入操作
        putStrings(javaElement, documents, indentCount);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为Java元素放入注解.
     * </pre>
     *
     * @param javaElement Java元素
     * @param indentCount 缩进次数
     */
    protected void putAnnotations(StringBuilder javaElement, Integer indentCount) {
        // 放入操作
        putStrings(javaElement, annotations, indentCount);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为Java元素放入关键字,具体由子类来实现.
     * </pre>
     *
     * @param javaElement Java元素
     */
    protected abstract void putKeyWords(StringBuilder javaElement);

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为Java元素放入内容,具体由子类来实现.
     * </pre>
     *
     * @param javaElement Java元素
     * @param indentCount 缩进次数
     */
    protected abstract void putContent(StringBuilder javaElement, Integer indentCount);

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为Java元素放入字符串集合.
     * </pre>
     *
     * @param javaElement Java元素
     * @param strings     字符串集合
     * @param indentCount 缩进次数
     */
    private void putStrings(StringBuilder javaElement, List<String> strings, Integer indentCount) {
        // 遍历字符串集合
        for (String string : strings) {
            // 缩进
            TextUtils.addIndentation(javaElement, indentCount);
            // 添加字符串
            javaElement.append(string);
            // 换行
            TextUtils.addLine(javaElement);
        }
    }
}
