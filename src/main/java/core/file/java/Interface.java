package core.file.java;

import core.util.TextUtils;

import java.util.*;

/**
 * 接口.
 *
 * @author 李程鹏
 */
public class Interface {
    /**
     * 权限访问符.
     */
    private String visibility;

    /**
     * 接口类型(接口名)
     */
    private Type type;

    /**
     * 需要导入的包
     */
    private Set<Type> imports = new TreeSet<>();

    /**
     * 文档注释
     */
    private List<String> documents = new ArrayList<>();

    /**
     * 注解
     */
    private List<String> annotations = new ArrayList<>();

    /**
     * 父接口
     */
    private Set<Type> superInterfaces = new LinkedHashSet<>();

    /**
     * 接口中的方法
     */
    private List<Method> methods = new ArrayList<>();

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置权限访问符
     * </pre>
     *
     * @param visibility 权限访问符
     */
    public void setVisibility(String visibility) {
        // 赋值
        this.visibility = visibility;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置接口类型(接口名).
     * </pre>
     *
     * @param type 接口类型(接口名)
     */
    public void setType(Type type) {
        // 赋值
        this.type = type;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加需要导入的包.
     * </pre>
     *
     * @param import_ 需要导入的包
     */
    public void addImports(Type import_) {
        // 添加入集合
        imports.add(import_);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加文档注释.
     * </pre>
     *
     * @param document 文档注释
     */
    public void addDocuments(String document) {
        // 添加入集合
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
        // 添加入集合
        annotations.add(annotation);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加父接口.
     * </pre>
     *
     * @param superInterface 父接口
     */
    public void addSuperInterface(Type superInterface) {
        // 添加入集合
        superInterfaces.add(superInterface);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加方法.
     * </pre>
     *
     * @param method 方法
     */
    public void addMethod(Method method) {
        // 添加入集合
        methods.add(method);
    }
}
