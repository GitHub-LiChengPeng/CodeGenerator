package core.generator;

import core.file.java.Class;
import core.database.Table;
import core.file.java.Type;
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
}