package core.generator.dao;

import core.database.Table;
import core.file.java.*;
import core.file.java.Class;
import core.generator.ClassGenerator;
import core.util.PackageUtils;

/**
 * Dao层实现类生成器.
 *
 * @author 李程鹏
 */
public class DaoImplGenerator extends ClassGenerator {
    /**
     * 代码中使用的会话工厂类型
     */
    private Type sessionFactoryType;

    /**
     * 公用的注释
     */
    private String commonStatement;

    /**
     * 公用的语句
     */
    private String commonComment;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造实例化生成器对象.
     * </pre>
     *
     * @param table 表格对象
     */
    public DaoImplGenerator(Table table) {
        // 调用父类的构造函数
        super(table);
        // 初始化类的类型
        this.classType = new Type(PackageUtils.DAO_IMPL.getValue() + getTableName() + "DaoImpl");
        // 初始化接口类型
        this.interfaceType = new Type(PackageUtils.DAO.getValue() + getTableName() + "Dao");
        // 初始化会话工厂类型
        this.sessionFactoryType = new Type("org.hibernate.SessionFactory");
        // 初始化公用注释
        this.commonComment = "// 获取会话对象";
        // 初始化公用语句
        this.commonStatement = "Session session = sessionFactory.getCurrentSession();";
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 生成代码.
     * </pre>
     *
     * @return {@code core.file.java.Class} - 生成的Dao层实现类.
     */
    @Override
    public Class generate() {
        // 新建一个类
        Class class_ = new Class();
        // 设置类的文档注释
        generateFileDocument(class_, "操作" + table.getRemark() + "对象的持久层代码");
        // 添加类的注解
        class_.addAnnotation("@Repository(\"" + getCommonName("Dao") + "\")");
        // 设置类的访问控制符
        class_.setVisibility("public ");
        // 设置类的类型
        class_.setType(classType);
        // 为类生成属性
        generateField(class_);
        // 为类生成构造方法
        generateConstructor(class_);
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
        // 导入实体类型
        class_.addImport(entityType);
        // 导入会话工厂类型
        class_.addImport(sessionFactoryType);
        // 导入会话类型
        class_.addImport(new Type("org.hibernate.Session"));
        // 导入条件对象类型
        class_.addImport(new Type("org.hibernate.Criteria"));
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
        generateFieldDocument(field, "会话工厂");
        // 设置访问控制符
        field.setVisibility("private ");
        // 设置终结标志
        field.setFinal(true);
        // 设置属性的类型
        field.setType(sessionFactoryType);
        // 设置属性名
        field.setName("sessionFactory");
        // 为类添加属性
        class_.addField(field);
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为类生成构造方法.
     * </pre>
     *
     * @param class_ 类
     */
    private void generateConstructor(Class class_) {
        // 新建一个方法
        Method method = new Method();
        // 添加注解
        method.addAnnotation("@Autowired");
        // 设置访问控制符
        method.setVisibility("public ");
        // 设置构造函数标志
        method.setConstructor(true);
        // 设置方法名
        method.setName(classType.getTypeName());
        // 定义参数名字
        String parameterName = "sessionFactory";
        // 定义一个参数
        Parameter parameter = new Parameter(sessionFactoryType, parameterName);
        // 为参数添加注解
        parameter.addAnnotation("@Qualifier(\"sessionFactory\")");
        // 为方法添加参数
        method.addParameter(parameter);
        // 添加方法语句
        method.addStatement("// 为属性赋值");
        method.addStatement("this.sessionFactory = sessionFactory;");
        // 为方法添加文档注释
        generateDocument(method, "构造注入会话工厂对象", parameterName, "会话工厂");
        // 为类添加方法
        class_.addMethod(method);
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
        // 添加方法语句
        method.addStatement(commonComment);
        method.addStatement(commonStatement);
        method.addStatement("// 添加实体对象");
        method.addStatement("session.save(entity);");
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
        // 为方法添加语句
        method.addStatement(commonComment);
        method.addStatement(commonStatement);
        method.addStatement("// 根据主键获取实体对象");
        method.addStatement(entityTypeName + " entity = (" + entityTypeName + ") session.get(" + entityTypeName + ".class, id);");
        method.addStatement("// 删除实体对象");
        method.addStatement("session.delete(entity);");
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
        // 添加方法语句
        method.addStatement(commonComment);
        method.addStatement(commonStatement);
        method.addStatement("// 更新实体对象");
        method.addStatement("session.update(entity);");
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
        // 添加方法语句
        method.addStatement(commonComment);
        method.addStatement(commonStatement);
        method.addStatement("// 根据主键查询并返回查询结果");
        method.addStatement("return (" + entityTypeName + ") session.get(" + entityTypeName + ".class, id);");
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
        // 添加方法语句
        method.addStatement(commonComment);
        method.addStatement(commonStatement);
        method.addStatement("// 创建QBC查询对象");
        method.addStatement("Criteria criteria = session.createCriteria(" + entityTypeName + ".class);");
        method.addStatement("// 查询并返回结果集");
        method.addStatement("return criteria.list();");
        // 为类导入返回值类型
        class_.addImport(method.getType());
        // 为类添加方法
        class_.addMethod(method);
    }
}