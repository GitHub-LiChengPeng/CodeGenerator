package core.generator.dao;

import core.database.Column;
import core.database.Table;
import core.file.java.*;
import core.file.java.Class;
import core.generator.Generator;
import core.resolver.TypeResolver;
import core.util.PackageUtils;

import java.util.List;

/**
 * 简单Java对象(Plain Ordinary Java Object)生成器.
 *
 * @author 李程鹏
 */
public class POJOGenerator extends Generator {
    /**
     * 类的类型(类名)
     */
    protected Type classType;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造初始化实例.
     * </pre>
     *
     * @param table 表格对象
     */
    public POJOGenerator(Table table) {
        // 调用父类构造函数
        super(table);
        // 初始化类的类型(类名).
        this.classType = new Type(PackageUtils.ENTITY.getValue() + getTableName());
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 生成代码.
     * </pre>
     *
     * @return {@code core.file.java.Class} - 生成的POJO类
     */
    @Override
    public Class generate() {
        // 新建一个类
        Class class_ = new Class();
        // 设置类的类型
        class_.setType(classType);
        // 设置类的访问控制符
        class_.setVisibility("public ");
        // 获取表中的所有列
        List<Column> columns = table.getColumns();
        // 遍历列集合
        for (Column column : columns) {
            // 新建一个属性
            Field field = new Field();
            // 设置属性的访问控制符
            field.setVisibility("private ");
            // 设置属性的类型
            field.setType(new TypeResolver().resolve(column.getType()));
            // 设置属性名
            field.setName(getColumnName(column));
            // 为类添加属性
            class_.addField(field);
            // 为类添加需要导入的类型
            class_.addImport(field.getType());
            // 生成Getter方法
            generateGetterMethod(class_, field);
            // 生成Setter方法
            generateSetterMethod(class_, field);
        }
        // 返回POJO类
        return class_;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 根据属性生成Getter方法.
     * </pre>
     *
     * @param class_ 类
     * @param field  属性
     */
    protected void generateGetterMethod(Class class_, Field field) {
        // 获取属性的类型
        Type type = field.getType();
        // 获取属性名
        String property = field.getName();
        // 新建一个方法
        Method method = new Method();
        // 设置访问控制符
        method.setVisibility("public ");
        // 设置返回值类型
        method.setType(type);
        // 设置方法名
        method.setName(getGetterMethodName(property, type));
        // 定义方法语句变量
        StringBuilder statement = new StringBuilder();
        // 添加返回关键字
        statement.append("return ");
        // 添加属性名
        statement.append(property);
        // 添加分号
        statement.append(";");
        // 为方法添加语句
        method.addStatement(statement.toString());
        // 为类添加方法
        class_.addMethod(method);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取Getter方法的方法名.
     * </pre>
     *
     * @param property 属性
     * @param type     属性的类型
     * @return {@code java.lang.String} - 方法名
     */
    private String getGetterMethodName(String property, Type type) {
        // 新建方法名变量
        StringBuilder methodName = new StringBuilder();
        // 将属性名放入变量中
        methodName.append(property);
        // 如果方法名中的第一个字符是小写
        if (Character.isLowerCase(methodName.charAt(0))) {
            // 如果方法名只有一个字符或者第二个字符不是大写
            if (methodName.length() == 1 || !Character.isUpperCase(methodName.charAt(1))) {
                // 将变量中的第一个字符变成大写
                methodName.setCharAt(0, Character.toUpperCase(methodName.charAt(0)));
            }
        }
        // 如果属性的类型是布尔型
        if (type.equals(new Type("boolean"))) {
            // 在方法名前加入is
            methodName.insert(0, "is");
        } else {// 否则
            // 在方法名前加入get
            methodName.insert(0, "get");
        }
        // 返回方法名
        return methodName.toString();
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 根据属性生成Setter方法.
     * </pre>
     *
     * @param class_ 类
     * @param field  属性
     */
    protected void generateSetterMethod(Class class_, Field field) {
        // 获取属性的类型
        Type type = field.getType();
        // 获取属性名
        String property = field.getName();
        // 新建一个方法
        Method method = new Method();
        // 设置方法的访问控制符
        method.setVisibility("public ");
        // 设置方法名
        method.setName(getSetterMethodName(property));
        // 为方法添加参数
        method.addParameter(new Parameter(type, property));
        // 定义方法语句变量
        StringBuilder statement = new StringBuilder();
        // 为语句变量添加内容
        statement.append("this.");
        statement.append(property);
        statement.append(" = ");
        statement.append(property);
        statement.append(';');
        // 为方法添加方法语句
        method.addStatement(statement.toString());
        // 为类添加方法
        class_.addMethod(method);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取Setter方法的方法名.
     * </pre>
     *
     * @param property 属性名
     * @return {@code java.lang.String} - 方法名
     */
    private static String getSetterMethodName(String property) {
        // 新建方法名变量
        StringBuilder methodName = new StringBuilder();
        // 将属性名放入变量中
        methodName.append(property);
        // 如果方法名中的第一个字符是小写
        if (Character.isLowerCase(methodName.charAt(0))) {
            // 如果方法名只有一个字符或者第二个字符不是大写
            if (methodName.length() == 1 || !Character.isUpperCase(methodName.charAt(1))) {
                // 将变量中的第一个字符变成大写
                methodName.setCharAt(0, Character.toUpperCase(methodName.charAt(0)));
            }
        }
        // 在方法名前加入set
        methodName.insert(0, "set");
        // 返回方法名
        return methodName.toString();
    }
}