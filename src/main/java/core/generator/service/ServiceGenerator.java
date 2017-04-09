package core.generator.service;

import core.database.Table;
import core.file.java.Type;
import core.generator.InterfaceGenerator;
import core.util.PackageUtils;

/**
 * Service层接口生成器.
 *
 * @author 李程鹏
 */
public class ServiceGenerator extends InterfaceGenerator {
    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造实例化生成器.
     * </pre>
     *
     * @param table 表格对象
     */
    public ServiceGenerator(Table table) {
        // 调用父类的构造函数
        super(table);
        // 实例化Service层接口的类型
        this.interfaceType = new Type(PackageUtils.SERVICE.getValue() + getTableName() + "Service");
        // 初始化业务层接口的文档注释
        this.comment = "业务层接口";
    }
}
