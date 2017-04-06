package core.file.java;

import core.util.StringUtils;
import core.util.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Java文件类.
 *
 * @author 李程鹏
 */
public abstract class JavaFile extends JavaElement {
    /**
     * 类型(类名或者接口名)
     */
    protected Type type;

    /**
     * 需要导入的类型
     */
    protected Set<Type> imports = new TreeSet<>();

    /**
     * 方法集合
     */
    protected List<Method> methods = new ArrayList<>();

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取类型.
     * </pre>
     *
     * @return {@code core.file.java.Type} - 类型
     */
    public Type getType() {
        // 返回类型
        return type;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 设置类型.
     * </pre>
     *
     * @param type 类型
     */
    public void setType(Type type) {
        // 赋值
        this.type = type;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加需要导入的类型.
     * </pre>
     *
     * @param import_ 需要导入的类型
     */
    public void addImport(Type import_) {
        // 如果这个类型需要导入,而且它的包名和本文件的包名不相同.
        if (import_.isNeedImport() && !import_.getPackageName().equals(type.getPackageName())) {
            // 添加入集合
            imports.add(import_);
        }
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

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为Java文件放入导入的类型.
     * </pre>
     *
     * @param javaFile Java文件
     */
    protected void putImports(StringBuilder javaFile) {
        // 获取文件类型所在的包名
        String packageName = type.getPackageName();
        // 如果包名不为空
        if (packageName != null && packageName.length() > 0) {
            // 添加包关键字
            javaFile.append("package");
            // 添加空格
            TextUtils.addSpace(javaFile);
            // 添加包名
            javaFile.append(type.getPackageName());
            // 添加分号
            javaFile.append(';');
            // 换行
            TextUtils.addLine(javaFile);
            // 换行
            TextUtils.addLine(javaFile);
        }
        // 获取导入语句集合
        Set<String> importStatements = StringUtils.getImportStatements(imports);
        // 遍历导入语句集合
        for (String importStatement : importStatements) {
            // 添加导入语句
            javaFile.append(importStatement);
            // 换行
            TextUtils.addLine(javaFile);
        }
        // 如果已经添加了导入语句
        if (importStatements.size() > 0) {
            // 添加一个空行
            TextUtils.addLine(javaFile);
        }
    }
}