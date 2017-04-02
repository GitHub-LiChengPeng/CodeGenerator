package core.file.java;

import core.util.TextUtils;

/**
 * 方法的参数.
 *
 * @author 李程鹏
 */
public class Parameter extends JavaComponent {
    /**
     * 是否是不定长参数
     */
    private boolean isVariable;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造初始化参数对象.
     * </pre>
     *
     * @param type       参数类型
     * @param name       参数名称
     * @param isVariable 是否是变长参数
     */
    public Parameter(Type type, String name, boolean isVariable) {
        // 为参数属性赋值
        this.type = type;
        // 为参数名称赋值
        this.name = name;
        // 为不定长标志赋值
        this.isVariable = isVariable;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造初始化参数对象.
     * </pre>
     *
     * @param type       参数类型
     * @param name       参数名字
     * @param annotation 参数注解
     */
    public Parameter(Type type, String name, String annotation) {
        // 调用另外一个构造函数
        this(type, name, false);
        // 添加参数注解
        annotations.add(annotation);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造初始化参数对象.
     * </pre>
     *
     * @param type 参数类型
     * @param name 参数名字
     */
    public Parameter(Type type, String name) {
        // 调用另外一个构造函数
        this(type, name, false);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 输出参数内容.
     * </pre>
     *
     * @return {@code java.lang.String} - 参数内容
     */
    @Override
    public String toString() {
        // 定义并初始化参数内容变量
        StringBuilder parameter = new StringBuilder();
        // 添加注解
        putAnnotations(parameter, null);
        // 添加内容
        putContent(parameter, null);
        // 返回参数内容
        return parameter.toString();
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为参数放入注解(重写父类的方法).
     * </pre>
     *
     * @param parameter   参数
     * @param indentCount 缩进次数
     */
    @Override
    protected void putAnnotations(StringBuilder parameter, Integer indentCount) {
        // 循环遍历参数的注解集合
        for (String annotation : annotations) {
            // 为参数添加注解
            parameter.append(annotation);
            // 添加空格
            TextUtils.addSpace(parameter);
        }
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为参数添加内容.
     * </pre>
     *
     * @param parameter   参数
     * @param indentCount 缩进次数
     */
    @Override
    protected void putContent(StringBuilder parameter, Integer indentCount) {
        // 添加参数的类型
        parameter.append(type.getTypeName());
        // 添加空格
        TextUtils.addSpace(parameter);
        // 如果参数是不定长的
        if (isVariable) {
            // 添加不定长关键字
            parameter.append("...");
            // 添加空格
            TextUtils.addSpace(parameter);
        }
        // 添加参数名字
        parameter.append(name);
    }

    /**
     * 空方法
     */
    @Override
    protected void putInformation(StringBuilder javaElement) {}
}