package core.generator.service;

import core.database.Table;
import core.file.java.Class;
import core.file.java.Field;
import core.file.java.Method;
import core.file.java.Type;
import core.generator.ClassGenerator;
import core.util.PackageUtils;

/**
 * Service层实现类生成器.
 *
 * @author 李程鹏
 */
public class ServiceImplGenerator extends ClassGenerator {
    /**
     * Service实现类需要注入的Dao接口类型
     */
    private Type daoType;

    /**
     * Dao层接口名字
     */
    private String daoName;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造初始化生成器对象.
     * </pre>
     *
     * @param table 表格对象
     */
    public ServiceImplGenerator(Table table) {
        // 调用父类的构造函数
        super(table);
        // 初始化类的类型
        this.classType = new Type(PackageUtils.SERVICE_IMPL.getValue() + getTableName() + "ServiceImpl");
        // 初始化接口类型
        this.interfaceType = new Type(PackageUtils.SERVICE.getValue() + getTableName() + "Service");
        // 初始化Dao接口类型
        this.daoType = new Type(PackageUtils.DAO.getValue() + getTableName() + "Dao");
        // 初始化Dao层接口的名字
        this.daoName = getCommonName("Dao");
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 生成代码.
     * </pre>
     *
     * @return {@code core.file.java.Class} - 生成的Dao层实现类
     */
    @Override
    public Class generate() {
        // 新建一个类
        Class class_ = new Class();
        // 设置类的文档注释
        generateFileDocument(class_, "操作" + table.getRemark() + "对象的业务层代码");
        // 添加类的注解
        class_.addAnnotation("@Service(\"" + getCommonName("Service") + "\")");
        class_.addAnnotation("@Transactional");
        // 设置访问控制符
        class_.setVisibility("public ");
        // 设置类的类型
        class_.setType(classType);
        // 为类生成属性
        generateField(class_);
        // 为类生成增加方法
        generateAddMethod(class_);
        // 为类生成删除方法
        generateDeleteMethod(class_);
        // 为类生成修改方法
        generateUpdateMethod(class_);
        // 为类生成查询方法(按主键查).
        generateReadOneMethod(class_);
        // 为类生成查询方法(查询所有).
        generateReadAllMethod(class_);
        // 添加类需要实现的接口
        class_.addImplementedInterface(interfaceType);
        // 添加类需要导入的类型
        addImports(class_);
        // 返回生成的类
        return class_;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 添加类需要导入的类型
     * </pre>
     *
     * @param class_ 类
     */
    private void addImports(Class class_) {
        // 导入通用类型
        addCommonImports(class_);
        // 导入接口类型
        class_.addImport(interfaceType);
        // 导入被注入的接口类型
        class_.addImport(daoType);
        // 导入实体类型
        class_.addImport(entityType);
        // 导入事务标签类型
        class_.addImport(new Type("org.springframework.transaction.annotation.Transactional"));
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为类生成属性.
     * </pre>
     *
     * @param class_ 类
     */
    private void generateField(Class class_) {
        // 新建一个属性
        Field field = new Field();
        // 添加属性的文档注释
        generateFieldDocument(field, "持久层接口");
        // 为属性添加注解
        field.addAnnotation("@Autowired");
        field.addAnnotation("@Qualifier(\"" + daoName + "\")");
        // 设置访问控制符
        field.setVisibility("private ");
        // 设置属性类型
        field.setType(daoType);
        // 设置属性名
        field.setName(daoName);
        // 为类添加属性
        class_.addField(field);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为类生成增加方法.
     * </pre>
     *
     * @param class_ 类
     */
    private void generateAddMethod(Class class_) {
        // 新建一个方法
        Method method = new Method();
        // 放入增加方法的基本信息
        putAddMethodInfo(method);
        // 添加注释
        method.addStatement("// 调用持久层接口添加实体对象");
        // 添加方法语句
        method.addStatement(daoName + ".addEntity(entity);");
        // 为类添加方法
        class_.addMethod(method);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为类生成删除方法.
     * </pre>
     *
     * @param class_ 类
     */
    private void generateDeleteMethod(Class class_) {
        // 新建一个方法
        Method method = new Method();
        // 放入删除方法的基本信息
        putDeleteMethodInfo(method);
        // 添加注释
        method.addStatement("// 调用持久层接口删除实体对象");
        // 添加方法语句
        method.addStatement(daoName + ".deleteEntity(id);");
        // 为类添加方法
        class_.addMethod(method);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为类生成修改方法.
     * </pre>
     *
     * @param class_ 类
     */
    private void generateUpdateMethod(Class class_) {
        // 新建一个方法
        Method method = new Method();
        // 放入修改方法的基本信息
        putUpdateMethodInfo(method);
        // 添加注释
        method.addStatement("// 调用持久层接口修改实体对象");
        // 添加方法语句
        method.addStatement(daoName + ".updateEntity(entity);");
        // 为类添加方法
        class_.addMethod(method);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为类生成查询方法(按主键查).
     * </pre>
     *
     * @param class_ 类
     */
    private void generateReadOneMethod(Class class_) {
        // 新建一个方法
        Method method = new Method();
        // 放入查询方法的基本信息
        putReadOneMethodInfo(method);
        // 添加注释
        method.addStatement("// 调用持久层接口查询实体对象");
        // 添加方法语句
        method.addStatement("return " + daoName + ".readEntity(id);");
        // 为类添加方法
        class_.addMethod(method);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为类生成查询方法(查询所有).
     * </pre>
     *
     * @param class_ 类
     */
    private void generateReadAllMethod(Class class_) {
        // 新建一个方法
        Method method = new Method();
        // 放入查询方法的基本信息
        putReadAllMethodInfo(method);
        // 添加注释
        method.addStatement("// 调用持久层接口查询实体对象集合");
        // 添加方法语句
        method.addStatement("return " + daoName + ".readEntities();");
        // 为类导入返回值类型
        class_.addImport(method.getType());
        // 为类添加方法
        class_.addMethod(method);
    }
}