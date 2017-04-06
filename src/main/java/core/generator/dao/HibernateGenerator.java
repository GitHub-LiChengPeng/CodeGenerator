package core.generator.dao;

import core.database.Table;
import core.file.java.Class;
import core.file.java.Interface;
import core.util.FileUtils;
import core.util.PathUtils;

import java.util.List;

/**
 * Hibernate代码生成器.
 *
 * @author 李程鹏
 */
public class HibernateGenerator {
    /**
     * 表格集合
     */
    private List<Table> tables;

    /**
     * 持久化对象生成器
     */
    private POGenerator poGenerator;

    /**
     * Dao层接口生成器
     */
    private DaoGenerator daoGenerator;

    /**
     * Dao层实现类生成器
     */
    private DaoImplGenerator daoImplGenerator;

    /**
     * <strong>Description:</strong>
     * <pre>
     * 构造实例化生成器.
     * </pre>
     *
     * @param tables 表格集合
     */
    public HibernateGenerator(List<Table> tables) {
        // 赋值
        this.tables = tables;
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 生成代码.
     * </pre>
     */
    public void generate() throws Exception {
        // 遍历表格集合
        for (Table table : tables) {
            // 根据表格对象创建持久化对象生成器
            poGenerator = new POGenerator(table);
            // 根据表格对象创建Dao层接口生成器
            daoGenerator = new DaoGenerator(table);
            // 根据表格对象创建Dao层实现类生成器
            daoImplGenerator = new DaoImplGenerator(table);
            // 定义文件名变量
            String fileName;
            // 生成持久化对象
            Class entity = poGenerator.generate();
            // 创建持久化对象文件名
            fileName = entity.getType().getTypeName() + ".java";
            // 生成持久化对象文件
            FileUtils.generateFile(PathUtils.ENTITY.getValue(), fileName, entity.toString(0));
            // 生成Dao层接口
            Interface dao = daoGenerator.generate();
            // 创建Dao层接口文件名
            fileName = dao.getType().getTypeName() + ".java";
            // 生成Dao层接口文件
            FileUtils.generateFile(PathUtils.DAO.getValue(), fileName, dao.toString());
            // 生成Dao层接口实现类
            Class daoImpl = daoImplGenerator.generate();
            // 创建Dao层接口实现类文件名
            fileName = daoImpl.getType().getTypeName() + ".java";
            // 生成Dao层接口实现类文件
            FileUtils.generateFile(PathUtils.DAO_IMPL.getValue(), fileName, daoImpl.toString(0));
        }
    }
}