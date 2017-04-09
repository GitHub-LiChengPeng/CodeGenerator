package core.util;

import core.file.java.Interface;
import core.file.java.Type;
import core.file.java.Class;

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

    /**
     * <strong>Description:</strong>
     * <pre>
     * 将输入的字符串转成驼峰式字符串.
     * </pre>
     *
     * @param input   输入的字符串
     * @param isUpper 是否按照大驼峰式规则
     * @return {@code java.lang.String} - 驼峰式字符串
     */
    public static String toCamelCase(String input, boolean isUpper) {
        // 定义结果值变量
        StringBuilder output = new StringBuilder();
        // 用于判断下一个字符是否需要大写的标志
        boolean nextUpperCase = false;
        // 循环遍历输入字符串的每一个字符
        for (int i = 0; i < input.length(); i++) {
            // 取出当前字符
            char c = input.charAt(i);
            // 分情况处理字符
            switch (c) {
                // 如果当前字符是一些特殊的分隔符
                case '_':
                case '-':
                case '@':
                case '$':
                case '#':
                case ' ':
                case '/':
                case '&':
                    // 如果结果值中存在了字符
                    if (output.length() > 0) {
                        // 下一个字符需要大写
                        nextUpperCase = true;
                    }
                    break;
                default:// 如果当前字符是字母
                    // 如果该字符需要大写
                    if (nextUpperCase) {
                        // 将该字符的大写形式放入结果变量中
                        output.append(Character.toUpperCase(c));
                        // 设置标志值为假
                        nextUpperCase = false;
                    } else {// 如果当前字符不需要大写
                        // 将该字符的小写形式放入结果变量中
                        output.append(Character.toLowerCase(c));
                    }
                    break;
            }
        }
        // 如果需要按照大驼峰规则来转换
        if (isUpper) {
            // 将结果值变量中的第一个字符变成大写
            output.setCharAt(0, Character.toUpperCase(output.charAt(0)));
        }
        // 返回结果值
        return output.toString();
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 根据类对象生成文件名.
     * </pre>
     *
     * @param class_ 类对象
     * @return {@code java.lang.String} - 文件名
     */
    public static String getJavaFileName(Class class_) {
        // 返回对应的文件名
        return class_.getType().getTypeName() + ".java";
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 根据接口对象生成文件名.
     * </pre>
     *
     * @param interface_ 接口对象
     * @return {@code java.lang.String} - 文件名
     */
    public static String getJavaFileName(Interface interface_) {
        // 返回对应的文件名
        return interface_.getType().getTypeName() + ".java";
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 去掉字符串末尾的点.
     * </pre>
     *
     * @param content 字符串内容
     * @return {@code java.lang.String} - 处理结果
     */
    public static String removeLastDot(String content) {
        // 返回处理结果
        return content.substring(0, content.length() - 1);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 判断字符串是否是空串.
     * </pre>
     *
     * @param content 字符串内容
     * @return {@code boolean} - 判断结果
     */
    public static boolean isNotEmpty(String content) {
        // 返回判断结果
        return content != null && !"".equals(content.trim());
    }
}