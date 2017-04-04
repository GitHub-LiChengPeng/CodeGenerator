package core.util;

import core.file.java.Type;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Java文件操作工具类.
 *
 * @author 李程鹏
 */
public class JavaUtils {
    /**
     * <strong>Description:</strong>
     * <pre>
     * 为Java元素放入需要继承的父接口.
     * </pre>
     *
     * @param javaElement     Java元素
     * @param superInterfaces 父接口集合
     */
    public static void putSuperInterfaces(StringBuilder javaElement, Set<Type> superInterfaces) {
        // 放入操作
        putTypes(javaElement, superInterfaces, "extends");
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为Java元素放入需要抛出的异常.
     * </pre>
     *
     * @param javaElement Java元素
     * @param exceptions  异常集合
     */
    public static void putExceptions(StringBuilder javaElement, List<Type> exceptions) {
        // 放入操作
        putTypes(javaElement, exceptions, "throws");
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为Java元素放入需要实现的接口.
     * </pre>
     *
     * @param javaElement           Java元素
     * @param implementedInterfaces 接口集合
     */
    public static void putImplementedInterfaces(StringBuilder javaElement, Set<Type> implementedInterfaces) {
        // 放入操作
        putTypes(javaElement, implementedInterfaces, "implements");
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为Java元素放入类型集合.
     * </pre>
     *
     * @param javaElement Java元素
     * @param types       类型集合
     * @param keyWord     关键字
     */
    private static void putTypes(StringBuilder javaElement, Collection<Type> types, String keyWord) {
        // 如果集合不为空
        if (types.size() > 0) {
            // 添加空格
            TextUtils.addSpace(javaElement);
            // 添加关键字
            javaElement.append(keyWord);
            // 添加空格
            TextUtils.addSpace(javaElement);
            // 是否需要添加逗号
            boolean needComma = false;
            // 遍历类型集合
            for (Type type : types) {
                // 如果需要添加逗号
                if (needComma) {
                    // 添加逗号
                    javaElement.append(",");
                    // 添加空格
                    TextUtils.addSpace(javaElement);
                } else {
                    // 除了第一个类型,后面的在添加时都需要先加上逗号.
                    needComma = true;
                }
                // 添加类型名
                javaElement.append(type.getTypeName());
            }
        }
    }
}
