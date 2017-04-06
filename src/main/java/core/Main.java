package core;

import core.database.DatabaseReader;
import core.database.Table;
import core.generator.dao.*;

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
    }
}