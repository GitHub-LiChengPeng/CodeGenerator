package core.generator.dao;

import core.database.Table;
import core.file.java.Interface;
import core.file.java.Method;
import core.file.java.Parameter;
import core.file.java.Type;
import core.generator.Generator;
import core.util.PackageUtils;

/**
 * Dao层接口生成器.
 *
 * @author 李程鹏
 */
public class DaoGenerator extends Generator {
    /**
     * 接口类型(接口名).
     */
    private Type interfaceType;

    /**
     * 接口操作的实体类型
     */
    private Type entityType;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造初始化实体.
     * </pre>
     *
     * @param table 表格对象
     */
    public DaoGenerator(Table table) {
        // 调用父类的构造函数
        super(table);
        // 实例化接口的类型
        this.interfaceType = new Type(PackageUtils.DAO.getValue() + getTableName() + "Dao");
        // 实例化实体的类型
        this.entityType = new Type(PackageUtils.ENTITY.getValue() + getTableName());
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 生成代码.
     * </pre>
     *
     * @return {@code core.file.java.Interface} - 生成的接口类.
     */
    @Override
    public Interface generate() {
        // 新建一个接口
        Interface interface_ = new Interface();
        // 设置接口的类型
        interface_.setType(interfaceType);
        // 设置设置访问控制符
        interface_.setVisibility("public ");
        // 为接口生成添加方法
        generateAddMethod(interface_);
        // 为接口生成删除方法
        generateDeleteMethod(interface_);
        // 为接口生成修改方法
        generateUpdateMethod(interface_);
        // 为接口生成查询方法(按主键查).
        generateReadOneMethod(interface_);
        // 为接口生成查询方法(查询所有).
        generateReadAllMethod(interface_);
        // 返回接口
        return interface_;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为接口生成增加方法.
     * </pre>
     *
     * @param interface_ 接口
     */
    private void generateAddMethod(Interface interface_) {
        // 新建一个方法
        Method method = new Method();
        // 设置方法名
        method.setName("addEntity");
        // 设置方法的参数
        method.addParameter(new Parameter(entityType, "entity"));
        // 为接口添加需要导入的类型
        interface_.addImport(entityType);
        // 为接口添加该方法
        interface_.addMethod(method);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为接口生成删除方法.
     * </pre>
     *
     * @param interface_ 接口
     */
    private void generateDeleteMethod(Interface interface_) {
        // 新建一个方法
        Method method = new Method();
        // 设置方法名
        method.setName("deleteEntity");
        // 定义参数类型
        Type parameterType = new Type("int");
        // 为方法设置参数
        method.addParameter(new Parameter(parameterType, "id"));
        // 为接口添加需要导入的类型
        interface_.addImport(parameterType);
        // 为接口添加该方法
        interface_.addMethod(method);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为接口生成修改方法.
     * </pre>
     *
     * @param interface_ 接口
     */
    private void generateUpdateMethod(Interface interface_) {
        // 新建一个方法
        Method method = new Method();
        // 添加方法名
        method.setName("updateEntity");
        // 为方法设置参数
        method.addParameter(new Parameter(entityType, "entity"));
        // 为接口添加需要导入的类型
        interface_.addImport(entityType);
        // 为接口添加该方法
        interface_.addMethod(method);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为接口生成查询方法(按主键查).
     * </pre>
     *
     * @param interface_ 接口
     */
    private void generateReadOneMethod(Interface interface_) {
        // 新建一个方法
        Method method = new Method();
        // 设置方法名
        method.setName("readEntity");
        // 设置方法返回值类型
        method.setType(entityType);
        // 定义参数类型
        Type parameterType = new Type("int");
        // 为方法设置参数
        method.addParameter(new Parameter(parameterType, "id"));
        // 为接口添加需要导入的返回值类型
        interface_.addImport(entityType);
        // 为接口添加需要导入的参数类型
        interface_.addImport(parameterType);
        // 为接口添加该方法
        interface_.addMethod(method);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为接口生成查询方法(查询所有).
     * </pre>
     *
     * @param interface_ 接口
     */
    private void generateReadAllMethod(Interface interface_) {
        // 新建一个方法
        Method method = new Method();
        // 定义方法返回值类型
        Type returnType = new Type("java.util.List");
        // 为方法的返回值类型添加泛型参数
        returnType.addTypeArgument(entityType);
        // 设置方法名
        method.setName("readEntities");
        // 设置方法返回值类型
        method.setType(returnType);
        // 为接口添加需要导入的返回值类型
        interface_.addImport(returnType);
        // 为接口添加需要导入的实体类型
        interface_.addImport(entityType);
        // 为接口添加该方法
        interface_.addMethod(method);
    }
}
