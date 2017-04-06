package core.generator.dao;

import core.database.Table;
import core.file.java.Class;
import core.file.java.Interface;
import core.util.FileUtils;
import core.util.PathUtils;
import core.util.StringUtils;

import java.util.List;

/**
 * Hibernate持久层代码生成器.
 *
 * @author 李程鹏
 */
public class HibernateGenerator {
    /**
     * 表格集合
     */
    private List<Table> tables;

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
            // 生成实体类文件
            generatePO(table);
            // 生成Dao层接口文件
            generateDao(table);
            // 生成Dao层实现类文件
            generateDaoImpl(table);
        }
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 根据表格对象生成实体类文件.
     * </pre>
     *
     * @param table 表格对象
     */
    private void generatePO(Table table) throws Exception {
        // 获取生成的实体类
        Class entity = new POGenerator(table).generate();
        // 根据实体类内容生成文件
        FileUtils.generateFile(PathUtils.ENTITY.getValue(), StringUtils.getJavaFileName(entity), entity.toString(0));
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 根据表格对象生成Dao层接口文件.
     * </pre>
     *
     * @param table 表格对象
     */
    private void generateDao(Table table) throws Exception {
        // 获取生成的Dao接口对象
        Interface dao = new DaoGenerator(table).generate();
        // 根据接口内容生成文件
        FileUtils.generateFile(PathUtils.DAO.getValue(), StringUtils.getJavaFileName(dao), dao.toString());
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 根据表格对象生成Dao层接口实现类文件.
     * </pre>
     *
     * @param table 表格对象
     */
    private void generateDaoImpl(Table table) throws Exception {
        // 获取生成的Dao接口实现类
        Class daoImpl = new DaoImplGenerator(table).generate();
        // 根据实现类的内容生成文件
        FileUtils.generateFile(PathUtils.DAO_IMPL.getValue(), StringUtils.getJavaFileName(daoImpl), daoImpl.toString(0));
    }
}