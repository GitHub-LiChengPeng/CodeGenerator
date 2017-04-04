package core.file.java;

import core.util.JavaUtils;
import core.util.TextUtils;

import java.util.*;

/**
 * 接口.
 *
 * @author 李程鹏
 */
public class Interface extends JavaFile {
    /**
     * 父接口
     */
    private Set<Type> superInterfaces = new LinkedHashSet<>();

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
     * 输出接口内容.
     * </pre>
     *
     * @return {@code java.lang.String} - 接口内容
     */
    public String toString() {
        // 定义并初始化接口内容变量
        StringBuilder interface_ = new StringBuilder();
        // 添加导入的类型
        putImports(interface_);
        // 定义并初始化缩进次数
        int indentCount = 0;
        // 添加文档注释
        putDocuments(interface_, indentCount);
        // 添加注解
        putAnnotations(interface_, indentCount);
        // 添加接口基本信息
        putInformation(interface_);
        // 添加接口具体内容
        putContent(interface_, indentCount);
        // 返回接口内容
        return interface_.toString();
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为接口放入基本信息.
     * </pre>
     *
     * @param interface_ 接口
     */
    @Override
    protected void putInformation(StringBuilder interface_) {
        // 添加访问控制符
        interface_.append(visibility);
        // 添加接口关键字
        interface_.append("interface");
        // 添加空格
        TextUtils.addSpace(interface_);
        // 添加接口名
        interface_.append(type.getTypeName());
        // 添加继承的父接口
        JavaUtils.putSuperInterfaces(interface_, superInterfaces);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为接口放入具体的内容.
     * </pre>
     *
     * @param interface_  接口
     * @param indentCount 缩进次数
     */
    @Override
    protected void putContent(StringBuilder interface_, Integer indentCount) {
        // 添加空格
        TextUtils.addSpace(interface_);
        // 添加左花括号
        interface_.append("{");
        // 增加缩进次数
        indentCount++;
        // 获取方法集合的迭代器
        Iterator<Method> iterator = methods.iterator();
        // 遍历方法集合
        while (iterator.hasNext()) {
            // 换行
            TextUtils.addLine(interface_);
            // 取出方法
            Method method = iterator.next();
            // 设置方法为接口方法
            method.setInterfaceMethod(true);
            // 添加方法
            interface_.append(method.toString(indentCount));
            // 如果还有下一个方法
            if (iterator.hasNext()) {
                // 加一个空行
                TextUtils.addLine(interface_);
            }
        }
        // 减小缩进次数
        indentCount--;
        // 换行
        TextUtils.addLine(interface_);
        // 缩进
        TextUtils.addIndentation(interface_, indentCount);
        // 添加右花括号
        interface_.append('}');
    }
}
