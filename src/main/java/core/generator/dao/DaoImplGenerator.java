package core.generator.dao;

import core.database.Table;
import core.file.java.*;
import core.file.java.Class;
import core.generator.Generator;
import core.util.PackageUtils;
import core.util.StringUtils;

/**
 * Dao层实现类生成器.
 *
 * @author 李程鹏
 */
public class DaoImplGenerator extends Generator {
    /**
     * 类的类型
     */
    private Type classType;

    /**
     * 实现类需要操作的实体类型
     */
    private Type entityType;

    /**
     * 实现类需要实现的接口类型
     */
    private Type daoType;

    /**
     * 代码中使用的会话工厂类型
     */
    private Type sessionFactoryType;

    /**
     * 需要操作的实体类型名
     */
    private String entityTypeName;

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
        // 初始化实体类型
        this.entityType = new Type(PackageUtils.ENTITY.getValue() + getTableName());
        // 初始化接口类型
        this.daoType = new Type(PackageUtils.DAO.getValue() + getTableName() + "Dao");
        // 初始化会话工厂类型
        this.sessionFactoryType = new Type("org.hibernate.SessionFactory");
        // 初始化实体类型名
        this.entityTypeName = entityType.getTypeName();
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
        // 设置类的类型
        class_.setType(classType);
        // 设置访问控制符
        class_.setVisibility("public ");
        // 创建本类在Spring工厂中的名字
        String name = StringUtils.toCamelCase(table.getName(), false) + "Dao";
        // 添加注解
        class_.addAnnotation("@Repository(\"" + name + "\")");
        // 为类生成属性
        generateField(class_);
        // 为类生成构造方法
        generateConstructor(class_);
        // 为类生成添加方法
        generateAddMethod(class_);
        // 为类生成删除方法
        generateDeleteMethod(class_);
        // 为类生成更新方法
        generateUpdateMethod(class_);
        // 为类生成查询方法(按主键查).
        generateReadOneMethod(class_);
        // 为类生成查询方法(查询所有).
        generateReadAllMethod(class_);
        // 类需要实现Dao层接口
        class_.addImplementedInterface(daoType);
        // 导入接口类型
        class_.addImport(daoType);
        // 导入实体类型
        class_.addImport(entityType);
        // 导入会话工厂类型
        class_.addImport(sessionFactoryType);
        // 导入会话类型
        class_.addImport(new Type("org.hibernate.Session"));
        // 导入Autowired注解类型
        class_.addImport(new Type("org.springframework.beans.factory.annotation.Autowired"));
        // 导入Qualifier注解类型
        class_.addImport(new Type("org.springframework.beans.factory.annotation.Qualifier"));
        // 导入Repository注解类型
        class_.addImport(new Type("org.springframework.stereotype.Repository"));
        // 导入Criteria类型
        class_.addImport(new Type("org.hibernate.Criteria"));
        // 返回生成的类
        return class_;
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
        // 定义一个属性
        Parameter parameter = new Parameter(sessionFactoryType, "sessionFactory");
        // 为属性添加注解
        parameter.addAnnotation("@Qualifier(\"sessionFactory\")");
        // 设置方法名
        method.setName(classType.getTypeName());
        // 添加方法语句
        method.addStatement("// 为属性赋值");
        method.addStatement("this.sessionFactory = sessionFactory;");
        // 为方法添加属性
        method.addParameter(parameter);
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
        // 设置访问控制符
        method.setVisibility("public ");
        // 设置方法名
        method.setName("addEntity");
        // 新建一个参数
        Parameter parameter = new Parameter(entityType, "entity");
        // 为方法添加参数
        method.addParameter(parameter);
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
     * 为类添加删除方法.
     * </pre>
     *
     * @param class_ 类
     */
    private void generateDeleteMethod(Class class_) {
        // 新建一个方法
        Method method = new Method();
        // 设置访问控制符
        method.setVisibility("public ");
        // 设置方法名
        method.setName("deleteEntity");
        // 新建一个参数
        Parameter parameter = new Parameter(new Type("int"), "id");
        // 为方法添加参数
        method.addParameter(parameter);
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
     * 为类添加修改方法.
     * </pre>
     *
     * @param class_ 类
     */
    private void generateUpdateMethod(Class class_) {
        // 新建一个方法
        Method method = new Method();
        // 设置访问控制符
        method.setVisibility("public ");
        // 设置方法名
        method.setName("updateEntity");
        // 新建一个参数
        Parameter parameter = new Parameter(entityType, "entity");
        // 为方法添加参数
        method.addParameter(parameter);
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
     * 为类添加查询方法(按主键查).
     * </pre>
     *
     * @param class_ 类
     */
    private void generateReadOneMethod(Class class_) {
        // 新建一个方法
        Method method = new Method();
        // 设置访问控制符
        method.setVisibility("public ");
        // 设置方法名
        method.setName("readEntity");
        // 设置方法的返回值类型
        method.setType(entityType);
        // 新建一个参数
        Parameter parameter = new Parameter(new Type("int"), "id");
        // 为方法添加参数
        method.addParameter(parameter);
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
     * 为类添加查询方法(查询所有).
     * </pre>
     *
     * @param class_ 类
     */
    private void generateReadAllMethod(Class class_) {
        // 新建一个方法
        Method method = new Method();
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
        // 添加方法语句
        method.addStatement(commonComment);
        method.addStatement(commonStatement);
        method.addStatement("// 创建QBC查询对象");
        method.addStatement("Criteria criteria = session.createCriteria(" + entityTypeName + ".class);");
        method.addStatement("// 查询并返回结果集");
        method.addStatement("return criteria.list();");
        // 为类导入返回值类型
        class_.addImport(returnType);
        // 为类添加方法
        class_.addMethod(method);
    }
}
