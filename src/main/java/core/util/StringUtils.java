package core.util;

import core.file.java.Type;

import java.util.Set;
import java.util.TreeSet;

/**
 * 字符串操作工具类.
 *
 * @author 李程鹏
 */
public class StringUtils {
    /**
     * <strong>Description:</strong>
     * <pre>
     * 根据需要导入的类型创建导入语句.
     * </pre>
     *
     * @param imports 需要导入的语句
     * @return {@code java.util.Set<java.lang.String>} - 导入语句集合
     */
    public static Set<String> getImportStatements(Set<Type> imports) {
        // 定义导入语句集合
        Set<String> importStatements = new TreeSet<>();
        // 定义导入语句变量
        StringBuilder importStatement = new StringBuilder();
        // 遍历需要导入的类型
        for (Type import_ : imports) {
            // 遍历类型中需要导入的值
            for (String importValue : import_.getImports()) {
                // 清空导入语句变量
                importStatement.setLength(0);
                // 添加导入关键字
                importStatement.append("import");
                // 添加空格
                TextUtils.addSpace(importStatement);
                // 添加需要导入的值
                importStatement.append(importValue);
                // 添加分号
                importStatement.append(';');
                // 将导入语句放入集合中
                importStatements.add(importStatement.toString());
            }
        }
        // 返回导入语句集合
        return importStatements;
    }
}
