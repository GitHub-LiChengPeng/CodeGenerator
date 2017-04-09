package core.generator;

import core.database.Table;
import core.file.java.Interface;
import core.file.java.Method;
import core.file.java.Type;

/**
 * 接口生成器.
 *
 * @author 李程鹏
 */
public class InterfaceGenerator extends Generator {
    /**
     * 接口类型(接口名).
     */
    protected Type interfaceType;

    /**
     * 接口文件注释
     */
    protected String comment;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造初始化生成器.
     * </pre>
     *
     * @param table 表格对象
     */
    public InterfaceGenerator(Table table) {
        // 调用父类的构造函数
        super(table);
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
        // 设置接口的文档注释
        generateFileDocument(interface_, comment);
        // 设置设置访问控制符
        interface_.setVisibility("public ");
        // 设置接口的类型
        interface_.setType(interfaceType);
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
        // 为接口导入实体类型
        interface_.addImport(entityType);
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
        // 放入增加方法的基本信息
        putAddMethodInfo(method);
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
        // 放入删除方法的基本信息
        putDeleteMethodInfo(method);
        // 获取方法的参数类型
        Type parameterType = method.getParameters().get(0).getType();
        // 为接口导入参数类型
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
        // 放入修改方法的基本信息
        putUpdateMethodInfo(method);
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
        // 放入查询方法(按主键查)的基本信息
        putReadOneMethodInfo(method);
        // 获取方法的参数类型
        Type parameterType = method.getParameters().get(0).getType();
        // 为接口导入参数类型
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
        // 放入查询方法(查询所有)的基本信息
        putReadAllMethodInfo(method);
        // 为接口添加需要导入的返回值类型
        interface_.addImport(method.getType());
        // 为接口添加该方法
        interface_.addMethod(method);
    }
}