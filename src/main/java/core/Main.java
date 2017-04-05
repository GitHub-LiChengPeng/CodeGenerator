package core;

import core.database.DatabaseReader;
import core.database.Table;
import core.file.java.Interface;
import core.file.java.Class;
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
        List<Table> tables = new DatabaseReader("root", "root", "test").readTables();

        POGenerator poGenerator = new POGenerator(tables.get(0));
        Class class_ = poGenerator.generate();
        System.out.println(class_.toString(0));

        System.out.println("--------------------------------------------");

        DaoGenerator daoGenerator = new DaoGenerator(tables.get(0));
        Interface interface_ = daoGenerator.generate();
        System.out.println(interface_.toString());
    }
}