package core.file.java;

import core.util.JavaUtils;
import core.util.TextUtils;

import java.util.*;

/**
 * 类.
 *
 * @author 李程鹏
 */
public class Class extends JavaFile {
    /**
     * 父类
     */
    private Type superClass;

    /**
     * 类中的属性
     */
    private List<Field> fields = new ArrayList<>();

    /**
     * 内部类
     */
    private List<Class> classes = new ArrayList<>();

    /**
     * 实现的接口
     */
    private Set<Type> implementedInterfaces = new TreeSet<>();

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置父类.
     * </pre>
     *
     * @param superClass 父类
     */
    public void setSuperClass(Type superClass) {
        // 赋值
        this.superClass = superClass;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加属性.
     * </pre>
     *
     * @param field 属性
     */
    public void addField(Field field) {
        // 添加入集合
        fields.add(field);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加类.
     * </pre>
     *
     * @param class_ 类
     */
    public void addClass(Class class_) {
        // 添加入集合
        classes.add(class_);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加实现的接口.
     * </pre>
     *
     * @param implementedInterface 需要实现的接口
     */
    public void addImplementedInterface(Type implementedInterface) {
        // 添加入集合
        implementedInterfaces.add(implementedInterface);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 输出类的内容.
     * </pre>
     *
     * @param indentCount 缩进次数
     * @return {@code java.lang.String} - 类的内容
     */
    public String toString(int indentCount) {
        // 定义类内容变量
        StringBuilder class_ = new StringBuilder();
        // 添加导入的类型
        putImports(class_);
        // 添加文档注释
        putDocuments(class_, indentCount);
        // 添加注解
        putAnnotations(class_, indentCount);
        // 添加缩进
        TextUtils.addIndentation(class_, indentCount);
        // 添加基本信息
        putInformation(class_);
        // 添加类的具体内容
        putContent(class_, indentCount);
        // 返回类的内容
        return class_.toString();
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为类放入基本信息.
     * </pre>
     *
     * @param class_ 类
     */
    @Override
    protected void putInformation(StringBuilder class_) {
        // 添加访问控制符
        putVisibility(class_);
        // 添加抽象标志
        putAbstract(class_);
        // 添加静态标志
        putStatic(class_);
        // 添加终结标志
        putFinal(class_);
        // 添加类关键字
        class_.append("class");
        // 添加空格
        TextUtils.addSpace(class_);
        // 添加类名
        class_.append(type.getTypeName());
        // 如果父类不为空
        if (superClass != null) {
            // 添加空格
            TextUtils.addSpace(class_);
            // 添加继承关键字
            class_.append("extends");
            // 添加空格
            TextUtils.addSpace(class_);
            // 添加父类名
            class_.append(superClass.getTypeName());
        }
        // 添加本类需要实现的接口
        JavaUtils.putImplementedInterfaces(class_, implementedInterfaces);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加类的具体内容.
     * </pre>
     *
     * @param class_      类
     * @param indentCount 缩进次数
     */
    @Override
    protected void putContent(StringBuilder class_, Integer indentCount) {
        // 添加空格
        TextUtils.addSpace(class_);
        // 添加左花括号
        class_.append("{");
        // 增加缩进次数
        indentCount++;
        // 获取属性集合的迭代器
        Iterator<Field> fieldsIterator = fields.iterator();
        // 遍历属性集合
        while (fieldsIterator.hasNext()) {
            // 换行
            TextUtils.addLine(class_);
            // 获取属性对象
            Field field = fieldsIterator.next();
            // 添加属性内容
            class_.append(field.toString(indentCount));
            // 如果还有下一个属性
            if (fieldsIterator.hasNext()) {
                // 换行
                TextUtils.addLine(class_);
            }
        }
        // 如果类中有方法
        if (methods.size() > 0) {
            // 换行
            TextUtils.addLine(class_);
        }
        // 获取方法集合的迭代器
        Iterator<Method> methodsIterator = methods.iterator();
        // 遍历方法集合
        while (methodsIterator.hasNext()) {
            // 换行
            TextUtils.addLine(class_);
            // 获取方法对象
            Method method = methodsIterator.next();
            // 不是接口中的方法
            method.setInterfaceMethod(false);
            // 添加方法内容
            class_.append(method.toString(indentCount));
            // 如果还有下一个方法
            if (methodsIterator.hasNext()) {
                // 换行
                TextUtils.addLine(class_);
            }
        }
        // 如果类中有内部类
        if (classes.size() > 0) {
            // 换行
            TextUtils.addLine(class_);
        }
        // 获取内部类集合的迭代器
        Iterator<Class> classesIterator = classes.iterator();
        // 遍历内部类集合
        while (classesIterator.hasNext()) {
            // 换行
            TextUtils.addLine(class_);
            // 获取内部类对象
            Class innerClass = classesIterator.next();
            // 添加内部类内容
            class_.append(innerClass.toString(indentCount));
            // 如果还有下一个内部类
            if (classesIterator.hasNext()) {
                // 换行
                TextUtils.addLine(class_);
            }
        }
        // 减小缩进次数
        indentCount--;
        // 换行
        TextUtils.addLine(class_);
        // 缩进
        TextUtils.addIndentation(class_, indentCount);
        // 添加右花括号
        class_.append('}');
    }
}