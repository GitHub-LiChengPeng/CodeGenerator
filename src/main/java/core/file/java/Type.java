package core.file.java;

import core.util.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 数据类型.
 *
 * @author 李程鹏
 */
public class Type implements Comparable<Type> {
    /**
     * 全限定名
     */
    private String fullName;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 类型名
     */
    private String typeName;

    /**
     * 泛型参数
     */
    private List<Type> typeArguments = new ArrayList<>();

    /**
     * 是否需要导包
     */
    private boolean needImport;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 根据指定的值构造初始化一个类型实例.
     * </pre>
     *
     * @param value 值
     */
    public Type(String value) {
        // 解析指定的值
        parse(value);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 判断类型是否需要导入.
     * </pre>
     *
     * @return {@code boolean} - 判断结果
     */
    public boolean isNeedImport() {
        // 返回判断结果
        return needImport;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加泛型参数.
     * </pre>
     *
     * @param type 类型
     */
    public void addTypeArgument(Type type) {
        // 添加入集合
        typeArguments.add(type);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 解析值.
     * </pre>
     *
     * @param value 值
     */
    private void parse(String value) {
        // 获取指定值中泛型起始字符的下标位置
        int index = value.indexOf('<');
        // 如果没获取到(即指定值中不存在泛型).
        if (index == -1) {
            // 解析指定值中的类型部分
            parseType(value);
        } else {// 如果指定值中存在泛型
            // 解析指定值中的类型部分
            parseType(value.substring(0, index));
            // 解析指定值中的泛型部分
            parseGeneric(value.substring(index));
        }
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 解析指定值中的类型部分.
     * </pre>
     *
     * @param typeValue 指定值的类型部分
     */
    private void parseType(String typeValue) {
        // 将去掉前后空格后的类型部分赋值给类的全限定名属性
        fullName = typeValue.trim();
        // 如果该类型值有包结构
        if (fullName.contains(".")) {
            // 取出类型值所在的包名
            packageName = fullName.substring(0, fullName.lastIndexOf('.'));
            // 取出类型名
            typeName = fullName.substring(packageName.length() + 1);
            // 不是java.lang包下的类才需要导入
            needImport = !"java.lang".equals(packageName);
        } else {// 如果该类型值没有包结构
            // 根据具体的名字去实例化具体的原始包装类.
            switch (fullName) {
                case "byte":
                    parseType("java.lang.Byte");
                    break;
                case "short":
                    parseType("java.lang.Short");
                    break;
                case "int":
                    parseType("java.lang.Integer");
                    break;
                case "long":
                    parseType("java.lang.Long");
                    break;
                case "char":
                    parseType("java.lang.Character");
                    break;
                case "float":
                    parseType("java.lang.Float");
                    break;
                case "double":
                    parseType("java.lang.Double");
                    break;
                case "boolean":
                    parseType("java.lang.Boolean");
                    break;
                default:// 如果找不到对应的类型
                    // 设置类型名为全限定名
                    typeName = fullName;
                    // 不需要导入
                    needImport = false;
                    // 设置包名为空字符串
                    packageName = "";
                    break;
            }
        }
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 解析指定值中的泛型部分.
     * </pre>
     *
     * @param genericValue 指定值的泛型部分
     */
    private void parseGeneric(String genericValue) {
        // 获取泛型部分的结束标志字符的下标位置
        int index = genericValue.lastIndexOf('>');
        // 如果该泛型部分不存在结束标志字符
        if (index == -1) {
            // 说明指定值中的泛型部分有语法错误
            throw new RuntimeException("存在泛型语法错误!");
        }
        // 取出泛型部分里的参数
        String arguments = genericValue.substring(1, index);
        // 将泛型参数字符串使用",","<"和">"进行分隔，分隔结果保留分隔符.
        StringTokenizer stringTokenizer = new StringTokenizer(arguments, ",<>", true);
        // 定义并初始化未结束的泛型个数
        int notEnd = 0;
        // 定义参数变量
        StringBuilder argument = new StringBuilder();
        // 遍历泛型参数字符串
        while (stringTokenizer.hasMoreTokens()) {
            // 获取分隔出来的值
            String token = stringTokenizer.nextToken();
            // 如果开始了一个新的泛型
            if ("<".equals(token)) {
                // 将泛型的起始字符放入参数变量中
                argument.append(token);
                // 未结束的泛型个数加一
                notEnd++;
            } else if (">".equals(token)) {// 如果结束了一个泛型
                // 将泛型的结束字符放入参数变量中
                argument.append(token);
                // 未结束的泛型个数减一
                notEnd--;
            } else if (",".equals(token)) {// 如果该值是逗号分隔符(Map结构会遇到逗号分隔符)
                // 如果这个时候不存在未结束的泛型,则参数变量中存着一个完整的参数类型
                if (notEnd == 0) {
                    // 将参数变量的值加入泛型参数的集合中
                    typeArguments.add(new Type(argument.toString()));
                    // 清空参数变量
                    argument.setLength(0);
                } else {// 如果还存在未结束的泛型
                    // 将逗号分隔符放入参数变量中
                    argument.append(token);
                }
            } else {// 如果该值不是分隔符,那就是类型名
                // 将类型名加入参数变量中
                argument.append(token);
            }
        }
        // 如果遍历完成后还存在未结束的泛型
        if (notEnd != 0) {
            // 说明存在泛型语法错误
            throw new RuntimeException("存在泛型语法错误!");
        }
        // 取出参数变量的值
        String value = argument.toString();
        // 如果该值不为空串
        if (value.length() > 0) {
            // 将它添加到泛型参数的集合中
            typeArguments.add(new Type(value));
        }
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取该类型所在的包名.
     * </pre>
     *
     * @return {@code java.lang.String} - 包名
     */
    public String getPackageName() {
        // 返回包名
        return packageName;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取类型的全限定名.
     * </pre>
     *
     * @return {@code java.lang.String} - 全限定名
     */
    public String getFullName() {
        // 获取类型的全限定名
        return getName(true);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取类型的名字.
     * </pre>
     *
     * @return {@code java.lang.String} - 类型名
     */
    public String getTypeName() {
        // 获取类型的名字
        return getName(false);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取名字.
     * </pre>
     *
     * @param isFull 为真表示获取类型的全限定名,为假表示获取类型名.
     * @return {@code java.lang.String} - 名字
     */
    private String getName(boolean isFull) {
        // 定义返回结果
        StringBuilder name = new StringBuilder();
        if (isFull) {
            // 将类型的全限定名添加入返回结果
            name.append(fullName);
        } else {
            // 将类型名添加入返回结果
            name.append(typeName);
        }
        // 如果类型的泛型参数不为空
        if (typeArguments.size() > 0) {
            // 用于判断是否是第一个参数的标志位
            boolean first = true;
            // 添加泛型开始标志
            name.append('<');
            // 遍历泛型参数集合
            for (Type typeArgument : typeArguments) {
                if (first) {// 如果是第一个参数
                    // 设置标志位为假
                    first = false;
                } else {// 如果不是第一个参数
                    // 在参数前边加上逗号
                    name.append(",");
                    // 添加空格
                    TextUtils.addSpace(name);
                }
                // 获取泛型参数名
                name.append(typeArgument.getName(isFull));
            }
            // 添加泛型结束标志
            name.append('>');
        }
        // 返回结果
        return name.toString();
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取需要导入的值集合.
     * </pre>
     *
     * @return {@code java.util.List<java.lang.String>} - 待导入的值集合
     */
    public List<String> getImports() {
        // 定义并初始化结果集
        List<String> imports = new ArrayList<>();
        // 如果本类类型需要导入
        if (needImport) {
            // 将本类类型的全限定名加入结果集中
            imports.add(fullName);
        }
        // 遍历类型的泛型参数集合
        for (Type typeArgument : typeArguments) {
            // 将泛型参数中需要导入的类型加入结果集中
            imports.addAll(typeArgument.getImports());
        }
        // 返回结果集
        return imports;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 判断两个对象是否相等.
     * </pre>
     *
     * @param object 待判断对象
     * @return {@code boolean} - 判断结果
     */
    @Override
    public boolean equals(Object object) {
        // 如果两个对象的地址相等
        if (this == object) {
            // 则这两个对象是相等的
            return true;
        }
        // 如果待判断对象不是该类的实例
        if (!(object instanceof Type)) {
            // 这两个对象不相等
            return false;
        }
        // 将待比较对象强转为类型对象
        Type other = (Type) object;
        // 如果两个对象的全限定名相等,则这两个对象相等.
        return getFullName().equals(other.getFullName());
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 比较两个类型(需要实现Comparable接口是因为TreeMap放入元素时需要排序).
     * </pre>
     *
     * @param other 另外一个类型
     * @return {@code int} - 比较结果
     */
    public int compareTo(Type other) {
        // 返回比较结果
        return getFullName().compareTo(other.getFullName());
    }
}