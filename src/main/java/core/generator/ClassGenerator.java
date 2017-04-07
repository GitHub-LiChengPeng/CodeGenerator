package core.generator;

import core.file.java.Class;
import core.database.Table;
import core.file.java.Method;
import core.file.java.Parameter;
import core.file.java.Type;
import core.util.PackageUtils;
import core.util.StringUtils;

/**
 * 类生成器.
 *
 * @author 李程鹏
 */
public abstract class ClassGenerator extends Generator {
    /**
     * 类的类型
     */
    protected Type classType;

    /**
     * 实现类需要实现的接口类型
     */
    protected Type interfaceType;

    /**
     * 实现类需要操作的实体类型
     */
    protected Type entityType;

    /**
     * 需要操作的实体类型名
     */
    protected String entityTypeName;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造初始化生成器.
     * </pre>
     *
     * @param table 表格对象
     */
    public ClassGenerator(Table table) {
        // 调用父类的构造方法
        super(table);
        // 初始化实体类型
        this.entityType = new Type(PackageUtils.ENTITY.getValue() + getTableName());
        // 初始化实体类型名
        this.entityTypeName = entityType.getTypeName();
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 获取通用变量名.
     * </pre>
     *
     * @param suffix 自定义后缀
     * @return {@code java.lang.String} - 变量名
     */
    protected String getCommonName(String suffix) {
        // 返回变量名
        return StringUtils.toCamelCase(table.getName(), false) + suffix;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为类导入通用类型.
     * </pre>
     *
     * @param class_ 类
     */
    protected void addCommonImports(Class class_) {
        // 导入的这个包下包含@Autowired和@Qualifier
        class_.addImport(new Type("org.springframework.beans.factory.annotation.*"));
        // 导入的这个包下包含@Controller,@Service和@Repository
        class_.addImport(new Type("org.springframework.stereotype.*"));
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
        // 新建一个参数
        Parameter parameter = new Parameter(entityType, "entity");
        // 为方法添加参数
        method.addParameter(parameter);
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
        // 新建一个参数
        Parameter parameter = new Parameter(new Type("int"), "id");
        // 为方法添加参数
        method.addParameter(parameter);
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
        // 新建一个参数
        Parameter parameter = new Parameter(entityType, "entity");
        // 为方法添加参数
        method.addParameter(parameter);
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
        // 新建一个参数
        Parameter parameter = new Parameter(new Type("int"), "id");
        // 为方法添加参数
        method.addParameter(parameter);
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
    }
}