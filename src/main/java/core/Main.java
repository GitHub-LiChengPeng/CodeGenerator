package core;

import core.database.DatabaseReader;
import core.database.Table;
import core.generator.controller.SpringMVCGenerator;
import core.generator.dao.HibernateGenerator;
import core.generator.service.SpringGenerator;

import java.util.List;

/**
 * 入口类.
 *
 * @author 李程鹏
 */
public class Main {
    /**
     * <strong>Description:</strong>
     * <pre>
     * 入口函数
     * </pre>
     *
     * @param args 参数
     */
    public static void main(String[] args) throws Exception {
        // 读取数据库表格
        List<Table> tables = new DatabaseReader("root", "root", "test").readTables();
        // 生成Dao层代码(Hibernate).
        new HibernateGenerator(tables).generate();
        // 生成Service层代码(Spring).
        new SpringGenerator(tables).generate();
        // 生成Controller层代码(SpringMVC)
        new SpringMVCGenerator(tables).generate();
    }
}