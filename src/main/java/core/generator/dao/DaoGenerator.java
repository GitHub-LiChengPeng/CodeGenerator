package core.generator.dao;

import core.database.Table;
import core.file.java.Type;
import core.generator.InterfaceGenerator;
import core.util.PackageUtils;

/**
 * Dao层接口生成器.
 *
 * @author 李程鹏
 */
public class DaoGenerator extends InterfaceGenerator {
    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造实例化生成器.
     * </pre>
     *
     * @param table 表格对象
     */
    public DaoGenerator(Table table) {
        // 调用父类的构造方法
        super(table);
        // 实例化Dao层接口的类型
        this.interfaceType = new Type(PackageUtils.DAO.getValue() + getTableName() + "Dao");
        // 设置Dao接口的文档注释
        this.comment = "持久层接口";
    }
}
