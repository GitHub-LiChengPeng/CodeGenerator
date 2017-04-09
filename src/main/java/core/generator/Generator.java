package core.generator;

import core.database.Column;
import core.database.Table;
import core.file.java.*;
import core.util.PackageUtils;
import core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成器抽象类.
 *
 * @author 李程鹏
 */
public abstract class Generator {
    /**
     * 数据库表
     */
    protected Table table;

    /**
     * 需要操作的实体类型
     */
    protected Type entityType;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造初始化生成器.
     * </pre>
     *
     * @param table 表
     */
    public Generator(Table table) {
        // 赋值
        this.table = table;
        // 初始化实体类型
        this.entityType = new Type(PackageUtils.ENTITY.getValue() + getTableName());
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取驼峰样式的表格名.
     * </pre>
     *
     * @return {@code java.lang.String} - 表格名
     */
    protected String getTableName() {
        // 返回大驼峰样式的表格名
        return StringUtils.toCamelCase(table.getName(), true);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取驼峰样式的列名.
     * </pre>
     *
     * @param column 列对象
     * @return {@code java.lang.String} - 列名
     */
    protected String getColumnName(Column column) {
        // 返回小驼峰样式的列名
        return StringUtils.toCamelCase(column.getName(), false);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为属性生成文档注释.
     * </pre>
     *
     * @param field    属性
     * @param document 文档注释
     */
    protected void generateFieldDocument(Field field, String document) {
        // 只有文档注释不为空是才添加
        if (StringUtils.isNotEmpty(document)) {
            field.addDocument("/**");
            // 将注释的前后空格去掉再添加
            field.addDocument(" * " + document.trim());
            field.addDocument(" */");
        }
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加方法的文档注释.
     * </pre>
     *
     * @param method    方法
     * @param statement 文档注释
     * @param map       属性的文档注释集合
     */
    protected void generateMethodDocument(Method method, String statement, Map<String, String> map) {
        method.addDocument("/**");
        method.addDocument(" * " + statement);
        // 遍历方法中的属性
        for (Parameter parameter : method.getParameters()) {
            // 获取属性的名字
            String parameterName = parameter.getName();
            // 添加对属性的注释
            method.addDocument(" * @param " + parameterName + " " + map.get(parameterName));
        }
        method.addDocument(" */");
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加文件文档注释.
     * </pre>
     *
     * @param javaFile java文件
     * @param document 文档注释
     */
    protected void generateFileDocument(JavaFile javaFile, String document) {
        javaFile.addDocument("/**");
        // 如果注释不为空
        if (StringUtils.isNotEmpty(document)) {
            // 添加去掉前后空格的注释
            javaFile.addDocument(" * " + document.trim());
            javaFile.addDocument(" * ");
        }
        // 添加文件编写作者
        javaFile.addDocument(" * @author 李程鹏");
        javaFile.addDocument(" */");
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 生成代码.
     * </pre>
     *
     * @return {@code java.lang.Object} - 生成的代码字符串
     */
    protected abstract Object generate();

    /**
     * <strong>Description:</strong>
     * <pre>
     * 生成注释(这个方法不通用,只是为了减少冗余代码而抽取出来的).
     * </pre>
     *
     * @param method    方法
     * @param methodDoc 方法的文档注释
     * @param paramName 参数名
     * @param paramDoc  参数的文档注释
     */
    protected void generateDocument(Method method, String methodDoc, String paramName, String paramDoc) {
        // 新建一个映射对象
        Map<String, String> map = new HashMap<>();
        // 将参数名和参数的注释映射关联起来
        map.put(paramName, paramDoc);
        // 添加方法的文档注释
        generateMethodDocument(method, methodDoc, map);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 放入增加方法的基本信息.
     * </pre>
     *
     * @param method 方法
     */
    protected void putAddMethodInfo(Method method) {
        // 设置访问控制符
        method.setVisibility("public ");
        // 设置方法名
        method.setName("addEntity");
        // 定义参数名
        String parameterName = "entity";
        // 新建一个参数
        Parameter parameter = new Parameter(entityType, parameterName);
        // 为方法添加参数
        method.addParameter(parameter);
        // 添加方法注释
        generateDocument(method, "增加实体对象", parameterName, "实体");
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 放入删除方法的基本信息.
     * </pre>
     *
     * @param method 方法
     */
    protected void putDeleteMethodInfo(Method method) {
        // 设置访问控制符
        method.setVisibility("public ");
        // 设置方法名
        method.setName("deleteEntity");
        // 定义参数名
        String parameterName = "id";
        // 新建一个参数
        Parameter parameter = new Parameter(new Type("int"), parameterName);
        // 为方法添加参数
        method.addParameter(parameter);
        // 添加方法注释
        generateDocument(method, "根据主键删除实体对象", parameterName, "主键");
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 放入修改方法的基本信息.
     * </pre>
     *
     * @param method 方法
     */
    protected void putUpdateMethodInfo(Method method) {
        // 设置访问控制符
        method.setVisibility("public ");
        // 设置方法名
        method.setName("updateEntity");
        // 定义参数名
        String parameterName = "entity";
        // 新建一个参数
        Parameter parameter = new Parameter(entityType, parameterName);
        // 为方法添加参数
        method.addParameter(parameter);
        // 添加方法注释
        generateDocument(method, "修改实体对象", parameterName, "实体");
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 放入查询方法(按主键查)的基本信息.
     * </pre>
     *
     * @param method 方法
     */
    protected void putReadOneMethodInfo(Method method) {
        // 设置访问控制符
        method.setVisibility("public ");
        // 设置方法名
        method.setName("readEntity");
        // 设置方法的返回值类型
        method.setType(entityType);
        // 定义参数名
        String parameterName = "id";
        // 新建一个参数
        Parameter parameter = new Parameter(new Type("int"), parameterName);
        // 为方法添加参数
        method.addParameter(parameter);
        // 添加方法注释
        generateDocument(method, "根据主键查询实体对象", parameterName, "主键");
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 放入查询方法(查询所有)的基本信息.
     * </pre>
     *
     * @param method 方法
     */
    protected void putReadAllMethodInfo(Method method) {
        // 设置访问控制符
        method.setVisibility("public ");
        // 设置方法名
        method.setName("readEntities");
        // 定义方法返回值类型
        Type returnType = new Type("java.util.List");
        // 设置返回值类型中的泛型参数
        returnType.addTypeArgument(entityType);
        // 为方法设置返回值类型
        method.setType(returnType);
        // 添加方法注释
        generateMethodDocument(method, "查询所有实体对象", null);
    }
}