package core;

import core.database.DatabaseReader;
import core.database.Table;
import core.generator.SwaggerUIGenerator;
import core.generator.config.*;
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
        // 生成代码
        generate("test", "root", "root");
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 根据数据库信息生成代码.
     * </pre>
     *
     * @param database 数据库
     * @param username 账户名
     * @param password 密码
     */
    private static void generate(String database, String username, String password) throws Exception {
        // 读取数据库表格
        List<Table> tables = new DatabaseReader(username, password, database).readTables();
        // 生成Dao层代码(Hibernate).
        new HibernateGenerator(tables).generate();
        // 生成Service层代码(Spring).
        new SpringGenerator(tables).generate();
        // 生成Controller层代码(SpringMVC)
        new SpringMVCGenerator(tables).generate();
        // 生成配置文件
        new ConfigGenerator(database, username, password).generate();
        // 生成SwaggerUI文件
        new SwaggerUIGenerator().generate();
    }
}