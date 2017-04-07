package core.file.java;

import core.util.JavaUtils;
import core.util.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 方法.
 *
 * @author 李程鹏
 */
public class Method extends JavaComponent {
    /**
     * 是否是构造方法
     */
    private boolean isConstructor;

    /**
     * 是否是接口中的方法
     */
    private boolean isInterfaceMethod;

    /**
     * 方法参数
     */
    private List<Parameter> parameters = new ArrayList<>();

    /**
     * 方法需要抛出的异常
     */
    private List<Type> exceptions = new ArrayList<>();

    /**
     * 方法体
     */
    private List<String> statements = new ArrayList<>();

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置构造方法标志.
     * </pre>
     *
     * @param isConstructor 构造方法标志
     */
    public void setConstructor(boolean isConstructor) {
        // 赋值
        this.isConstructor = isConstructor;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置接口方法标志.
     * </pre>
     *
     * @param isInterfaceMethod 接口方法标志
     */
    public void setInterfaceMethod(boolean isInterfaceMethod) {
        // 赋值
        this.isInterfaceMethod = isInterfaceMethod;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 返回方法的参数.
     * </pre>
     *
     * @return {@code java.util.List<core.file.java.Parameter>} - 参数集合
     */
    public List<Parameter> getParameters() {
        // 返回参数集合
        return parameters;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加参数.
     * </pre>
     *
     * @param parameter 参数
     */
    public void addParameter(Parameter parameter) {
        // 添加操作
        parameters.add(parameter);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加异常.
     * </pre>
     *
     * @param exception 异常
     */
    public void addException(Type exception) {
        // 添加操作
        exceptions.add(exception);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加方法语句.
     * </pre>
     *
     * @param statement 方法语句
     */
    public void addStatement(String statement) {
        // 添加操作
        statements.add(statement);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 输出方法内容.
     * </pre>
     *
     * @param indentCount 缩进次数
     * @return {@code java.lang.String} - 方法内容
     */
    public String toString(int indentCount) {
        // 定义并初始化方法内容变量
        StringBuilder method = new StringBuilder();
        // 添加文档注释
        putDocuments(method, indentCount);
        // 添加注解
        putAnnotations(method, indentCount);
        // 缩进
        TextUtils.addIndentation(method, indentCount);
        // 添加基本信息
        putInformation(method);
        // 添加内容
        putContent(method, indentCount);
        // 返回结果
        return method.toString();
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为方法放入基本信息.
     * </pre>
     *
     * @param method 方法
     */
    @Override
    protected void putInformation(StringBuilder method) {
        // 添加关键字
        putKeyWords(method);
        // 添加方法名
        method.append(name);
        // 添加参数
        putParameters(method);
        // 添加异常
        JavaUtils.putExceptions(method, exceptions);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为方法放入方法体内容.
     * </pre>
     *
     * @param method      方法
     * @param indentCount 缩进次数
     */
    @Override
    protected void putContent(StringBuilder method, Integer indentCount) {
        // 如果方法体为空
        if (statements.size() == 0) {
            // 添加分号
            method.append(";");
        } else {// 如果方法体不为空
            // 添加空格
            TextUtils.addSpace(method);
            // 添加左花括号
            method.append("{");
            // 增加缩进次数
            indentCount++;
            // 获取方法语句集合的迭代器
            ListIterator<String> listIterator = statements.listIterator();
            // 遍历方法语句集合
            while (listIterator.hasNext()) {
                // 取出语句
                String statement = listIterator.next();
                // 如果该语句以"}"开始(例如:"}else{").
                if (statement.startsWith("}")) {
                    // 减小缩进次数
                    indentCount--;
                }
                // 换行
                TextUtils.addLine(method);
                // 缩进
                TextUtils.addIndentation(method, indentCount);
                // 添加语句
                method.append(statement);
                // 如果该语句又开启了一个新的语句块(例如:"if(){").
                if ((statement.endsWith("{") && !statement.startsWith("switch")) || statement.endsWith(":")) {
                    // 增加缩进次数
                    indentCount++;
                }
                // 如果该语句以"break"关键字开始
                if (statement.startsWith("break")) {
                    // 如果该语句后面还有语句
                    if (listIterator.hasNext()) {
                        // 获取该语句后面的语句
                        String nextStatement = listIterator.next();
                        // 如果后面的语句以"}"开始
                        if (nextStatement.startsWith("}")) {
                            // 增加缩进次数
                            indentCount++;
                        }
                        // 将迭代器返回上一次所在的位置
                        listIterator.previous();
                    }
                    // 减小缩进次数
                    indentCount--;
                }
            }
            // 减小缩进次数
            indentCount--;
            // 换行
            TextUtils.addLine(method);
            // 缩进
            TextUtils.addIndentation(method, indentCount);
            // 添加右花括号
            method.append('}');
        }
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为方法放入关键字.
     * </pre>
     *
     * @param method 方法
     */
    private void putKeyWords(StringBuilder method) {
        // 如果不是接口的方法
        if (!isInterfaceMethod) {
            // 添加访问控制符
            putVisibility(method);
            // 添加静态标志
            putStatic(method);
            // 添加终结标志
            putFinal(method);
            // 如果方法体为空
            if (statements.size() == 0) {
                // 添加abstract关键字
                method.append("abstract");
                // 添加空格
                TextUtils.addSpace(method);
            }
        }
        // 如果不是构造方法
        if (!isConstructor) {
            // 如果返回值为空
            if (type == null) {
                // 添加void关键字
                method.append("void");
            } else {// 如果返回值不为空
                // 添加返回值类型
                method.append(type.getTypeName());
            }
            // 添加空格
            TextUtils.addSpace(method);
        }
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为方法放入参数信息.
     * </pre>
     *
     * @param method 方法
     */
    private void putParameters(StringBuilder method) {
        // 添加左圆括号
        method.append("(");
        // 是否需要添加逗号
        boolean needComma = false;
        // 遍历参数集合
        for (Parameter parameter : parameters) {
            // 如果需要添加逗号
            if (needComma) {
                // 添加逗号
                method.append(",");
                // 添加空格
                TextUtils.addSpace(method);
            } else {
                // 除了第一个参数,后边的参数在添加时都需要在参数前加上逗号..
                needComma = true;
            }
            // 添加参数内容
            method.append(parameter.toString());
        }
        // 添加右圆括号
        method.append(")");
    }
}