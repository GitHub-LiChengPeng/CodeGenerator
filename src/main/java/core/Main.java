package core;

import core.file.java.Class;
import core.database.DatabaseReader;
import core.database.Table;
import core.generator.dao.POJOGenerator;

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
        POJOGenerator generator = new POJOGenerator(tables.get(0));
        Class class_ = generator.generate("core.pojo.");
        System.out.print(class_.toString(0));
    }
}